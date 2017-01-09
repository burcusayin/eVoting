package wp_preparationForVoting;

import java.util.ArrayList;

/*
 * Author :Arzum
 * Date : November 26, 2016
 * 
 * This class represents a ballot.
 * On the ballot there are only list of candidates.
 */
public class Ballot {
	/*instance variables*/
	private ArrayList<Candidate> candidates;

	//constructor()
	public Ballot(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}
	
       
	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}


	@Override
	public String toString() {
		return "Ballot [candidates=" + candidates + "]";
	}
	
    
	
}
