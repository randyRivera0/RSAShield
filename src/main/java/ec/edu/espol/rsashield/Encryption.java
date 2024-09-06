/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.rsashield;

import java.math.BigInteger;

/**
 *
 * @author User Dell
 */
public class Encryption {
    
    private static BigInteger p;
    private static BigInteger q;
    private static RSAEncryption.KeyPair keyPair;
    private static BigInteger phi;
    private static BigInteger e;
    private static BigInteger d;
    private BigInteger n;
    private BigInteger euler;
    
    public Encryption(){
        p = new BigInteger("53");
        q = new BigInteger("61");
    }

    public static void setKeyPair(RSAEncryption.KeyPair keyPair) {
        Encryption.keyPair = keyPair;
    }

    public static RSAEncryption.KeyPair getKeyPair() {
        return keyPair;
    }

    
    
    public static void setP(BigInteger p) {
        Encryption.p = p;
    }

    public static void setQ(BigInteger q) {
        Encryption.q = q;
    }

    public static BigInteger getP() {
        return p;
    }

    public static BigInteger getQ() {
        return q;
    }

    public static BigInteger getPhi() {
        return phi;
    }

    public static void setPhi(BigInteger phi) {
        Encryption.phi = phi;
    }

    public static BigInteger getE() {
        return e;
    }

    public static BigInteger getD() {
        return d;
    }

    public static void setE(BigInteger e) {
        Encryption.e = e;
    }

    public static void setD(BigInteger d) {
        Encryption.d = d;
    }
    
}
