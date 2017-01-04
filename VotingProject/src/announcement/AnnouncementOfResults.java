package announcement;

/* Result details will be shown on bulletin board (Details will include the percentage of eligible voters, valid and invalid votes, 
   scores for each candidate etc.). System keys will be announced to public.*/

public class AnnouncementOfResults {
	
	
	private void updateBulletinBoard()
	{
		int numOfEligibleVoters = getNumOfEligibleVoters();
		int numOfValidVotes = getNumOfValidVotes();
		int numOfInvalidVotes = getNumOfInvalidVotes();
		
		//G�ven txt dosyas�na kaydediyor.
		int scores = getScores();
		
		//private key txt dosyas�nda, iki tane. Leyla okuyup par�alay�p dosyay� bo�alt�yor.
		//Leyda'dan private key'i okuma fonk. laz�m.
		//G�ven public key'i de bir txt dosyas�na atacak.
		int systemKeys = getSystemKeys();
	}

	private int getNumOfEligibleVoters()
	{
		return 0;
	}
	
	private int getNumOfValidVotes()
	{
		return 0;
	}
	
	private int getNumOfInvalidVotes()
	{
		return 0;
	}
	
	// Score'u hangi tiple alabilirim?
	private int getScores()
	{
		return 0;
	}
	
	//Key'leri nas�l alabilirim
	private int getSystemKeys()
	{
		return 0;
	}
}
