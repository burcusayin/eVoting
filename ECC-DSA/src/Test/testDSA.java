package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Random;

import Ecc.CheckSign;
import Ecc.Curve;
import Ecc.ECDSA;
import Ecc.Point;
import Ecc.Sign;

public class testDSA {

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    	
    	
    	Curve curve = new Curve("P-192");
    	Random rnd = new Random(System.currentTimeMillis());
        ECDSA app = new ECDSA();
        
        // pr 
        // to generate a new pr:
        //BigInteger privateKey = new BigInteger(curve.getP().bitLength(), rnd);
        //pre-defined pr
        BigInteger privateKey = new BigInteger("113030449655396724366806196427178321280749587523943565521585687101690651316050");
        
        // pu computation
        app.setPu(privateKey);
        
        // get pu points 
        Point publicKey = app.getQA();
        // System.out.println("Private key of CA': " + app.getPr());
        // System.out.println("Public key of CA: (" + publicKey.getX() + "," + publicKey.getY() + ")");
        
        
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
        
        
        
        String m = new String(test);
        // System.out.println("Test:\n " + m);
        Sign sign = new Sign(app.getPr());
        String signature = sign.signingMessage(m);
        
        // System.out.println("Message: " + m);
        // System.out.println("Signature: " + signature);
        // System.out.println("length: " + signature.length());
        
        byte[] test2 = null ;
        try{
	        InputStream in = new FileInputStream("test2.txt");
	        int size = in.available();
	        test2 = new byte[size];
	        
	        for(int i = 0; i< size ; i++){
	        	test2[i] = (byte) in.read();
	        }
	        in.close();
        }catch(IOException e){
        	
        	System.out.println("Exception");
        }
        // if test2 is not exactly same as test, checking returns false
        String m2 = new String(test2);
        CheckSign checking = new CheckSign(publicKey);
        boolean check = checking.checkSignature(m2, signature);
        System.out.println("Signature verification: " + check);
    }
}
