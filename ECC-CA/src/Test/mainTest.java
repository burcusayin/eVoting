package Test;

import java.math.BigInteger;
import java.util.Random;

import Ecc.Curve;
import Ecc.ECDSA;
import Ecc.Point;
import Operations.CAcheckSign;
import Operations.CAsign;

public class mainTest {

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    	
    	
    	Curve curve = new Curve("P-256");
    	Random rnd = new Random(System.currentTimeMillis());
        ECDSA app = new ECDSA();
        // pr 
        BigInteger privateKey = new BigInteger(curve.getP().bitLength(), rnd);
        // pu computation
        app.setdA(privateKey);
        // get pu points 
        Point publicKey = app.getQA();
        System.out.println("Private key of CA': " + privateKey);
        System.out.println("Public key of CA: (" + publicKey.getX() + "," + publicKey.getY() + ")");
        
        String m = "guven";
        CAsign sign = new CAsign(privateKey);
        String signature = sign.signingMessage(m);
        
        System.out.println("Message: " + m);
        System.out.println("Signature: " + signature);
        System.out.println("length: " + signature.length());
        
        CAcheckSign checking = new CAcheckSign(publicKey);
        boolean check = checking.checkSignature(m, signature);
        System.out.println("Signature verification: " + check);
    }
}
