package Paillier;

import java.math.BigInteger;

import Paillier.Math;

public class Voting implements IVote {
	
	// Public keys
	private BigInteger n, g, n_square, maxCountOfVoter;
	private int maxBitLength;
	
	private int bit_len = 512;
	private BigInteger voterCount;
	
	public Voting(ICreateElection election){
		
		this.n = election.getPuN();
		this.g= election.getPuG();
		this.n_square  = this.n.multiply(this.n);
		this.maxBitLength = election.getMaxBitLength();
		this.maxCountOfVoter = election.getMaxCountOfVoter();
		this.voterCount = BigInteger.ZERO;
	}
	
	
	public BigInteger vote(int candidateID){
	        if(voterCount.compareTo(maxCountOfVoter) > 1){
	            return null;
	        } else if(candidateID < 0) {
	            return null;
	        } else {
	            try{
	            	// 2^(candidateId * Bit length of maxCountOfVoter)
	                BigInteger encyptedVote = encrypt(BigInteger.valueOf(2).pow(candidateID * maxBitLength)); 
	                
	                voterCount = voterCount.add(BigInteger.ONE);
	                return encyptedVote;
	            } catch (Exception ex) {
	                System.out.println("Voting operation is failed : "+ex);
	                return null;
	            }
	        }
		
	}
	
	private BigInteger encrypt(BigInteger plainText) throws Exception {
        assertGreaterThanZero(plainText);
        BigInteger r = Math.random(bit_len, n);
        // c = g^m * r^n mod n^2
        return (g.modPow(plainText, n_square).multiply(r.modPow(n, n_square))).mod(n_square);      // c = g^m * r^n mod n^2
    }
	
	private void assertGreaterThanZero(BigInteger b) throws Exception {
        if (b.compareTo(BigInteger.ZERO) < 0)
        {
            throw new Exception("The number must be greater than zero.");
        }
    }
	
	

}
