package Test;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import Paillier.CountAndDecrypt;
import Paillier.CreatingEvoting;
import Paillier.ICreateElection;
import Paillier.IPaillierResultComputation;
import Paillier.IVote;
import Paillier.Voting;

public class mainTest {

	public static void main(String [] args) throws Exception{
		
		Random rn = new Random();
		BigInteger encryptedVote;
		int randomVote;
		BigInteger numOfVoters = new BigInteger("1000");
		
		// Creating election
		ICreateElection election = new CreatingEvoting(numOfVoters, 10);
		IVote vote = new Voting(election);
		// Voting
		try{
			
			PrintStream ps = new PrintStream(new File("votes"));
			for(int i = 0; i < numOfVoters.intValue() ; i++){
				randomVote = rn.nextInt(10);
				encryptedVote = vote.vote(randomVote);
				ps.println(encryptedVote);
			}
			ps.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		IPaillierResultComputation cd = new CountAndDecrypt(election);
		
		// Initializing private key
		cd.setPr("paillierPr");
		
		
		// Counting votes
		try{
			List<String> votes = Files.readAllLines(Paths.get("votes"), StandardCharsets.UTF_8);
			
			for( int k=0; k<votes.size(); k++){
					
				cd.count(new BigInteger(votes.get(k)));
			}
			
			
		}catch(Exception e){
			
			System.out.println("Before set private key, you must comupute and create private key file!");
		}
		
		// Decrypting counted votes
		cd.decrypt();
		cd.getResult("Result");
		
		
	}
}
