package voting;

//Election bitince isCompleted ý true yap
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
		//GUI aracýlýðýyla Ballottan seçilmiþ olan Candidate Id alýnacak
		//encrypt the vote by calling encrypt() method from paillierInterface
		//set encryptedVote
		//return encrypted vote
		return null;
	} 

	// GUI'den giriþ bilgileri alýnacak
	private boolean isLoginSuccessful() {
		//GUI'den girilmiþ olan email ve regCode alýnýr.
		//regCode'un hash deðeri hesaplanýr. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yapýlýr.
		//Voter ve RegisteredVoter db tablolarý joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash deðerleri çekilir. Ayrýca VoterId çekilip set edilir.
		//H(RegCode) ile bu çekilen deðerler karþýlaþtýrýlýr.
		//HErhangi birine eþitse result = true yapýlýr.
		//Eðer fake code girilmiþse isCodeFake = true yap.
		//Result deðeri döndürülür.
		
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
		//VoterID'ye bakarak VoterCredentials db tablosundan private key çekilir.
		//encryptedVote bu key ile imzalanýr. (Elliptic curve'ün interfacei kullanýlýr)
		//signedEncryptedVote set edilip döndürülür.
		String signedEncryptedVote = null;
		
		return signedEncryptedVote;
	}
	
	private boolean saveVote()
	{
		//Calculate hash value of signedEncryptedVote. H(signedEncryptedVote) diyelim.
		//DB'deki Vote tablosuna þu bilgileri ekle: voterID, signedEncryptedVote, H(signedEncryptedVote).
		//Baþarýlý bir þekilde kaydedilip edilmediðini kontrol et.
		//Kaydedildiyse, numOfCastedVotes deðerini 1 arttýr ve true döndür.
		//Kaydedilmediyse false döndür.

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
