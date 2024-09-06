/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.rsashield;

/**
 *
 * @author Gecko
 */

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAEncryption {

    // Function to calculate the greatest common divisor (GCD) of a and b
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Function to calculate the modular inverse of e with respect to phi
    public static BigInteger modInverse(BigInteger e, BigInteger phi) {
        BigInteger d = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger x2 = BigInteger.ONE;
        BigInteger y1 = BigInteger.ONE;
        BigInteger tempPhi = phi;

        while (e.compareTo(BigInteger.ZERO) > 0) {
            BigInteger temp1 = tempPhi.divide(e);
            BigInteger temp2 = tempPhi.subtract(temp1.multiply(e));
            tempPhi = e;
            e = temp2;

            BigInteger x = x2.subtract(temp1.multiply(x1));
            BigInteger y = d.subtract(temp1.multiply(y1));

            x2 = x1;
            x1 = x;
            d = y1;
            y1 = y;
        }

        if (tempPhi.equals(BigInteger.ONE)) {
            return d.add(phi);
        }

        throw new ArithmeticException("No modular inverse exists");
    }

    // Function to check if a number is prime
    public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        if (num.equals(BigInteger.TWO) || num.equals(BigInteger.valueOf(3))) {
            return true;
        }
        if (num.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        BigInteger sqrt = num.sqrt();
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(sqrt) <= 0; i = i.add(BigInteger.TWO)) {
            if (num.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

    // Function to generate a keypair (public and private keys)
    public static KeyPair generateKeyPair(BigInteger p, BigInteger q) {
        if (!isPrime(p) || !isPrime(q)) {
            throw new IllegalArgumentException("Both numbers must be prime.");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("p and q cannot be the same.");
        }
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        Encryption.setPhi(phi);
       
        BigInteger e = getEValue(phi);
        
        /*
        SecureRandom random = new SecureRandom();
        BigInteger e;
        do {
            e = new BigInteger(phi.bitLength(), random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !gcd(e, phi).equals(BigInteger.ONE));
        */
        
        BigInteger d = modInverse(e, phi);
        

        Encryption.setE(e);
        Encryption.setD(d);
        return new KeyPair(new PublicKey(e, n), new PrivateKey(d, n));
    }
    
    public static BigInteger getEValue(BigInteger phi){
        BigInteger e = BigInteger.valueOf(2); // Start with the smallest possible value for e
        while (e.compareTo(phi) < 0) {
            if (gcd(e, phi).equals(BigInteger.ONE)) {
                // Found a valid e, use it here
                System.out.println("Selected e: " + e);
                break;
            }
            e = e.add(BigInteger.ONE); // Increment e
        }
        
        return e;
    }

    // Function to encrypt a plaintext message using the public key
    public static BigInteger[] encrypt(PublicKey pk, String plaintext) {
        BigInteger key = pk.getKey();
        BigInteger n = pk.getN();
        
        // Print the plaintext to debug
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Character -> Code Point -> BigInteger -> Encrypted");
    
        /*
        return plaintext.chars()
                        .mapToObj(c -> BigInteger.valueOf(c))
                        .map(c -> c.modPow(key, n))
                        .toArray(BigInteger[]::new);
        */
        
        return plaintext.chars()
                    .mapToObj(c -> {
                        BigInteger codePoint = BigInteger.valueOf(c); // Unicode code point
                        BigInteger encrypted = codePoint.modPow(key, n); // Encrypted value

                        // Print each step for debugging
                        System.out.println((char) c + " -> " + c + " -> " + codePoint + " -> " + encrypted);

                        return encrypted;
                    })
                    .toArray(BigInteger[]::new);
    }

    // Function to decrypt a ciphertext message using the private key
    public static String decrypt(PrivateKey pk, BigInteger[] ciphertext) {
        BigInteger key = pk.getKey();
        BigInteger n = pk.getN();
        StringBuilder plain = new StringBuilder();
        for (BigInteger c : ciphertext) {
            plain.append((char) c.modPow(key, n).intValue());
        }
        return plain.toString();
    }

    // Function to ask for a prime number
    public static BigInteger askForPrime() {
        Scanner scanner = new Scanner(System.in);
        BigInteger p;
        do {
            System.out.print("Enter prime: ");
            p = scanner.nextBigInteger();
        } while (!isPrime(p));
        return p;
    }

    // Function to ask for a message
    public static String askForPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message: ");
        return scanner.nextLine();
    }

    public static void maing(String[] args) {
        // Example usage
        BigInteger p = askForPrime();
        BigInteger q = askForPrime();
        
        KeyPair keyPair = generateKeyPair(p, q);
        
        String message = askForPassword();
        System.out.println("Original Message: " + message);
        
        BigInteger[] encrypted = encrypt(keyPair.getPublicKey(), message);
        System.out.println("Encrypted Message: ");
        for (BigInteger b : encrypted) {
            System.out.print(b + " ");
        }
        System.out.println();
        
        String decrypted = decrypt(keyPair.getPrivateKey(), encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }

    // Helper classes for RSA key representation
    public static class PublicKey {
        private final BigInteger key;
        private final BigInteger n;

        public PublicKey(BigInteger key, BigInteger n) {
            this.key = key;
            this.n = n;
        }

        public BigInteger getKey() {
            return key;
        }

        public BigInteger getN() {
            return n;
        }
        
        @Override
        public String toString() {
            return "PublicKey{" +
                   "key=" + key +
                   ", n=" + n +
                   '}';
        }
    }

    public static class PrivateKey {
        private final BigInteger key;
        private final BigInteger n;

        public PrivateKey(BigInteger key, BigInteger n) {
            this.key = key;
            this.n = n;
        }

        public BigInteger getKey() {
            return key;
        }

        public BigInteger getN() {
            return n;
        }
        
        @Override
        public String toString() {
            return "PrivateKey{" +
                   "key=" + key +
                   ", n=" + n +
                   '}';
        }
    }

    public static class KeyPair {
        private final PublicKey publicKey;
        private final PrivateKey privateKey;

        public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }
    }
}
