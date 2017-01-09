package Paillier;

import java.math.BigInteger;

public interface IPaillierResultComputation {
	
	// constructor to create a count&decrypt instance:
	// public CountAndDecrypt(CreatingEvoting e);
	
	// encrypted votes added one by one 
	public void count(BigInteger vote);
	// to initialize private key path of the reconstructed private key file is defined
	public void setPr(String pathFile) throws Exception;
	// after all votes are counted, decryption is called
	public void decrypt() throws Exception;
	// at the end of decryption result is obtained
	public void getResult(String path);
	
	

}
