package voting;

import controller.Controller;
import helper.DBHelper;

public class QueryCastedVote {
	
	private String hashOfRegCode;
	private String hashOfSeHregCode;
	private boolean isLoginSuccessful;
	private Controller c;
	private IVoting iVoting;
	private DBHelper db;
		
	public QueryCastedVote() throws Exception{
		
		c = Controller.getInstance();
		iVoting = c.vm;
		db = c.db;
		c.setQueriedVote(getVote());
	}



	private String getVote()
	{
		//Check if (isLoginSuccessful == true). If not, return null
		//DB'deki Vote tablosundan VoteID'si hashOfRegCode'a eþit olan satýr bulunur.
		//hash_seHregCode deðeri çekilip vote'a set edilir.
		//vote deðeri döndürülür.
			
		String vote = null;
		this.isLoginSuccessful = iVoting.isLoginSuccessful();
		if(isLoginSuccessful)
		{
			hashOfRegCode = iVoting.getHashOfRegCode();
			if(hashOfRegCode == null)
			{
				hashOfRegCode = iVoting.getHashOfFakeRegCode();
			}
			
			// DB operation
			hashOfSeHregCode = db.getHashOfSavedVoteForQuery(hashOfRegCode);
			vote = hashOfSeHregCode;
		}

		return vote;
			
	}

}
