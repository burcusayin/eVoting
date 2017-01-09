package helper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import wp_preparationForVoting.Candidate;
import registration.RegisteredVoter;
import registration.Voter;

public class DBHelper {

	 private Connection dbConnection;
	 
	 public int openConnection(String userName, String password)
	    {
	        try {
	            dbConnection = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@localhost:1521:xe",userName, password); //connect the database by instantiating DriverManager object via username and password
	                                                                                //that we already created on sql command line. To connect db url format for oracle 
	                                                                                //jdbc:oracle:thin:@hostname:port Number:databaseName, thin->driver name, oracle database xe
	        } catch (SQLException ex) {
	            
	           return -1;
	        }
	        return 0;
	    }
	 
	 public ResultSet getResultOfQuery( String query){
        ResultSet rs=null;
        try {
          
            PreparedStatement ps = dbConnection.prepareStatement(query);/*a Statement object that carries your SQL language query to the database*/
            rs = ps.executeQuery();/*instantiates a ResultSet object that retrieves the results of your query, and executes a simple while loop, which retrieves and displays those results.*/
                                        /*The java.sql.ResultSet interface represents the result set of a database query. A ResultSet object maintains a cursor that points to the current row in the result set. 
                                        The term "result set" refers to the row and column data contained in a ResultSet object.*/
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            /*public static final Level SEVERE
            SEVERE is a message level indicating a serious failure.*/
        }
        return rs;
	    }
	
	 public Voter getVoter(String email){
		
		 ResultSet rs=null;
         Voter voter = null;
         try {
            
        	String query = "SELECT * FROM voter where email = '"+ email+"'";
        	//System.out.println(query);
            PreparedStatement ps = dbConnection.prepareStatement(query);
           
            
        	
            rs = ps.executeQuery(query);//returns result set of the query, used for only select queries
     
        	
            while(rs.next())
            {
                // System.out.println("voterid: "+rs.getInt(1)+" name:"+rs.getString(2)+" email:"+rs.getString(3)+" birthdate: "+rs.getDate(4)+" certificateName:"+rs.getString(5));
                 voter = new Voter(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return voter;
		
	}
	 
	 

	 public boolean insertRegisteredVoter(RegisteredVoter regVoter )
	    {
	        try {
	        	System.out.println("inside insert register method");
	        	
	            String query="INSERT INTO registeredvoter(voterid, hash_regcode, hash_fakeregcode, validityflag, coercionflag)"
	                    + "values(?,?,?,?,?)";
	            PreparedStatement ps = dbConnection.prepareStatement(query);/*An object that represents a precompiled SQL statement.
	                                                                          A SQL statement is precompiled and stored in a PreparedStatement object.
	                                                                        This object can then be used to efficiently execute this statement multiple times. Prepare statement is an interface extends from Statement class*/


	            ps.setInt(1,regVoter.getVid());
	            ps.setString(2, regVoter.getHashRegistrationCode());
	            ps.setString(3, regVoter.getHashCoercionCode());
	            ps.setBoolean(4, false);
	            ps.setBoolean(5, false);
	            ps.executeUpdate();
	            
	            System.out.println("executed");
	            
	            dbConnection.commit();
	            
	            return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	             return false;
	        }
	    }

	 public boolean insertCandidate(Candidate candidate, int electionid )
	    {
	        try {
	        	System.out.println("inside insert candidate method");
	        	
	            String query="INSERT INTO candidate(candidateid,name,job,birthday,electionid)"
	                    + "values(?,?,?,?,?)";
	            PreparedStatement ps = dbConnection.prepareStatement(query);/*An object that represents a precompiled SQL statement.
	                                                                          A SQL statement is precompiled and stored in a PreparedStatement object.
	                                                                        This object can then be used to efficiently execute this statement multiple times. Prepare statement is an interface extends from Statement class*/


	            ps.setInt(1,candidate.getCid());
	            ps.setString(2, candidate.getName());
	            ps.setString(3, candidate.getJob());
	            ps.setDate(4, new Date(candidate.getBirthdate().getTime()));
	            ps.setInt(5, electionid);
	            ps.executeUpdate();
	            
	            System.out.println("executed");
	            
	            dbConnection.commit();
	            
	            return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	             return false;
	        }
	    }
	 
	 public boolean isRegisteredVoter(int vid){
			
		 ResultSet rs=null;
		         
         try {
            
        	String query = "SELECT * FROM registeredvoter where voterid = "+ vid;
        	//System.out.println(query);
            PreparedStatement ps = dbConnection.prepareStatement(query);
           
            
        	
            rs = ps.executeQuery(query);//returns result set of the query, used for only select queries
     
        	
            if(rs.next())
            {
                return true;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return false;
		
	}
	 
	 public boolean isRegisteredCandidate(int electionid, int cid){
			
		 ResultSet rs=null;
		         
         try {
            
        	String query = "SELECT * FROM candidate where candidateid = "+ cid+" and electionid = "+electionid;
        	//System.out.println(query);
            PreparedStatement ps = dbConnection.prepareStatement(query);
           
            rs = ps.executeQuery(query);//returns result set of the query, used for only select queries
        	
            if(rs.next())
            {
                return true;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return false;
	}
	 
	 public int countCandidates(int eid){
		 ResultSet rs=null;
         int countOfCandidates=-1;
         try {
            
        	String query = "SELECT COUNT(*) FROM candidates where electionid="+eid;
            PreparedStatement ps = dbConnection.prepareStatement(query);
            rs = ps.executeQuery(query);//returns result set of the query, used for only select queries
     
            while(rs.next())
            {
                 System.out.println("Number of Candidates:"+rs.getInt(1)+" in electionid= "+eid);
                 countOfCandidates = rs.getInt(1);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countOfCandidates;
	 }

	 public int countRegisteredVoters(){
		 ResultSet rs=null;
         int countOfRegisteredVoters=-1;
         try {
            
        	String query = "SELECT COUNT(*) FROM registeredvoter";
            PreparedStatement ps = dbConnection.prepareStatement(query);
            rs = ps.executeQuery(query);//returns result set of the query, used for only select queries
     
            while(rs.next())
            {
                 System.out.println("Number of Eligible(Registered) Voters"+rs.getInt(1));
                 countOfRegisteredVoters = rs.getInt(1);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countOfRegisteredVoters;
	 }

	 public int getVotersSecret(int voterID){
		 int vSecret=-1;
		 try { 
			 String query = "SELECT * FROM votercredentials where voterid="+voterID;
			 ResultSet rs= getResultOfQuery(query);
			 
			 while(rs.next())
	            {
	                 System.out.println("voterid: "+rs.getInt(1)+" secret:"+rs.getInt(2));
	                 rs.getInt(1);
	                 vSecret = rs.getInt(2);
	            }
		 	} catch (SQLException ex) {
	            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      			
		return vSecret;
	 }

     public ArrayList<Candidate> getCandidates(int electionid){
    	 ArrayList<Candidate> candidateList = new ArrayList<Candidate> ();
    	 Candidate candidate = null;
    	 
    	 try { 
			 String query = "SELECT * FROM candidate where electionid="+electionid;
			 ResultSet rs= getResultOfQuery(query);
			 
			 while(rs.next())
	            {
	                 System.out.println("candidateID: "+rs.getInt(1)+" Name:"+rs.getString(2)+" Job:"+rs.getString(3)+" BirthDate:"+rs.getDate(4)+ " electionID:"+rs.getInt(5));
	                 candidate = new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
	                 candidateList.add(candidate);
	            }
		 	} catch (SQLException ex) {
	            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      			
    	 return candidateList;
    	 
     }
     
     public String getHashOfSavedVoteForQuery(String hashOfRegCode)
	    {
	        System.out.println("inside get hash of casted vote for query");
	    
	        String vSecret= null;
	    	try 
	    	{ 
	    		String query = "select HASH_SEHREGCODE from Vote where VOTEID="+hashOfRegCode;
	    		ResultSet rs= getResultOfQuery(query);
	    			 
	    		while(rs.next())
	    	    {
	    			System.out.println("queriedVote:"+rs.getString(1));
	    	        vSecret = rs.getString(1);
	    	    }
	    	} catch (SQLException ex) 
	    	{
	    		Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	    	}
	    	      			
	    		return vSecret;
	    }
     
     public ArrayList<String> getRegCodeAndFakeRegCodeOfVoter(String email)
	    {
	        System.out.println("inside get registration codes of voter");
	        ArrayList<String> results = new ArrayList<>();
	        
	    	try 
	    	{ 
	    		String query = "select hash_regcode, hash_fakeregcode from Voter, RegisteredVoter where Voter.VoterID = RegisteredVoter.VoterID and Voter.email ="+email;
	    		ResultSet rs= getResultOfQuery(query);
	    			 
	    		while(rs.next())
	    	    {
	    			System.out.println("RegCode: "+rs.getString(1)+" FakeRegCode:"+rs.getString(2));
	    			results.add(rs.getString(1));
	    			results.add(rs.getString(2));
	    	    }
	    	} catch (SQLException ex) 
	    	{
	    		Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
	    	}
	    	      			
	    		return results;
	    }
     
}
