package Paillier;

import java.math.BigInteger;

public interface ICreateElection {
	
	// public key pair => n, g
	// Get public key 
	public BigInteger getPuN();
	public BigInteger getPuG();
	
	// This returns bit lengths to represent number of voters
	public int getMaxBitLength();
	
	// This returns number of registered voters
	public BigInteger getMaxCountOfVoter();
	// This returns number of candidate
	public int getCandidateCount();

}
