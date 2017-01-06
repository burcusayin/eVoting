package Paillier;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CountAndDecrypt implements IPaillierResultComputation{
	
	
	private BigInteger lambda;
	private BigInteger u;
	private BigInteger n;
	private BigInteger n_square;
	private BigInteger encryptedResult = BigInteger.ONE;
	private BigInteger voterCount = BigInteger.ZERO;
	private BigInteger decryptedResult = BigInteger.ONE;
	private Boolean countingOpen = false;
	private ICreateElection election;
	
	public CountAndDecrypt(ICreateElection e){
		
		this.n = e.getPuN();
		this.n_square = this.n.multiply(this.n);
		this.election = e; 
	}
	
	
	// Sum encrypted votes
	public void count(BigInteger vote){

		this.encryptedResult = this.encryptedResult.multiply(vote);
		this.voterCount = this.voterCount.add(BigInteger.ONE);
	}
	
	// Reading and initializing private key pair
	public void setPr(String pathFile) throws Exception{
		try{
			List<String> lines = Files.readAllLines(Paths.get(pathFile), StandardCharsets.UTF_8);
			
			this.lambda = new BigInteger(lines.get(0), 16);
			this.u = new BigInteger(lines.get(1), 16);
			
		}catch(Exception e){
			
			System.out.println("Before set private key, you must comupute and create private key file!");
		}
		

		
	}
	
	// Decrypt 
	public void decrypt() throws Exception {
		try{
	        assertGreaterThanZero(this.encryptedResult);
	        // L(c^lambda mod n^2) * u mod n, where L(x) = (x-1)/n
	        this.decryptedResult =  this.encryptedResult.modPow(this.lambda, this.n_square).subtract(BigInteger.ONE).divide(this.n).multiply(this.u).mod(this.n);       
		}catch(Exception ex){
			System.out.println("Before count, private key must be set!" + ex);
		}
	}
	
	private void assertGreaterThanZero(BigInteger b) throws Exception {
        if (b.compareTo(BigInteger.ZERO) < 0)
        {
            throw new Exception("The number must be greater than zero.");
        }
    }
	
	// Computing election result
	public void getResult(String path){
		
		try{
			PrintStream ps = new PrintStream(new File(path));
			int candidateCount = election.getCandidateCount();
			int maxBitLengthToRepresent = election.getMaxBitLength();
			BigInteger p = this.decryptedResult;
			 String result = " Valid vote count : "+voterCount+
		                "\n Candidate count : "+candidateCount+
		                "\n *****__RESULTS__*****";
			 ps.println(result);
			for(int i = 0;i < candidateCount; i++) {
	            result = "Candidate " + i + " => "+ p.and(BigInteger.ONE.shiftLeft(maxBitLengthToRepresent).subtract(BigInteger.ONE)).toString();
	            p = p.shiftRight(maxBitLengthToRepresent);
	            ps.println(result);
	        }
		}catch(Exception e){
			System.out.println("Result computation is failed: " + e);
		}
		
	}


	
	

}
