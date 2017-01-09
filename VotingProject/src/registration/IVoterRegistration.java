package registration;

/*
 * Author :Arzum
 * Date : November 26, 2016
 */
public interface IVoterRegistration {

	public boolean checkCertificate(String certificateName);
	public boolean checkSignature(String certificateName);
	
	//changed here
	public RegisteredVoter registerVoter(String regCode, String coercionCode);
	
	public String getHashRegistrationCode();
	public String getHashCoercionCode();
	
	public String getHashofRegistrationForm(Voter voter);
	
	
}
