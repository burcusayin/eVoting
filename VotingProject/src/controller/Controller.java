package controller;

import java.math.BigInteger;

import Paillier.CreatingEvoting;
import Paillier.ICreateElection;
import Paillier.IVote;
import Paillier.Voting;
import hash.Hash;
import hash.IHash;
import helper.DBHelper;
import voting.IVoting;
import voting.VotingModule;

//@generic, emre
public class Controller{
	
	private int selectedCandidateID = -1;
	private String email = null;
	private String regCode = null;

	/*Generic Def*/
	private static Controller instance = null;
	
	/*Arzum Def*/
	
	/*Burcu Def*/
	public DBHelper db = null;
	ICreateElection paillier = null;
	public IVote vote = null;
	public IHash hash = null;
	public IVoting vm = null;
	public String queriedVote = null;
	
	/*Emre Def*/
	
	/*Guven Def*/
	
	/*Leyla Def*/
	
	/*Pelin Def*/
	
	//@generic, emre
	private Controller() throws Exception{
		/*Generic Def*/
		
		/*Arzum Def*/
		
		/*Burcu Def*/
		db = new DBHelper();
		paillier = new CreatingEvoting(new BigInteger("10"), 10);
		vote = new Voting(paillier);
		hash = new Hash();
		vm = new VotingModule();
		/*Emre Def*/
		
		/*Guven Def*/
		
		/*Leyla Def*/
		
		/*Pelin Def*/
		
	}
	
	//@generic, emre
	public static Controller getInstance() throws Exception{
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public int getCandidateID()
	{
		return this.selectedCandidateID;
	};
	
	public void setCandidateID(int id)
	{
		this.selectedCandidateID = id;
	};
	
	public String getEmail()
	{
		return this.email;
	};
	
	public void setEmail(String mail)
	{
		this.email = mail;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	};
	
	public void resetParameters()
	{
		this.selectedCandidateID = -1;
		this.email = null;
		this.regCode = null;
	}

	public String getQueriedVote() {
		return queriedVote;
	}

	public void setQueriedVote(String queriedVote) {
		this.queriedVote = queriedVote;
	}
	
	
	
}
