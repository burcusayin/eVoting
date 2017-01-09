package registration;

/*
 * Author :Arzum
 * Date : November 26, 2016
 */
public class RegisteredVoter extends Voter{
	
	private String hashofForm;
	private String hashRegistrationCode;
	private String hashCoercionCode;
	

	public RegisteredVoter(Voter voter, String hashofForm, String hashRegistrationCode, String hashCoercionCode) {
		
		super(voter.getVid(),voter.getVname(), voter.getEmail(), voter.getBirthdate(), voter.getCertificateName());
	    this.hashofForm = hashofForm;
	    this.hashRegistrationCode = hashRegistrationCode;
	    this.hashCoercionCode = hashCoercionCode;
	    		
	}

	public String getHashRegistrationCode() {
		
		return hashRegistrationCode;
	}

	public String getHashofForm() {
		return hashofForm;
	}

	public String getHashCoercionCode() {
		return hashCoercionCode;
	}

	
	
	
	
	
}
