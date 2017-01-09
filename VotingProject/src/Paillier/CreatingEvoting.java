package Paillier;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;

public class CreatingEvoting implements ICreateElection{
	
	private BigInteger maxCountOfVoter;
	private int candidateCount;
	
	private int maxBitLengthToRepresent;
	private BigInteger encryptedResult;
	
	private final int certainty = 64;
	private int bit_len = 512;
	
	// pu => (n, g)
	// pr => (lambda, u)
	private BigInteger p, q;
	private BigInteger lambda, n, n_square, g, u;
	
	public CreatingEvoting (BigInteger maxCountOfVoter, int candidateCount) throws Exception{
		if(maxCountOfVoter.compareTo(BigInteger.ZERO) <= 0){
			throw new Exception("Maximum count of voter must be greater than zero!");
		}
		
		this.candidateCount = candidateCount;
		this.maxCountOfVoter = maxCountOfVoter;
		
		maxBitLengthToRepresent = maxCountOfVoter.bitLength();
		encryptedResult = BigInteger.ONE;
		
		Paillier();
		
	}
	
	private void Paillier(){
		// Generating p and q 
		this.p = Math.rPrime(bit_len / 2, certainty);
		this.q = Math.independentRPrime(bit_len / 2, certainty, p);
		
		// Generating lambda and u
		// lambda = lcm(p-1, q-1)
		this.lambda = Math.carmichael(p, q);
		this.n = p.multiply(q);
		this.n_square = n.multiply(n);
		// gcd(L(g^lambda mod n^2), n) must be equal 1
		do{
			g = Math.random(bit_len * 2, n_square);
			// Until gcd(L(g^lambda mod n^2), n) is equal 1
		}while(L(g.modPow(lambda, n_square)).gcd(n).intValue() != 1);
		
		// u = (L(g^lambda mod n^2))^{-1} mod n
		u = L(g.modPow(lambda, n_square)).modInverse(n);  
		
		savePr("paillierPr");
		
		
	}
	// L(x) = (x-1)/n
	private BigInteger L(BigInteger x) {        
        return x.subtract(BigInteger.ONE).divide(n);
    }
	
	private void savePr(String path){
		try{
			
			PrintStream ps = new PrintStream(new File(path));
			ps.println(this.lambda.toString(16));
			ps.println(this.u.toString(16));
			ps.close();
			
			// Removing variables of private key 
			this.lambda = null;
			this.u = null;
			
		}catch(Exception e){
			e.printStackTrace();;
		}
		
	}
	
	public BigInteger getPuN(){
		return this.n;
	}
	
	public BigInteger getPuG(){
		return this.g;
	}
	
	public int getMaxBitLength(){
		return this.maxBitLengthToRepresent;
	}
	
	public BigInteger getMaxCountOfVoter(){
		return this.maxCountOfVoter;
	}
	
	public int getCandidateCount(){
		return this.candidateCount;
	}

	

}
