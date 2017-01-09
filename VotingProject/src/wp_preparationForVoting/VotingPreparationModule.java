package wp_preparationForVoting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import helper.DBHelper;
/*
 * Author: Arzum
 * Date : January 6, 2017
 */
public class VotingPreparationModule {
/*
 * This is a driver class to show how to use VotingPreparationModule
 */
	public static void main(String[] args) {
		/*specify db credentials*/
		String dbUserName ="dblab";
		String dbPassword = "123456";
		
		//determine election id
		int electionID = 1;
		
		/*specifies specific dates for the election*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date bdate1 = null,bdate2 = null,bdate3 = null,bdate4 = null;
               
		try {
			bdate1 = sdf.parse("1-1-1993");
			bdate2 = sdf.parse("15-1-1995");
	    	bdate3 = sdf.parse("17-1-1991");
	    	bdate4 = sdf.parse("20-1-1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		/*create candidates*/
		Candidate c1 = new Candidate("Candidate1","Computer Engineer",bdate1);
		Candidate c2 = new Candidate("Candidate2","Chemical Engineer",bdate2);
		Candidate c3 = new Candidate("Candidate3","BioEngineer",bdate3);
		Candidate c4 = new Candidate("Candidate4","Physicist",bdate4);
		
		/*connect to db*/
		DBHelper dbHelper = new DBHelper();
		dbHelper.openConnection(dbUserName, dbPassword);
		
		// ask for the count of registered voters
		int numOfEligibleVoters = dbHelper.countRegisteredVoters();
		
		// create the module manager
		PreparationForVotingManager pvotingManager = new PreparationForVotingManager(numOfEligibleVoters, 1, dbHelper);
		
		/*pvotingManager.addCandidate(c1);
		pvotingManager.addCandidate(c2);
		pvotingManager.addCandidate(c3);
		pvotingManager.addCandidate(c4);
		
		//pvotingManager.saveCandidates();
		*/
		
		// obtain candidate list
	    ArrayList<Candidate> candidates = pvotingManager.getCandidateListFromDB(electionID);
		
	    /*generate and display the ballot*/
		System.out.println("***************************Ballot********************\n"+pvotingManager.generateBallot());
		/*genarate and display bulletin board*/
		System.out.println("\n***************************Bulletin Board*************\n"+pvotingManager.generateBulletinBoard());
		
		
		
	}

}
