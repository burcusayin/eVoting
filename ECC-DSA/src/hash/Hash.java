package hash;

import java.util.Formatter;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;


public class Hash implements IHash {

    //@Override
	public String sha3(String message, int digestSizeBits) {
		// TODO Auto-generated method stub
	
    	String s = null;
    	
    	try{
    		DigestSHA3 md = new DigestSHA3(digestSizeBits);
    		byte[] hash = md.digest(getByteArray(message));
    		s = getHexStringByByteArray(hash);
    	} catch (IllegalArgumentException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return s;
	}
    
    
    public static byte[] getByteArray(String s) {
        return (s != null) ? s.getBytes(): null;
    }
    
    public static String getHexStringByByteArray(byte[] array) {
        if (array == null)
            return null;

        StringBuilder stringBuilder = new StringBuilder(array.length * 2);
        @SuppressWarnings("resource")
        Formatter formatter = new Formatter(stringBuilder);
        for (byte tempByte : array)
            formatter.format("%02x", tempByte);

        return stringBuilder.toString();
    }
}
