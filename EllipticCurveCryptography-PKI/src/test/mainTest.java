package test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import ellipticCurve.EllipticCurve;
import operations.KeyPair;
import ellipticCurve.math;
import operations.keyPairGeneration;
import operations.encryption;
import operations.decryption;
import operations.PrivateKey;

public class mainTest {
	
	public static void main(String[] args) throws Exception {
        // using NIST_P_192 to test
        EllipticCurve c = EllipticCurve.NIST_P_192;
        Random rnd = new Random(System.currentTimeMillis());
        System.out.println("rnd:" + System.currentTimeMillis());
        
       
        byte[] test = null ;
        try{
	        InputStream in = new FileInputStream("test.txt");
	        int size = in.available();
	        test = new byte[size];
	        
	        for(int i = 0; i< size ; i++){
	        	test[i] = (byte) in.read();
	        	//System.out.println("i:" + i + ", test[i]:" + test[i]);
	        }
	        in.close();
        }catch(IOException e){
        	
        	System.out.println("Exception");
        }
        //System.out.println("Hash:"+test.hashCode());
        
        // generate pair of keys
        KeyPair keys = keyPairGeneration.generateKeyPair(c, rnd);
        
       // encrypt test.txt
        byte[] cipherText = encryption.encrypt(test, keys.getPublicKey());
        System.out.println("Cipher Text Len:" + cipherText.length);
        System.out.println("0.3:" + cipherText[0] + ", 1.3:" + cipherText[1]);
        
        for(int k= 0; k< cipherText.length ; k++){
        	
        		//System.out.println(k + ":" + cipherText[k]);
        }
        
        try{
	        OutputStream out = new FileOutputStream("encryptedTest.txt");
	        PrintWriter writer = new PrintWriter("encryptedTest2.txt", "UTF-8");
	        FileOutputStream fos = new FileOutputStream("encryptedTest3");
	        fos.write(cipherText);
	        fos.close();
	        for(int i = 0; i< cipherText.length ; i++){
	        	out.write(cipherText[i]);
	        	writer.println(String.valueOf(cipherText[i]));
	        	//writer.print(" ");
	        }
	        writer.close();
	        out.close();
        }catch(IOException e){
        	
        	System.out.println("Exception");
        }
        
        
// decryption
        // get ciphertext
        byte[] test2 = null ;
        try{
	        InputStream in = new FileInputStream("encryptedTest3");
	        int size = in.available();
	        test2 = new byte[size];
	        for(int j=0; j<size ; j++){
	        	test2[j] = (byte) in.read();
	        }
	        in.close();
        }catch(IOException e){
        	
        	System.out.println("Exception");
        }
        //get private
        PrivateKey pk = new PrivateKey("privateKey");
        
        byte[] plainText = decryption.decrypt(test2, pk);
        System.out.println("Plain Text Len:" + plainText.length);
        System.out.println("0.4:" + plainText[0] + ", 1.4:" + plainText[1]);
        
        
        try{
	        OutputStream outDec = new FileOutputStream("decryptedTest.txt");
	    
	        
	        for(int i = 0; i< plainText.length ; i++){
	        	outDec.write((char)plainText[i]);
	        }
	        outDec.close();
        }catch(IOException e){
        	
        	System.out.println("Exception");
        }
      
    }

}
