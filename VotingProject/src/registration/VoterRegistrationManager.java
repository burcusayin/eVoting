package registration;

import hash.Hash;
import helper.CertificateReader;
import helper.CodeGenerator;
import helper.DBHelper;
import helper.EmailValidator;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import systemCreation.Election;

/*
 * Author :Arzum
 * Date : November 27, 2016
 */
public class VoterRegistrationManager{
	/*
	 * This class is responsible for registration of the voter into the the system as registered voter
	 * Voter information already stored on db.
	 * 
	 * Registration is accepted via forms which are filled on Kiosk. Registration code and fake registration
code are generated for each registered user and shared with them. Hash value of them are kept in the
system. Number of eligible voters are determined.
	 */

	/*instance variables*/
	private Voter voter;
	private Election election;
	private DBHelper dbHelper;
	private Hash hashGenerator;
	private CodeGenerator codeGenerator;
	private final int DIGESTION_SIZE = 512;
	private final File folder ;
	private CertificateReader certificateReader;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	/*constructor*/
	public VoterRegistrationManager(Election el) {
		
		/*those values will come from GUI*/
		voter = new Voter("NA", "NA", el.getElectionEndTime(), "NA");
		dbHelper = new DBHelper();
		codeGenerator = new CodeGenerator();
		hashGenerator = new Hash();
		election = el;
		folder = new File("C://Users//Arzum//Google Drive//CENG661_TermProject//Certificates");
		certificateReader = new CertificateReader();
	}
	
	public boolean connectDB(String userName, String password){
		
		int connStatus = dbHelper.openConnection(userName, password);
		
		if(connStatus== -1){
			return false;
		}
		
		return true;
	}
	/*
	 * Step1 : Check if the registration time valid from DB
	 */
	
	public boolean isRegistrationAvailable(){
		
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		
		if((sdf.format(election.getRegStartTime()).compareTo(currentDate)<= 0)&&
		   (sdf.format(election.getRegEndTime()).compareTo(currentDate)>= 0))
				
			return true;
		
		return false;
		
	}
	
	public void setVoterInfo(String vname, String email, Date birthdate,
			String certificateName){
		voter.setVname(vname);
		voter.setEmail(email);
		voter.setBirthdate(birthdate);
		voter.setCertificateName(certificateName);
	}
	
	/*Step 2:Check the voter information from Voter table in the DB
	 * 
	 */
	public boolean isVoterValid(){
		
		Voter v = dbHelper.getVoter(voter.getEmail());
		Date vbirthDate = v.getBirthdate();
		
		System.out.println(v);
		if(v==null){
			return false;
		}
		
		if(voter.getVname().equalsIgnoreCase(v.getVname())
				/*&& ((sdf.format(voter.getBirthdate()).compareTo(sdf.format(vbirthDate))== 0)
				&& voter.getCertificateName().equals(v.getCertificateName())
				&& voter.getEmail().equals(v.getEmail()))*/){
			
			voter.setVid(v.getVid());
			System.out.println("Voter id in voter valid function :"+ voter.getVid());
			return true;
		}
				
		
		return false;
	}
	
	/*
	 * Step 3 : If voter is saved in Voter db before,
	 *          then, generate  reg code, coercion code and save her as registered voter.
	 *          
	 *           */
	public RegisteredVoter registerVoter(String signedForm){
		String regCode = generateRegistrationCode();
		String coercionCode = generateCoercionCode();
		
		System.out.println("reg code:"+ regCode);
		System.out.println("coercion code"+ coercionCode);
		
		while(regCode.equals(coercionCode)){
			System.out.println("in looop");
			coercionCode = generateCoercionCode();
		
		}
		
		RegisteredVoter regVoter = register(signedForm,regCode,coercionCode);
		
		dbHelper.insertRegisteredVoter(regVoter);
		
		return regVoter;
	}
	 private String generateRegistrationCode(){
		
		 String regCode = codeGenerator.generateACode();
		
		 return regCode;
		 
	 }
	 
	 private String generateCoercionCode(){
		 String coercionCode = codeGenerator.generateACode();
			
		 return coercionCode;
	 }
	
	 private RegisteredVoter register(String signedForm, String regCode, String coercionCode){
		
		 String hashOfForm = ""; // DECRYPTION METHOD HERE
		 // Sertifikadan public keyi çekilecek
		 String hashOfRegCode = hashGenerator.sha3(regCode, DIGESTION_SIZE);		 
		 String hashOfCoercionCode =hashGenerator.sha3(coercionCode, DIGESTION_SIZE);
		 
		 RegisteredVoter regVoter = new RegisteredVoter(voter,hashOfForm,hashOfRegCode,hashOfCoercionCode);
		 
		 return regVoter;
	 }
	
	 public boolean checkCertificate(String certificateName) {
		// sertifika diskteki file da var mı?
		 if(checkCertificateFolder(folder, certificateName)){
			 certificateReader.readCertificate(certificateName);
		     if(certificateReader.getCertificateUserName().trim().equalsIgnoreCase(voter.getVname())
		    	 && isCertificateValidTime(certificateReader.getCertificateStartDate(),certificateReader.getCertificateEndDate()) )
		    	 
		    	 return true; 
	 
		 //içindeki bilgilerle voterın guiden girdiği bilgiler uyuşuyor mu?
		 // valid mi hala?
		//dosyadan okunacak
		 }
			 
		return false;
	 }
	 
     private boolean isCertificateValidTime(String sTime, String eTime){
    	 
    	  Date startTime = null,endTime = null;
    	  try {
			startTime = sdf.parse(sTime);
			endTime  = sdf.parse(eTime);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 if((sdf.format(startTime).compareTo(sdf.format(election.getElectionStartTime()))<= 0)
    			&& (sdf.format(endTime)).compareTo(sdf.format(election.getRegEndTime()))>= 0)
    		 return true;
    	 
    	 return false;
    	 
     }
	/*public boolean checkSignature(String certificateName) {
		// TODO Auto-generated method stub
		return false;
	} */
	 
		
	 public boolean checkVoterEmailValidity(){
		
		String email = voter.getEmail();
		 if(EmailValidator.isIyteEmailAddress(email)||EmailValidator.isIyteStudentEmailAddress(email))
			 
			 return true;
		 
		 return false;
	}

	
	 public int getNumberOfEligibleVoters(){
		
		return dbHelper.countRegisteredVoters() ;
	}

	 private boolean checkCertificateFolder(final File folder, String certificateName){

	       for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	checkCertificateFolder(fileEntry,certificateName);
	        } else {
	            if(fileEntry.getName().equals(certificateName))
	            	return true;
	                 
	        }
	    }
		return false;
	}
	 
	 public String signForm(){
		 
		 voter.setVid(dbHelper.getVoter(voter.getEmail()).getVid());

		 int vSecret = dbHelper.getVotersSecret(voter.getVid());
		 
		 System.out.println("Voter id:"+ voter.getVid()+" secret key :"+ vSecret);
		 String form = voter.getEmail()+ voter.getVname()+ voter.getBirthdate()+voter.getCertificateName();
		
		 String hashOfForm = hashGenerator.sha3(form, DIGESTION_SIZE);
		 
		 // sign with his secret
		 String signedForm=""; //ENCRYPTION here-----
		 return signedForm;
	 }

	@Override
	public String toString() {
		return "VoterRegistrationManager [voter=" + voter.getVid()+" "+voter.getEmail() + ", election="
				+ election + "]";
	}
	 
	 
}
