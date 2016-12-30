package operations;


import java.math.BigInteger;
import java.util.Random;

import ellipticCurve.ECPoint;
import ellipticCurve.EllipticCurve;
import ellipticCurve.math;

public class keyPairGeneration {
	
	/**
     * Generate a random key-pair, given the elliptic curve being used.
     * 
     * @param c
     * @param rnd
     * @return
     */
    public static KeyPair generateKeyPair(EllipticCurve c, Random rnd) throws Exception {
        
        // Randomly select the private key, such that it is relatively
        // prime to p
        BigInteger p = c.getP();
        System.out.println("Order of finite field: " + p);
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(p.bitLength(), rnd);
        } while (privateKey.mod(p).compareTo(BigInteger.ZERO) == 0);
        System.out.println("Private key:" + privateKey);
        
        // Calculate the public key, k * g.
        // First, randomly generate g if it is not present in the curve.
        ECPoint g = c.getBasePoint();
        System.out.println("Base point:" + g);
        if (g == null) {
            // Randomly generate g using Koblits method.
            // The starting value of x should be random.
            BigInteger x = new BigInteger(p.bitLength(), rnd);
            g = math.koblitzProbabilistic(c, x);
            c.setBasePoint(g);
        }
        ECPoint publicKey = c.multiply(g, privateKey);
        
        System.out.println("Public key: " + publicKey);
        
        KeyPair result = new KeyPair(
                new PublicKey(c, publicKey),
                new PrivateKey(c, privateKey)
        );
        
        return result;
    }

}
