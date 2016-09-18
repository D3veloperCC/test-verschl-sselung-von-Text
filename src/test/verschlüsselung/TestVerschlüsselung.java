package test.verschlüsselung;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

public class TestVerschlüsselung {

    
    public static void main(String[] args) {
        String text;
        String VerschlüsselterText = "ha";
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte schreibe den Text der Verschlüsselt werden soll.");
        text = sc.nextLine();
        verschlüsseln(text, VerschlüsselterText);
    }
    public static void verschlüsseln(String VerschlüsselText, String Ausgabe) {
        String EndVerschlüsselt;
        try {
            
            String keyStr = "geheim";
            
            byte[] key = (keyStr).getBytes("UTF-8");
            try {
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                key = sha.digest(key);
                
                key = Arrays.copyOf(key, 16);
                SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                
                Cipher cipher;
                try {
                    cipher = Cipher.getInstance("AES");
                    cipher.init(cipher.ENCRYPT_MODE, secretKeySpec);
                    byte[] encrypted = cipher.doFinal(VerschlüsselText.getBytes());
                    
                    BASE64Encoder myEncoder = new BASE64Encoder();
                    EndVerschlüsselt = myEncoder.encode(encrypted);
                    
                    System.out.println("Ursprungstext: " + VerschlüsselText);
                    System.out.println("Verschlüsselt: " + EndVerschlüsselt);
                    
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(TestVerschlüsselung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(TestVerschlüsselung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(TestVerschlüsselung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(TestVerschlüsselung.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(TestVerschlüsselung.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }
        
    }
}
