package voting;

import java.math.BigInteger;

import Paillier.IVote;
import controller.Controller;
import hash.IHash;

//Election bitince isCompleted ý true yap
public class VotingModule implements IVoting{
	
	private boolean isCodeFake;
	private int voterID;
	private String hashOfRegCode;
	private String hashOfFakeRegCode;
	private String encryptedVote;
	private String signedEncryptedVote;
	private int numOfCastedVotes = 0;
	private Controller controller;
	private IVote vote;
	private IHash hash;
	private int digestSizeBits = 512;
	private String dbHashOfRegCode;
	private String dbHashOfFakeRegCode;
	private int validityFlag;
	private int coercionFlag;

	
	public VotingModule() throws Exception{
		this.isCodeFake = false;
		this.hashOfRegCode = null;
		this.hashOfFakeRegCode = null;
		this.encryptedVote = null;
		this.voterID = -1;
		this.controller = Controller.getInstance();
		this.validityFlag = -1;
		this.coercionFlag = -1;
	}


	private String getCastedVote() {
		//GUI aracýlýðýyla Ballottan seçilmiþ olan Candidate Id alýnacak
		//encrypt the vote by calling encrypt() method from paillierInterface
		//set encryptedVote
		//return encrypted vote
		
		int id = controller.getCandidateID();//get Candidate id
		vote = controller.vote;
		BigInteger encryptedVote = vote.vote(id);
		
		return encryptedVote.toString();
	} 

	// GUI'den giriþ bilgileri alýnacak

	
	public boolean isLoginSuccessful() {
		//GUI'den girilmiþ olan email ve regCode alýnýr.
		//regCode'un hash deðeri hesaplanýr. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yapýlýr.
		//Voter ve RegisteredVoter db tablolarý joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash deðerleri çekilir. Ayrýca VoterId çekilip set edilir.
		//H(RegCode) ile bu çekilen deðerler karþýlaþtýrýlýr.
		//HErhangi birine eþitse result = true yapýlýr.
		//Eðer fake code girilmiþse isCodeFake = true yap.
		//Result deðeri döndürülür.
		boolean result;
		String email = controller.getEmail();
		String regCode = controller.getRegCode();
		hash = controller.hash;
		hashOfRegCode = hash.sha3(regCode, digestSizeBits);
		
		//DBden regCode ve hashRegCode çekilir set edilir.
		
		if(hashOfRegCode.equals(this.dbHashOfRegCode))
		{
			result = true;
		}
		else if(hashOfRegCode.equals(this.dbHashOfFakeRegCode))
		{
			this.isCodeFake = true;
			this.hashOfFakeRegCode = hashOfRegCode; // control if it successfully assign
			hashOfRegCode = null;
			result = true;
			
		}
		else
		{
			result = false;
		}
		
		return result;
	} 
	
	
	private boolean checkCastedVoteValidity()
	{
		//First, check validity flag
		//If it is zero, check isCodeFake.
		//If isCodeFake is true, then check coercion flag. 
		       //If the coercion flag is 1, then vote cannot be accepted. 
		       //If the coercion flag is 0, set coercionFlag = 1 (Þu iþlem saveVote'da yapýlacak: and save the vote. Check if it is successfully saved, if not change the flag.)
		//If isCodeFake is false, then set the validityFlag = 1 (Þu iþlem saveVote'da yapýlacak: and save the vote.)
		//(Þu iþlem saveVote'da yapýlacak: Check if it is successfully saved, if not change the validity flag.)
		
		return false;
	}
	
	private String signEncryptedVote()
	{
		//VoterID'ye bakarak VoterCredentials db tablosundan private key çekilir.
		//encryptedVote bu key ile imzalanýr. (Elliptic curve'ün interfacei kullanýlýr)
		//signedEncryptedVote set edilip döndürülür.
		this.signedEncryptedVote = null;
		
		return signedEncryptedVote;
	}
	
	private boolean saveVote()
	{
		//check vote checkCastedVoteValidity
		//If it returns true, then call signEncryptedVote
		//Calculate hash value of signedEncryptedVote. H(signedEncryptedVote) diyelim.
		//DB'deki Vote tablosuna þu bilgileri ekle (save) : voterID, signedEncryptedVote, H(EV || Signature || HashOfRegCode or HashOfFakeRegCode).
		//If successfully saved: modify the flags, whose values are not equal to -1, of registered voters as parameters validityFlag and coercionFlag. 
		//Kaydedildiyse, numOfCastedVotes deðerini 1 arttýr ve true döndür.
		//Kaydedilmediyse false döndür ve checkCastedVoteValidity fonksiyonunda set edilen flagleri deðiþtir:


		return false;
	}
	

	private boolean isCodeFake() {
		return isCodeFake;
	}
	
	public int getNumberOfCastedVotes()
	{
		return numOfCastedVotes;
	}

	public String getHashOfRegCode()
	{
		return this.hashOfRegCode;
	}
	
	public String getHashOfFakeRegCode()
	{
		return this.hashOfFakeRegCode;
	}
}
