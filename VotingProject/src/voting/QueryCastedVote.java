package voting;

public class QueryCastedVote {
	
	private String hashOfRegCode;
	
	// GUI'den giri� bilgileri al�nacak
		private boolean isLoginSuccessful() {
			//GUI'den girilmi� olan email ve regCode al�n�r.
			//regCode'un hash de�eri hesaplan�r. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yap�l�r.
			//Voter ve RegisteredVoter db tablolar� joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash de�erleri �ekilir. 
			//H(RegCode) ile bu �ekilen de�erler kar��la�t�r�l�r.
			//HErhangi birine e�itse result = true yap�l�r.
			//E�er fake code girilmi�se isCodeFake = true yap.
			//Result de�eri d�nd�r�l�r.
			boolean result = false;
			return result;
		} 
		
		private String getVote()
		{
			//DB'deki Vote tablosundan VoteID'si hashOfRegCode'a e�it olan sat�r bulunur.
			//hash_seHregCode de�eri �ekilip vote'a set edilir.
			//vote de�eri d�nd�r�l�r.
			
			String vote = null;
			return vote;
		}

}
