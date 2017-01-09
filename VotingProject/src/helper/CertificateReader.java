package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Arzum Karatas
 */
public class CertificateReader {

	//instance variable
	private ArrayList<String> certificateInfo;
	
	private final int NAME = 7;
	private final int STARTDATE = 5;
	private final int ENDDATE = 6;
	private final int PUBLICKEY1 = 13; 
	private final int PUBLICKEY2 = 14;
	private String certificateFolderPath;
	
	public CertificateReader(){
		certificateInfo = new ArrayList<String>();
		certificateFolderPath= "C:/Users/Arzum/Google Drive/CENG661_TermProject/Certificates/";
	}
	
	public ArrayList<String>  getCertificateInfo() {
		
		return certificateInfo;
	   
	}
	
	public void  readCertificate(String certificate) {
		
		//certificateInfo.clear();
	    Scanner scan;
	    String line ="";
		try {
			scan = new Scanner(new File(certificateFolderPath+certificate));
		    while(scan.hasNextLine()){
		        line = scan.nextLine();
		        certificateInfo.add(line);    
		    }
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	   
	}
	
	public String getCertificateUserName(){
		String name ="";
		if(certificateInfo.size()>= 0)
			name= certificateInfo.get(NAME);
		return name;
		
	}
	
	public String getCertificateStartDate(){
		String date ="";
		if(certificateInfo.size()>= 0)
			date= certificateInfo.get(STARTDATE);
		return date;
		
	}
	
	public String getCertificateEndDate(){
		String date ="";
		if(certificateInfo.size()>= 0)
			date= certificateInfo.get(ENDDATE);
		return date;
		
	}
	
	public String getPublicKey1(){
		String key ="";
		if(certificateInfo.size()>= 0)
			key= certificateInfo.get(PUBLICKEY1);
		return key;
	}
	
	public String getPublicKey2(){
		String key ="";
		if(certificateInfo.size()>= 0)
			key= certificateInfo.get(PUBLICKEY2);
		return key;
	}
}
