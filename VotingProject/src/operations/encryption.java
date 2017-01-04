package operations;

import java.math.BigInteger;
import java.util.Random;

import ellipticCurve.ECPoint;
import ellipticCurve.EllipticCurve;
import ellipticCurve.math;


public class encryption {
	
	/**
     * The main encryption function of ECC.
     * 
     * @param plainText
     * @param key
     * @return 
     */
	
	 public static byte[] encrypt(byte[] plainText, PublicKey key) throws Exception {
	      
	        
	        EllipticCurve c = key.getCurve();
	        ECPoint g = c.getBasePoint();
	        ECPoint publicKey = key.getKey();
	        BigInteger p = c.getP();
	        int numBits = p.bitLength();
	        int blockSize = math.getBlockSize(c);
	        System.out.println("Block size of plain text:" + blockSize);
	        int cipherTextBlockSize = math.getCipherTextBlockSize(c);
	        System.out.println("Block size of cipher text:" + cipherTextBlockSize);
	        
	        // Pad the plainText
	        byte[] padded = math.pad(plainText, blockSize);
	        System.out.println("Padded len: "+ padded.length);
	        for(int x = 0; x<padded.length ; x++)
	        	System.out.print(" " + padded[x] + " ");
	        System.out.println("");
	        // Chunk the plainText into blocks.
	        byte[][] block = new byte[padded.length / blockSize][blockSize];
	        for (int i = 0; i < block.length; ++i) {
	            for (int j = 0; j < blockSize; ++j) {
	                block[i][j] = padded[i * blockSize + j];
	            }
	        }
	        
	        // Encode each block into unique point.
	        ECPoint[] encoded = new ECPoint[block.length];
	        for (int i = 0; i < encoded.length; ++i) {
	            encoded[i] = math.encode(block[i], c);
	        }
	        
	        // Encrypt each encoded point into a pair of points:
	        // [C_1, C_2] = [kG, P_m + kP_G], where:
	        // k is a randomly generated integer such that 1 <= k < p-1,
	        // G is the base point (provided in the key),
	        // P_m is the encoded point from the plain text,
	        // P_G is the point provided in the public key.
	        ECPoint[][] encrypted = new ECPoint[block.length][2];
	        Random rnd = new Random(System.currentTimeMillis());
	        for (int i = 0; i < encrypted.length; ++i) {
	            BigInteger k;
	            do {
	                k = new BigInteger(numBits, rnd);
	            } while (k.mod(p).compareTo(BigInteger.ZERO) == 0);
	            encrypted[i][0] = c.multiply(g, k);
	            encrypted[i][1] = c.add(encoded[i], c.multiply(publicKey, k));
	        }
	        
	        // Represent the ciphertext as an array of bytes
	        byte[] cipherText = new byte[encrypted.length * cipherTextBlockSize * 4];
	        for (int i = 0; i < encrypted.length; ++i) {
	            // encrypted[0].x
	            byte[] cipher = encrypted[i][0].x.toByteArray();
	            int offset = i * cipherTextBlockSize * 4 + cipherTextBlockSize * 0 + (cipherTextBlockSize - cipher.length);
	            for (int j = 0; j < cipher.length; ++j) {
	                cipherText[j + offset] = cipher[j];
	            }
	            // encrypted[0].y
	            cipher = encrypted[i][0].y.toByteArray();
	            offset = i * cipherTextBlockSize * 4 + cipherTextBlockSize * 1 + (cipherTextBlockSize - cipher.length);
	            for (int j = 0; j < cipher.length; ++j) {
	                cipherText[j + offset] = cipher[j];
	            }
	            // encrypted[1].x
	            cipher = encrypted[i][1].x.toByteArray();
	            offset = i * cipherTextBlockSize * 4 + cipherTextBlockSize * 2 + (cipherTextBlockSize - cipher.length);
	            for (int j = 0; j < cipher.length; ++j) {
	                cipherText[j + offset] = cipher[j];
	            }
	            // encrypted[1].y
	            cipher = encrypted[i][1].y.toByteArray();
	            offset = i * cipherTextBlockSize * 4 + cipherTextBlockSize * 3 + (cipherTextBlockSize - cipher.length);
	            for (int j = 0; j < cipher.length; ++j) {
	                cipherText[j + offset] = cipher[j];
	            }
	        }
	        
	     
	        return cipherText;
	    }

}
