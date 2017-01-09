package wp_preparationForVoting;

import java.util.ArrayList;

import helper.DBHelper;

/*
 * Author :Arzum
 * Date : January 06, 2017
 */
public class PreparationForVotingManager {

	/*instance variables*/
	private CandidateManager candidateManager;
	private Ballot ballot;
	private BulletinBoard bulletinboard;
	private int eid;
	private DBHelper dbHelper;
	private int numOfEligibleVoters;
	
	// constructor
	public PreparationForVotingManager(int numOfEligibleVoters, int eid, DBHelper dhelper) {
		this.eid = eid;
		candidateManager = new CandidateManager(eid,dhelper);
		dbHelper = dhelper;
		this.numOfEligibleVoters = numOfEligibleVoters;
	}
	
	public void addCandidate(Candidate c){
		candidateManager.addCandidate(c);
	}
	
	public void removeCandidate(Candidate c){
		candidateManager.removeCandidate(c);
	}

	public void saveCandidates(){
		candidateManager.saveCandidatestoDB();
	}
	
	public Ballot generateBallot(){
		Ballot ballot= null;
		
		if(candidateManager.getCandidates().size()>0){
			
			ballot = new Ballot(candidateManager.getCandidates());
			
		}
		return ballot;
	}
	
	public BulletinBoard generateBulletinBoard(){
		BulletinBoard board = new BulletinBoard(numOfEligibleVoters,candidateManager.getCandidates());
		return board;
	}
	
	public ArrayList<Candidate> getCandidateListFromDB(int electionID){
		
		return candidateManager.getCandidatesFromDB(electionID);
	}
}
