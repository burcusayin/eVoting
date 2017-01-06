package voting;

public class QueryCastedVote {
	
	private String hashOfRegCode;
	
	// GUI'den giriþ bilgileri alýnacak
		private boolean isLoginSuccessful() {
			//GUI'den girilmiþ olan email ve regCode alýnýr.
			//regCode'un hash deðeri hesaplanýr. H(RegCode) diyelim. hashOfRegCode = H(RegCode) yapýlýr.
			//Voter ve RegisteredVoter db tablolarý joinlenip, girilen emaile ait regCode'un ve fakeRegCode'un hash deðerleri çekilir. 
			//H(RegCode) ile bu çekilen deðerler karþýlaþtýrýlýr.
			//HErhangi birine eþitse result = true yapýlýr.
			//Eðer fake code girilmiþse isCodeFake = true yap.
			//Result deðeri döndürülür.
			boolean result = false;
			return result;
		} 
		
		private String getVote()
		{
			//DB'deki Vote tablosundan VoteID'si hashOfRegCode'a eþit olan satýr bulunur.
			//hash_seHregCode deðeri çekilip vote'a set edilir.
			//vote deðeri döndürülür.
			
			String vote = null;
			return vote;
		}

}
