package td5;

import td4.SerializeDeserializeManager;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class Main {
    public static void main(String[] args) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            BigInteger publicKeyModulus = rsaPublicKey.getModulus();
            BigInteger publicKeyExponent = rsaPublicKey.getPublicExponent();

            System.out.println(publicKeyModulus);
            System.out.println(publicKeyExponent);

            RSAPublicKeySpec newKey = new RSAPublicKeySpec(publicKeyModulus, publicKeyExponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey1 = keyFactory.generatePublic(newKey);

            KeyHandler.serialize(publicKeyModulus, publicKeyExponent, "publicKey.ser");
            PublicKey publicKeyDeser =  (PublicKey) KeyHandler.deserialize("publicKey.ser", "public");

            System.out.println(publicKeyDeser);

        }catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
