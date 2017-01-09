package voting;

import java.math.BigInteger;

import Paillier.IVote;
import controller.Controller;
import hash.IHash;

//Election bitince isCompleted � true yap
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
		//GUI arac�l���yla Ballottan se�ilmi� olan Candidate Id al�nacak
		//encrypt the vote by calling encrypt() method from paillierInterface
		//set encryptedVote
		//return encrypted vote
		
		int id = controller.getCandidateID();//get Candidate id
		vote = controller.vote;
		BigInteger encryptedVote = vote.vote(id);
		
		return encryptedVote.toString();
	} 

	// GUI'den giri� bilgileri al�nacak

	
	public boolean isLoginSuccessful() {
		//GUI'den girilmi� olan email ve regCode al�n�r.
		//regCode'un hash de�eri hesaplan�r. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yap�l�r.
		//Voter ve RegisteredVoter db tablolar� joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash de�erleri �ekilir. Ayr�ca VoterId �ekilip set edilir.
		//H(RegCode) ile bu �ekilen de�erler kar��la�t�r�l�r.
		//HErhangi birine e�itse result = true yap�l�r.
		//E�er fake code girilmi�se isCodeFake = true yap.
		//Result de�eri d�nd�r�l�r.
		boolean result;
		String email = controller.getEmail();
		String regCode = controller.getRegCode();
		hash = controller.hash;
		hashOfRegCode = hash.sha3(regCode, digestSizeBits);
		
		//DBden regCode ve hashRegCode �ekilir set edilir.
		
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
		       //If the coercion flag is 0, set coercionFlag = 1 (�u i�lem saveVote'da yap�lacak: and save the vote. Check if it is successfully saved, if not change the flag.)
		//If isCodeFake is false, then set the validityFlag = 1 (�u i�lem saveVote'da yap�lacak: and save the vote.)
		//(�u i�lem saveVote'da yap�lacak: Check if it is successfully saved, if not change the validity flag.)
		
		return false;
	}
	
	private String signEncryptedVote()
	{
		//VoterID'ye bakarak VoterCredentials db tablosundan private key �ekilir.
		//encryptedVote bu key ile imzalan�r. (Elliptic curve'�n interfacei kullan�l�r)
		//signedEncryptedVote set edilip d�nd�r�l�r.
		this.signedEncryptedVote = null;
		
		return signedEncryptedVote;
	}
	
	private boolean saveVote()
	{
		//check vote checkCastedVoteValidity
		//If it returns true, then call signEncryptedVote
		//Calculate hash value of signedEncryptedVote. H(signedEncryptedVote) diyelim.
		//DB'deki Vote tablosuna �u bilgileri ekle (save) : voterID, signedEncryptedVote, H(EV || Signature || HashOfRegCode or HashOfFakeRegCode).
		//If successfully saved: modify the flags, whose values are not equal to -1, of registered voters as parameters validityFlag and coercionFlag. 
		//Kaydedildiyse, numOfCastedVotes de�erini 1 artt�r ve true d�nd�r.
		//Kaydedilmediyse false d�nd�r ve checkCastedVoteValidity fonksiyonunda set edilen flagleri de�i�tir:


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
