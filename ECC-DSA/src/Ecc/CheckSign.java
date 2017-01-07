package Ecc;

import java.math.BigInteger;

import hash.Hash;
import hash.IHash;


public class CheckSign {
	
    private BigInteger n;     	//the order of the curve
    private Curve curve;    	//the elliptic curve
    private Point G;        	//the generator point, an elliptic curve domain parameter
    private Point pu;    		//public key of CA

    public CheckSign(Point publicKey) {
        curve = new Curve("P-192");
        n = curve.getN();
        G = curve.getG();
        pu = publicKey;
    }
	
	
	//For checking A's signature in message m. Signature is in hex string representation
    //Returns true if the signature is valid, returns false if it is invalid
    public boolean checkSignature(String message, String signature){
        int len = signature.length();
        Point signPoint = new Point();
        signPoint.setX(new BigInteger(signature.substring(0, len/2), 16));
        signPoint.setY(new BigInteger(signature.substring(len/2), 16));      
        return signatureVerification(message, signPoint); 
    }
	
	//Authenticate A's point signature
    //Returns true if the signature is valid, returns false if it is invalid
    private boolean signatureVerification(String m, Point signature){
        BigInteger r = signature.getX();
        BigInteger s = signature.getY();
        BigInteger e, w, u1, u2;
        if ((r.compareTo(BigInteger.ONE) >= 0) && (r.compareTo(n.subtract(BigInteger.ONE)) <= 0) && (s.compareTo(BigInteger.ONE) >= 0) && (s.compareTo(n.subtract(BigInteger.ONE)) <= 0)){
        	IHash hashing = new Hash();
            e = new BigInteger( hashing.sha3(m, 224), 16);
            //e = new BigInteger("7e16b5527c77ea58bac36dddda6f5b444f32e81b", 16);
            w = s.modInverse(n);
            u1 = (e.multiply(w)).mod(n);
            u2 = (r.multiply(w)).mod(n);
            Point x1y1 = new Point();
            x1y1 = (G.multiplication(u1)).addition(pu.multiplication(u2));
            if ((x1y1.getX().mod(n)).compareTo(r.mod(n)) == 0){
                return true;
            } else {
                System.out.println("x1 = " + x1y1.getX().mod(n) + " | " + "r(mod n) = " + r.mod(n));
                return false;
            }
        } else {
            return false;
        }
    }

}
