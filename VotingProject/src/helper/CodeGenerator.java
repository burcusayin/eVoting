package helper;

import java.util.Random;

public class CodeGenerator {
	  //attributes
	   private int cLength;
	   private Random random;
	   private String alphabet;
		   
	   //constructor
	   public CodeGenerator(){
		   cLength = 6;
		   random = new Random();
		   alphabet = "ABCDEFGHIJKLMNOPQRESTUVWXYZabcdefgghiklmnopqrstuvwxyz1234567890";
	   }
	   //constructor
	   public CodeGenerator(int codeLenght){
		   cLength = codeLenght;
		   random = new Random();
		   alphabet = "ABCDEFGHIJKLMNOPQRESTUVWXYZabcdefgghiklmnopqrstuvwxyz1234567890";
	   }
	   
	   public String generateACode() {
		    String code = "";
		    
		    while (code.length() < cLength) {
		        int index = random.nextInt(alphabet.length());
		        String chr = "" + alphabet.charAt(index);
		        code += chr;
		    }
	    return code;
	}
	
	   public void setcLength(int cLength) {
		if(cLength>3){
			this.cLength = cLength;
		}
		
	}
        
	   
    
}

	
	
