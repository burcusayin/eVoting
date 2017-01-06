package Ecc;

import java.util.ArrayList;
import java.math.BigInteger;


/**
 *
 * @author 
 */
public class Curve {
    //y^2 = x^3+ax+b
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private BigInteger a;       //Curve parameter
    private BigInteger b;       //Curve parameter
    private BigInteger p;       //Curve modulus prime
    private BigInteger nEFp;    //The number of points on elliptic curve
    private Point G;            //the generator point, a point on the elliptic curve chosen for cryptographic operations
    private BigInteger n;       //Order of Point G
    private String mode;        //The defined mode of curve
    public ArrayList<Point> ellipticGroup;      //Group contains all points over elliptic curve 
    
    //Public constructor with input parameter String mode
    //Constructs curve with defined domain parameter
    public Curve(String mode) {
        this.mode = mode; 
        switch(mode){
            case "P-256": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", 16);
                p = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951");
                G = new Point(new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16), 
                            new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16),
                            a, p);
                n = new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369");
                break;
            case "P-192": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("64210519e59c80e70fa7e9ab72243049feb8deecc146b9b1", 16);
                p = new BigInteger("6277101735386680763835789423207666416083908700390324961279");
                G = new Point(new BigInteger("188da80eb03090f67cbf20eb43a18800f4ff0afd82ff1012", 16), 
                            new BigInteger("07192b95ffc8da78631011ed6b24cdd573f977a11e794811", 16),
                            a, p);
                n = new BigInteger("6277101735386680763835789423176059013767194773182842284081");
                break;
            case "P-224": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("b4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4", 16);
                p = new BigInteger("26959946667150639794667015087019630673557916260026308143510066298881");
                G = new Point(new BigInteger("b70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21", 16), 
                            new BigInteger("bd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34", 16),
                            a, p);
                n = new BigInteger("26959946667150639794667015087019625940457807714424391721682722368061");
                break;
            case "P-384": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", 16);
                p = new BigInteger("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319");
                G = new Point(new BigInteger("b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", 16), 
                            new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f", 16),
                            a, p);
                n = new BigInteger("39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643");
                break;    
            case "P-521": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", 16);
                p = new BigInteger("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151");
                G = new Point(new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16), 
                            new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16),
                            a, p);
                n = new BigInteger("6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449");
                break;                    
            default:        
                break;
        }
    }
    
    public BigInteger getA() {
        return a;
    }

    public BigInteger getP() {
        return p;
    }
    
    public Point getG(){
        return G;
    }
    
    public BigInteger getN(){
        return n;
    }
    
    private void setG(){
        
    }
    
    //Find all elliptic group of equation y^2 = x^3+ax+b
    private void setEllipticGroup(){
       ellipticGroup = new ArrayList<>();
       BigInteger y2, aCongruence, y, y3;
       BigInteger x = BigInteger.ZERO;
       n = BigInteger.ZERO;
       while (x.compareTo(this.p.subtract(BigInteger.ONE)) <= 0){
            y2 = x.pow(3).add(this.a.multiply(x)).add(this.b);
            aCongruence = y2.mod(this.p);
            for (BigInteger j = BigInteger.ONE; j.compareTo(this.p.subtract(BigInteger.ONE)) <= 0; j = j.add(BigInteger.ONE)){
                y3 = this.p.multiply(j).add(aCongruence);
                if (isPerfectSquare(y3)) {
                    y = sqrt(y3);
                    Point po = new Point(x,y,a,p);
                    if (!isPointInGroup(po)){
                        ellipticGroup.add(po);
                        n = n.add(BigInteger.ONE);
                    }
                    Point pp = new Point(x, this.p.subtract(y), a, p);
                    if (!isPointInGroup(pp)) {
                        ellipticGroup.add(pp);
                        n = n.add(BigInteger.ONE);
                    }
                    
                }
            }
            x = x.add(BigInteger.ONE);
       }
    }
    
    //Check whether point p is in elliptic group
    private boolean isPointInGroup(Point p){
        boolean found = false;
        int i = 0;
        while (!found && i<ellipticGroup.size()) {
            if (p.isEqual(ellipticGroup.get(i))) {
                found = true;
            }
            i++;
        }
        return found;
    }
    
    //Check whether the BigInteger n is perfect square
    private boolean isPerfectSquare(BigInteger n){
        BigInteger sqrt = sqrt(n);
        return isSqrt(n, sqrt);
    }

    /**
     * Computes the integer square root of a number.
     *
     * @param n  The number.
     *
     * @return  The integer square root, i.e. the largest number whose square
     *     doesn't exceed n.
     */
    private BigInteger sqrt(BigInteger n){
        if (n.signum() >= 0)
        {
            final int bitLength = n.bitLength();
            BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

            while (!isSqrt(n, root))
            {
                root = root.add(n.divide(root)).divide(TWO);
            }
            return root;
        }
        else
        {
            throw new ArithmeticException("square root of negative number");
        }
    }
    
    //Check whether the BigInteger root is square root of BigInteger n
    private boolean isSqrt(BigInteger n, BigInteger root){
        final BigInteger lowerBound = root.pow(2);
        final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
        return lowerBound.compareTo(n) <= 0
            && n.compareTo(upperBound) < 0;
    }
}