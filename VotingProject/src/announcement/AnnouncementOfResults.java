package announcement;

/* Result details will be shown on bulletin board (Details will include the percentage of eligible voters, valid and invalid votes, 
   scores for each candidate etc.). System keys will be announced to public.*/

public class AnnouncementOfResults {
	
	
	private void updateBulletinBoard()
	{
		int numOfEligibleVoters = getNumOfEligibleVoters();
		int numOfValidVotes = getNumOfValidVotes();
		int numOfInvalidVotes = getNumOfInvalidVotes();
		
		//Güven txt dosyasýna kaydediyor.
		int scores = getScores();
		
		//private key txt dosyasýnda, iki tane. Leyla okuyup parçalayýp dosyayý boþaltýyor.
		//Leyda'dan private key'i okuma fonk. lazým.
		//Güven public key'i de bir txt dosyasýna atacak.
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
	
	//Key'leri nasýl alabilirim
	private int getSystemKeys()
	{
		return 0;
	}
}
