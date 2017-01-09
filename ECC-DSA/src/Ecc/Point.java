/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ecc;

import java.math.BigInteger;

/**
 *
 * @author User
 */
public class Point {
    private BigInteger x;       //The value of x-absis
    private BigInteger y;       //The value of y-ordinate
    private BigInteger a;       //A domain parameter of elliptic curve contains this point
    private BigInteger p;       //A domain parameter of elliptic curve contains this point
    private boolean infinity;   
    public static Point O = new Point(true);
    
    //Default constructor for point
    public Point() {
        this.x = BigInteger.ZERO; 
        this.y = BigInteger.ZERO; 
        this.infinity = false;
    }
    
    //Contructs a point of infinity named O
    public Point(boolean infinity) {
        this.x = BigInteger.ZERO;
        this.y = BigInteger.ZERO; 
        this.infinity = infinity;
    }
    
    //Constructs a point with defined parameter
    public Point(BigInteger x, BigInteger y, BigInteger a, BigInteger p) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.p = p;
        this.infinity = false;
    }
    
    //Check wheter this point is point O
    public boolean isInfinity(){
        return this.infinity;
    }
    
    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getA() {
        return a;
    }
    
    public BigInteger getP() {
        return p;
    }
    
    public Point copy(){
        Point r = new Point(this.x, this.y, this.a, this.p);
        return r;
    }
    
    //Returns the inverse of point
    public Point inverse(){
        Point r = new Point(this.x, this.y.negate().mod(p), this.a, this.p);
        return r;
    }
    
    //Check whether the point q is equal to this point 
    public boolean isEqual(Point q){
        if ((this.x.compareTo(q.getX()) == 0) && (this.y.compareTo(q.getY()) == 0) && (this.infinity == q.isInfinity())){
            return true;
        } else {
            return false;
        }
    }
    
    //Returns point result of addition between the point and another point
    public Point addition(Point q){
        if (q.isEqual(O)){
            return this.copy();
        } else if (this.isEqual(O)){
            return q;
        } else if (this.inverse().isEqual(q)){
            return O;
        } else if (this.isEqual(q)){
            return this.doubling();
        } else if (this.x.compareTo(q.getX()) == 0){
            return O;
        } else {
            BigInteger lambda = ((this.y.subtract(q.getY())).multiply((this.x.subtract(q.getX())).modInverse(p))).mod(p);  //Calculate the slope of line
            BigInteger _x = (lambda.pow(2).subtract(this.x)).subtract(q.getX());
            BigInteger _y = (lambda.multiply(this.x.subtract(_x))).subtract(this.y) ;
            Point r = new Point(_x.mod(p), _y.mod(p), this.a, this.p);
            return r;
        }
    }
    
    //Returns point result of subtraction between the point and aother point. P +(-Q)
    public Point subtraction(Point q){
        Point r = new Point();
        r = this.addition(q.inverse());
        return r;
    }
    
    //Returns point result of addition between the point and itself
    public Point doubling(){
        if (this.y.compareTo(BigInteger.ZERO) == 0){
            return O;
        } else {
            BigInteger lambda = ((this.x.pow(2).multiply(BigInteger.valueOf(3))).add(this.a)).multiply((this.y.multiply(BigInteger.valueOf(2))).modInverse(p));  //Menghitung gradien garis
            BigInteger _x = (lambda.pow(2)).subtract(this.x.multiply(BigInteger.valueOf(2))) ;
            BigInteger _y = (lambda.multiply(this.x.subtract(_x))).subtract(this.y) ;
            Point r = new Point(_x.mod(p), _y.mod(p), this.a, this.p);            
            return r;
        }
    }
    
    //Returns point result of addition between the point and itself for k-1 times
    public Point iteration(BigInteger k){
        Point r = this.copy();
        for (BigInteger i=BigInteger.ONE; i.compareTo(k.subtract(BigInteger.ONE)) == -1; i = i.add(BigInteger.ONE)){
            r.addition(this);
        }
        return r;
    }
    
    //Returns point result of multiplication between the point and scalar k
    //The point multiplication is obtained by rounding two basic elliptic kurve:
    //1. Point Addition (P + Q = R)
    //2. Point Doubling (2P = R)
    public Point multiplication(BigInteger k){
        Point r = new Point();
        if (k.compareTo(BigInteger.ZERO) == 0){
            return O;
        }
        if (k.compareTo(BigInteger.ONE) == 0){
            return this.copy();
        } else if (k.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ONE) == 0) {
            r = this.addition(this.multiplication(k.subtract(BigInteger.ONE)));
            return r;
        } else {
            Point temp = this.doubling();
            r = temp.multiplication(k.divide(BigInteger.valueOf(2)));
            return r;
        }
    }
    
    //Returns hex string representation of point
    public String toHexString(){
        String r = x.toString(16) + y.toString(16);
        return r;
    }
}
