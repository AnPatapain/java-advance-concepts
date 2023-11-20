package td6;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
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

    public static List<String> getPassAndSalts(String filepath) {
        File file = new File(filepath);
        List<String> passAndSalts = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            String line;
            int count = 0;
            while((line = reader.readLine()) != null && count < 1000 ) {
                passAndSalts.add(line);
                count++;
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return passAndSalts;
    }

    public static SecretKey findSecretKey(String password, String salt, String pass_stagaire_hashed) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey secretKey = getKeyFromPassword(password, salt);
        IvParameterSpec iv = generateIv();
        String sangoku_hashed = encrypt("AES/ECB/PKCS5Padding", "sangoku", secretKey, iv);
        if(sangoku_hashed.equals(pass_stagaire_hashed)) {
            return secretKey;
        }
        return null;
    }
    public static void main(String[] args) throws Exception{
        List<String> passAndSalts = getPassAndSalts("C:\\Users\\NGUYEN KE AN\\Documents\\insa\\semester-7\\java-advance\\java-avance-tds\\src\\td6\\passwords.txt");
        String pass_stagaire_hashed = "zyLj+eqxjzTh9eEz/Ga4Lw==";
        AtomicReference<SecretKey> detected_secretKey = new AtomicReference<>(null);

//        int numThreads = (Runtime.getRuntime().availableProcessors() > 10) ? 10 : 5;
        int numThreads = 10;
        List<Thread> threads = new ArrayList<>();
        AtomicBoolean isKeyDetected = new AtomicBoolean(false);
        int chunkedSize = passAndSalts.size() / numThreads;

        System.out.println("Start brute force by 10 threads");
        for(int i = 0; i < numThreads; i++) {
            int start = i*chunkedSize;
            int end = start + chunkedSize - 1;
            Thread thread = new Thread(() -> {
                if(isKeyDetected.get()) return;

                for(int j = start; j <= end; j++) {
                    for(String salt : passAndSalts) {
                        try {
                            SecretKey secretKey = findSecretKey(passAndSalts.get(j), salt, pass_stagaire_hashed);
                            if (secretKey != null) {
                                detected_secretKey.set(secretKey);
                                isKeyDetected.set(true);
                                return;
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for(Thread thread : threads) {
            thread.join();
        }

        System.out.print("Secret Key detected: ");
        for(byte b : detected_secretKey.get().getEncoded()) {
            System.out.print(String.format("%02X", b));
        }
        System.out.println();

        String cible = "XLtXaPNv67TJqQ0d8xCiMR7oyVumZsn4nnVv3P/W4ZA=";
        String password_original = decrypt("AES/ECB/PKCS5Padding", cible, detected_secretKey.get(), null);
        System.out.println("Password of bnguyen: " + password_original);
    }
}
