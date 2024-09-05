/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.rsashield;

/**
 *
 * @author Gecko
 */

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
public class FileHandler {

    public static void storePassword() {
        BigInteger p = askForPrime();
        BigInteger q = askForPrime();
        RSAEncryption.KeyPair keyPair = RSAEncryption.generateKeyPair(p, q);
        RSAEncryption.PublicKey publicKey = keyPair.getPublicKey();
        RSAEncryption.PrivateKey privateKey = keyPair.getPrivateKey();

        System.out.println("Public Key: " + publicKey.getKey() + ", " + publicKey.getN());
        System.out.println("Private Key: " + privateKey.getKey() + ", " + privateKey.getN());

        String password = askForPassword();
        BigInteger[] encryptedPassword = RSAEncryption.encrypt(publicKey, password);
        System.out.println("Encrypted Message: " + encryptedListToString(encryptedPassword));

        String encryptedListAsString = encryptedListToString(encryptedPassword);
        appendToFile(encryptedListAsString);
    }
    
    public static void storePassword (BigInteger p, BigInteger q, String message) {
        RSAEncryption.KeyPair keyPair = RSAEncryption.generateKeyPair(p, q);
        RSAEncryption.PublicKey publicKey = keyPair.getPublicKey();
        RSAEncryption.PrivateKey privateKey = keyPair.getPrivateKey();

        System.out.println("Public Key: " + publicKey.getKey() + ", " + publicKey.getN());
        System.out.println("Private Key: " + privateKey.getKey() + ", " + privateKey.getN());

        // String message = askForPassword();
        BigInteger[] encryptedPassword = RSAEncryption.encrypt(publicKey, message);
        System.out.println("Encrypted Message: " + encryptedListToString(encryptedPassword));

        String encryptedListAsString = encryptedListToString(encryptedPassword);
        appendToFile(encryptedListAsString);
    }

    public static String encryptedListToString(BigInteger[] encryptedMsg) {
        StringBuilder sb = new StringBuilder();
        for (BigInteger b : encryptedMsg) {
            sb.append(b.toString()).append(" ");
        }
        return sb.toString().trim();
    }

    public static void appendToFile(String content) {
        try (FileWriter fileWriter = new FileWriter("passwords.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void retrievePassword() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("passwords.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Prompt the user for input
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter integers separated by commas: ");
                String userInput = scanner.nextLine();

                // Split the input string by commas and convert each value to an integer
                String[] values = userInput.split(",");
                BigInteger[] privateKeyValues = new BigInteger[values.length];
                for (int i = 0; i < values.length; i++) {
                    privateKeyValues[i] = new BigInteger(values[i].trim());
                }

                // Convert the string to a list of integers
                String[] numbers = line.split(" ");
                BigInteger[] numbersList = new BigInteger[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    numbersList[i] = new BigInteger(numbers[i]);
                }

                RSAEncryption.PrivateKey privateKey = new RSAEncryption.PrivateKey(privateKeyValues[0], privateKeyValues[1]);
                String decryptedMsg = RSAEncryption.decrypt(privateKey, numbersList);

                System.out.println("Decrypted Message: " + decryptedMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String retrievePassword(BigInteger d, BigInteger n) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("passwords.txt"))) {
            String line;
            BigInteger[] privateKeyValues = new BigInteger[2];
            privateKeyValues[0] = d;
            privateKeyValues[1] = n;
            RSAEncryption.PrivateKey privateKey = new RSAEncryption.PrivateKey(privateKeyValues[0], privateKeyValues[1]);

            while ((line = bufferedReader.readLine()) != null) {
                // Convert the string to a list of integers
                String[] numbers = line.split(" ");
                BigInteger[] numbersList = new BigInteger[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    numbersList[i] = new BigInteger(numbers[i]);
                }

                String decryptedMsg = RSAEncryption.decrypt(privateKey, numbersList);

                System.out.println("Decrypted Message: " + decryptedMsg);
                return decryptedMsg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger askForPrime() {
        Scanner scanner = new Scanner(System.in);
        BigInteger p;
        do {
            System.out.print("Enter prime: ");
            p = scanner.nextBigInteger();
        } while (!RSAEncryption.isPrime(p));
        return p;
    }

    public static String askForPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message: ");
        return scanner.nextLine();
    }
    
}
