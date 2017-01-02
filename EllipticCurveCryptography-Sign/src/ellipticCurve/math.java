package ellipticCurve;

import java.math.BigInteger;


import ellipticCurve.ECPoint;
import ellipticCurve.EllipticCurve;

public class math {

	public static final long AUXILIARY_CONSTANT_LONG = 1000;
    public static final BigInteger AUXILIARY_CONSTANT = BigInteger.valueOf(AUXILIARY_CONSTANT_LONG);
    
    
	/**
     * Return the encoded point from a block of byte.
     * 
     * @param block
     * @param c
     * @return 
     */
    public static ECPoint encode(byte[] block, EllipticCurve c) throws Exception {
        // pad two zero byte
        byte[] paddedBlock = new byte[block.length + 2];
        for (int i = 0; i < block.length; ++i) {
            paddedBlock[i + 2] = block[i];
        }
        return koblitzProbabilistic(c, new BigInteger(paddedBlock));
    }
    
    
    /**
     * Return the encoded block from a point.
     * 
     * @param point
     * @param c
     * @return 
     */
    public static byte[] decode(ECPoint point, EllipticCurve c) {
        return point.x.divide(AUXILIARY_CONSTANT).toByteArray();
    }
    
    /**
     * Calculate the block size of plain text in bytes.
     * 
     * This assumes that the order of g over p is very close to |c|, as the
     * recommended cofactor must be no larger than 4.
     * 
     * The chosen block size is max((bitLength(p) / 8) - 5, 1).
     * 
     * @param c
     * @return 
     */
    public static int getBlockSize(EllipticCurve c) {
        return Math.max(c.getP().bitLength() / 8 - 5, 1);
    }
    
    /**
     * Calculate the block size of cipher text given in bytes.
     * 
     * The chosen block size is (bitLength(p) / 8) + 5.
     * 
     * @param c
     * @return 
     */
    public static int getCipherTextBlockSize(EllipticCurve c) {
        return c.getP().bitLength() / 8 + 5;
    }
    
    /**
     * Pad the array of byte b so its length will be multiple of blockSize.
     * 
     * There will be at least one byte padded. The last byte will contain the
     * number of padded bytes.
     * 
     * @param b
     * @return 
     */
    public static byte[] pad(byte[] b, int blockSize) {
        int paddedLength = blockSize - (b.length % blockSize);
        byte[] padded = new byte[b.length + paddedLength];
        for (int i = 0; i < b.length; ++i) {
            padded[i] = b[i];
        }
        for (int i = 0; i < paddedLength - 1; ++i) {
            padded[b.length + i] = 0;
        }
        padded[padded.length - 1] = (byte)paddedLength;
        
        return padded;
    }
    
    /**
     * Recover the original array of byte given the padded array of byte b.
     * 
     * @param b
     * @param blockSize
     * @return 
     */
    public static byte[] unpad(byte[] b, int blockSize) {
        int paddedLength = b[b.length - 1];
        byte[] unpadded = new byte[b.length - paddedLength];
        for (int i = 0; i < unpadded.length; ++i) {
            unpadded[i] = b[i];
        }
        return unpadded;
    }
    
    /**
     * Find a point inside the curve with the x-coordinate equals
     * x * AUXILIARY_CONSTANT + k, where k is as small as possible.
     * 
     * There is a very small probability that k will be as large as the
     * AUXILIARY_CONSTANT, and this method relies on the fact. If k exceeds
     * the constant, an exception will be thrown.
     * 
     * This method works only for p = 3 (mod 4), as finding the solution to
     * the quadratic congruence is non-deterministic for p = 1 (mod 4). If
     * p equals 1 (mod 4), an exception will also be thrown.
     * 
     * Source: http://www.ams.org/journals/mcom/1987-48-177/S0025-5718-1987-0866109-5/S0025-5718-1987-0866109-5.pdf
     * 
     * @param c
     * @param x
     * @return 
     */
    public static ECPoint koblitzProbabilistic(EllipticCurve c, BigInteger x) throws Exception {
        BigInteger p = c.getP();
        
        // throw an exception if p != 3 (mod 4)
        if (!p.testBit(0) || !p.testBit(1)) {
            throw new Exception("P should be 3 (mod 4)");
        }
        BigInteger pMinusOnePerTwo = p.subtract(BigInteger.ONE).shiftRight(1);
        
        BigInteger tempX = x.multiply(AUXILIARY_CONSTANT).mod(p);
        for (long k = 0; k < AUXILIARY_CONSTANT_LONG; ++k) {
            BigInteger newX = tempX.add(BigInteger.valueOf(k));
            
            // Calculates the rhs of the elliptic curve equation, call it a
            BigInteger a = c.calculateRhs(newX);
            
            // Determine whether this value is a quadratic residue modulo p
            // It is if and only if a ^ ((p - 1) / 2) = 1 (mod p)
            if (a.modPow(pMinusOnePerTwo, p).compareTo(BigInteger.ONE) == 0) {
                // We found it! Now, the solution is y = a ^ ((p + 1) / 4)
                BigInteger y = a.modPow(p.add(BigInteger.ONE).shiftRight(2), p);
                return new ECPoint(newX.mod(p), y);
            }
        }
        
        // If we reach this point, then no point are found within the limit.
        throw new Exception("No point found within the auxiliary constant");
    }
    
    
    
    
}
