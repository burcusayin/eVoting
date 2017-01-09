package Paillier;

import java.math.BigInteger;
import java.util.Random;


public final class Math {


    public static BigInteger random(int bitLength){
        return new BigInteger(bitLength, new Random());
    }

    public static BigInteger random(int bitLength, BigInteger lowerBound){
        BigInteger rand;
        do
        {
            rand = random(bitLength);
        }
        while (rand.compareTo(lowerBound) >= 0);
        return rand;
    }

    public static BigInteger rPrime(int bitLength, int certainty){
        return new BigInteger(bitLength, certainty, new Random());

        // certainty
    }

    public static BigInteger independentRPrime(int bitLength, int certainty, BigInteger base){
        BigInteger iPrime;
        do{
            iPrime = rPrime(bitLength, certainty);
        }while (iPrime.compareTo(base) == 0 || !areIndependentEachOther(base, iPrime));
        return iPrime;
    }
    public static boolean areIndependentEachOther(BigInteger p, BigInteger q) {      // guarantees that p and q are of equal length.
        return ((p.multiply(q)).gcd(eulerPhi(p,q)).intValue() == 1);
    }

    public static BigInteger eulerPhi(BigInteger p) {       // Euler's totient function
        return p.subtract(BigInteger.ONE);
    }

    public static BigInteger eulerPhi(BigInteger p, BigInteger q) {     // Euler's totient function
        return eulerPhi(p).multiply(eulerPhi(q));
    }

    public static BigInteger lcm(BigInteger p, BigInteger q) {
        return p.multiply(q).divide(p.gcd(q));
    }

    public static BigInteger carmichael(BigInteger p, BigInteger q) {
        return lcm(eulerPhi(p), eulerPhi(q));
    }

}
