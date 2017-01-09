package wp_preparationForVoting;

import java.util.ArrayList;

/*
 * Author :Arzum
 * Date : November 26, 2016
 * 
 * This class represent a bulletin board.
 * There are some specific information below on the board
 * 
 * numOfEligibleVoters : the count of registered voters that are allowed to use their votes
 * noOfVotes: the count of the votes are used without regarding vote validity.
 * noOfValidVotes : the count of valid votes
 * noOfInvalidVotes: the count of invalid votes
 * message : it holds specific messages about the voting process such as below
 *            - election is started/finished
 *            - votes are shuffling now
 *            - Election is finished!
 *            - Results of the election ...
 * 
 * candidates : it holds the list of candidates for a specific election.
 */
public class BulletinBoard {
	
	/*attributes*/
	private int numOfEligibleVoters;
	private int noOfVotes;
	private int noOfValidVotes;
	private int noOfInvalidVotes;
	private String message;
	private ArrayList<Candidate> candidates;
		
	/*constructor*/
	public BulletinBoard(int numOfEligibleVoters, ArrayList<Candidate> candidates ) {
		noOfVotes =0;
		noOfValidVotes = 0;
		noOfInvalidVotes= 0;
		message = "Welcome!";
		this.numOfEligibleVoters = numOfEligibleVoters;
		this.candidates = candidates;
	}

	/*getters and setters*/
	
	public int getNumOfEligibleVoters() {
		return numOfEligibleVoters;
	}

	public void setNumOfEligibleVoters(int numOfEligibleVoters) {
		this.numOfEligibleVoters = numOfEligibleVoters;
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}

	
	public int getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(int noOfVotes) {
		this.noOfVotes = noOfVotes;
	}

	public int getNoOfValidVotes() {
		return noOfValidVotes;
	}

	public void setNoOfValidVotes(int noOfValidVotes) {
		this.noOfValidVotes = noOfValidVotes;
	}

	public int getNoOfInvalidVotes() {
		return noOfInvalidVotes;
	}

	public void setNoOfInvalidVotes(int noOfInvalidVotes) {
		this.noOfInvalidVotes = noOfInvalidVotes;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/*toString*/
	@Override
	public String toString() {
		return "BulletinBoard [numOfEligibleVoters=" + numOfEligibleVoters
				+ ", message=" + message + ", candidates=" + candidates + "]";
	}
}
