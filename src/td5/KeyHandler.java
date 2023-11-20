package td5;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class KeyHandler {
    public static void serialize(BigInteger modulus, BigInteger exponent, String fileName) {
        try {
            CustomKey customKey = new CustomKey(modulus, exponent);

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(customKey);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key deserialize(String fileName, String mode) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            CustomKey customKey = (CustomKey) objectInputStream.readObject();

            if(mode.equals("public")) {
                RSAPublicKeySpec newKey = new RSAPublicKeySpec(customKey.getModulus(), customKey.getExponent());
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(newKey);
                return publicKey;
            }
            else if (mode.equals("private")) {
                RSAPrivateKeySpec newKey = new RSAPrivateKeySpec(customKey.getModulus(), customKey.getExponent());
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = keyFactory.generatePrivate(newKey);
                return privateKey;
            } else {
                throw new Exception("Must be private or public");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
