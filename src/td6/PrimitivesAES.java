package td6;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PrimitivesAES {
    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 42, 128);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES"); // Permet de valider le format mais on a déjà généré un format valide
        //SecretKey secret = factory.generateSecret(spec);
        return secret;
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec zeroIv() {
        byte[] iv = new byte[16];
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        if(cipher.getAlgorithm().contains("CBC")) {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        }else if(cipher.getAlgorithm().contains("ECB")) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        if(cipher.getAlgorithm().contains("CBC")) {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        }else if(cipher.getAlgorithm().contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }



    public static void main (String[] args) throws Exception {
        byte[] key1 = generateKey(128).getEncoded();
        for(byte b : key1) {
            System.out.print(String.format("%02X", b));
        }
        System.out.println();
        byte[] key2 = getKeyFromPassword("supersecret1", "du sel").getEncoded();
        for(byte b : key2) {
            System.out.print(String.format("%02X", b));
        }
        String message = "Alea Jacta Est !"; // regarder ce qui se passe si on répète le message en ECB et CBC
        IvParameterSpec iv = generateIv();
        SecretKey key3 = getKeyFromPassword("supersecret1", "du sel");
        String messageSecret = encrypt("AES/ECB/PKCS5Padding", message, key3, iv);
        System.out.println();
        byte[] messageSecretEnOctets = messageSecret.getBytes();
        for(byte b : messageSecretEnOctets) {
            System.out.print(b);
        }
        System.out.println();
        String dechiffre = decrypt("AES/ECB/PKCS5Padding", messageSecret, key3, iv);
        System.out.println(dechiffre);
    }
}

