package voting;

//Election bitince isCompleted � true yap
public class Voting {
	
	private boolean isCodeFake;
	private int voterID;
	private String hashOfRegCode;
	private String encryptedVote;
	private int numOfCastedVotes = 0;
	
	public Voting() {
		this.isCodeFake = false;
		this.hashOfRegCode = null;
		this.encryptedVote = null;
		this.voterID = -1;
	}


	private String getCastedVote() {
		//GUI arac�l���yla Ballottan se�ilmi� olan Candidate Id al�nacak
		//encrypt the vote by calling encrypt() method from paillierInterface
		//set encryptedVote
		//return encrypted vote
		return null;
	} 

	// GUI'den giri� bilgileri al�nacak
	private boolean isLoginSuccessful() {
		//GUI'den girilmi� olan email ve regCode al�n�r.
		//regCode'un hash de�eri hesaplan�r. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yap�l�r.
		//Voter ve RegisteredVoter db tablolar� joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash de�erleri �ekilir. Ayr�ca VoterId �ekilip set edilir.
		//H(RegCode) ile bu �ekilen de�erler kar��la�t�r�l�r.
		//HErhangi birine e�itse result = true yap�l�r.
		//E�er fake code girilmi�se isCodeFake = true yap.
		//Result de�eri d�nd�r�l�r.
		
		boolean result = false;
		return result;
	} 
	
	private boolean checkCastedVoteValidity()
	{
		//First, check validity flag
		//If it is zero, check isCodeFake.
		//If isCodeFake is true, then check coercion flag. 
		       //If the coercion flag is 1, then vote cannot be accepted. 
		       //If the coercion flag is 0, set it to 1 and save the vote. Check if it is successfully saved, if not change the flag.
		//If isCodeFake is false, then set the validity flag to 1 and save the vote.
		//Check if it is successfully saved, if not change the validity flag.
		return false;
	}
	
	private String signEncryptedVote()
	{
		//VoterID'ye bakarak VoterCredentials db tablosundan private key �ekilir.
		//encryptedVote bu key ile imzalan�r. (Elliptic curve'�n interfacei kullan�l�r)
		//signedEncryptedVote set edilip d�nd�r�l�r.
		String signedEncryptedVote = null;
		
		return signedEncryptedVote;
	}
	
	private boolean saveVote()
	{
		//Calculate hash value of signedEncryptedVote. H(signedEncryptedVote) diyelim.
		//DB'deki Vote tablosuna �u bilgileri ekle: voterID, signedEncryptedVote, H(signedEncryptedVote).
		//Ba�ar�l� bir �ekilde kaydedilip edilmedi�ini kontrol et.
		//Kaydedildiyse, numOfCastedVotes de�erini 1 artt�r ve true d�nd�r.
		//Kaydedilmediyse false d�nd�r.

		return false;
	}
	

	private boolean isCodeFake() {
		return isCodeFake;
	}
	
	public int getNumberOfCastedVotes()
	{
		return numOfCastedVotes;
	}


}
