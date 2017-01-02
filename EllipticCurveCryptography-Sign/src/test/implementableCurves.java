package test;


import ellipticCurve.ECPoint;
import ellipticCurve.EllipticCurve;

public class implementableCurves {
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Check whether the standard curves' base points lie on the curve
        System.out.println("NIST_P_192: " + EllipticCurve.NIST_P_192.isPointInsideCurve(EllipticCurve.NIST_P_192.getBasePoint()));
        System.out.println("NIST_P_224: " + EllipticCurve.NIST_P_224.isPointInsideCurve(EllipticCurve.NIST_P_224.getBasePoint()));
        System.out.println("NIST_P_256: " + EllipticCurve.NIST_P_256.isPointInsideCurve(EllipticCurve.NIST_P_256.getBasePoint()));
        System.out.println("NIST_P_384: " + EllipticCurve.NIST_P_384.isPointInsideCurve(EllipticCurve.NIST_P_384.getBasePoint()));
        System.out.println("NIST_P_521: " + EllipticCurve.NIST_P_521.isPointInsideCurve(EllipticCurve.NIST_P_521.getBasePoint()));
        
        for (int i = 0; i < 20; ++i) {
            System.out.println("NIST_P_521 x " + i + " = " + EllipticCurve.NIST_P_521.multiply(EllipticCurve.NIST_P_521.getBasePoint(), i).toString(16));
        }
        
        // This computes (2, 4) + (5, 9) in y^2 = x^3 + x + 6 mod 11
        EllipticCurve e = new EllipticCurve(1, 6, 11);
        ECPoint p = new ECPoint(3, 5);
        ECPoint q = new ECPoint(5, 9);
        
        System.out.println(p + " + " + q + " = " + e.add(p, q));
        for (int i = 0; i < 20; ++i) {
            System.out.println(p + " x " + i + " = " + e.multiply(p, i));
        }
    }

}
