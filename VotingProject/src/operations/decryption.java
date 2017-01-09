
package operations;

import java.math.BigInteger;

import ellipticCurve.ECPoint;
import ellipticCurve.EllipticCurve;
import ellipticCurve.math;

public class decryption {
	
	/**
     * The main decryption function of ECC.
     * 
     * @param cipherText
     * @param key
     * @return 
     */
    public static byte[] decrypt(byte[] cipherText, PrivateKey key) throws Exception {
        
        EllipticCurve c = key.getCurve();
        ECPoint g = c.getBasePoint();
        BigInteger privateKey = key.getKey();
        BigInteger p = c.getP();
        int numBits = p.bitLength();
        int blockSize = math.getBlockSize(c);
        int cipherTextBlockSize = math.getCipherTextBlockSize(c);
        
        // Chunk the cipherText into blocks.
        if (cipherText.length % cipherTextBlockSize != 0 || (cipherText.length / cipherTextBlockSize) % 4 != 0) {
            throw new Exception("The length of the cipher text is not valid");
        }
        byte block[][] = new byte[cipherText.length / cipherTextBlockSize][cipherTextBlockSize];
        for (int i = 0; i < block.length; ++i) {
            for (int j = 0; j < cipherTextBlockSize; ++j) {
                block[i][j] = cipherText[i * cipherTextBlockSize + j];
            }
        }
        
        // Calculate the encoded point
        // P_m = C_2 - kC_1, where:
        // [C_1, C_2] is the ciphertext,
        // k is the private key.
        ECPoint encoded[] = new ECPoint[block.length / 4];
        for (int i = 0; i < block.length; i += 4) {
            ECPoint c1 = new ECPoint(new BigInteger(block[i]), new BigInteger(block[i + 1]));
            ECPoint c2 = new ECPoint(new BigInteger(block[i + 2]), new BigInteger(block[i + 3]));
            encoded[i / 4] = c.subtract(c2, c.multiply(c1, privateKey));
        }
        
        // Decode the encoded point
        byte plainText[] = new byte[encoded.length * blockSize];
        for (int i = 0; i < encoded.length; ++i) {
            byte decoded[] = math.decode(encoded[i], c);
            for (int j = Math.max(blockSize - decoded.length, 0); j < blockSize; ++j) {
                plainText[i * blockSize + j] = decoded[j + decoded.length - blockSize];
            }
        }
        plainText = math.unpad(plainText, blockSize);
        
        return plainText;
    }

}
