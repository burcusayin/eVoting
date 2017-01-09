package voting;

public interface IVoting {
	
	public int getNumberOfCastedVotes();
	public boolean isLoginSuccessful();
	public String getHashOfRegCode();
	public String getHashOfFakeRegCode();
}
