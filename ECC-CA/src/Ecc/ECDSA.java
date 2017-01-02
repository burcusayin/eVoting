/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ecc;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author User
 */
public class ECDSA {
    private BigInteger pr;   	//private key of CA
    private BigInteger n;     	//the order of the curve
    private Curve curve;   		//the elliptic curve
    private Point G;        	//the generator point, an elliptic curve domain parameter
    private Point pu;    		//public key of CA

    public ECDSA() {
        pr = BigInteger.ZERO;
        curve = new Curve("P-256");
        n = curve.getN();
        G = curve.getG();
        pu = new Point();
    }

    public BigInteger getdA() {
        return pr;
    }
    
    //Generate public key from private key
    public void setdA(BigInteger privateKey) {
        this.pr = privateKey;
        pu = G.multiplication(this.pr);
    }

    public Point getQA() {
        return pu;
    }
     
}
