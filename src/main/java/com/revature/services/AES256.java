package com.revature.services;

//Reference https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AES256 {
	
private  final String SECRET_KEY = "This is something like a public key";

private  final String SALT = "Ocean but not salted";

private  SecretKeySpec secretKey() {
	  try {
	  //key Factory class to create opaque key
 SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

 // Keyspec is java security interface
 KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
 
 //
 SecretKey tmp = factory.generateSecret(spec);
 
 SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
 

 return secretKey;
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  return null;
}

public  String encrypt(String strToEncrypt) {
	  
	  try {
		  
 byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
 
 //Vector Initializing class
 IvParameterSpec ivspec = new IvParameterSpec(iv);
 
 
 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
 
 cipher.init(Cipher.ENCRYPT_MODE, secretKey(), ivspec);

 
 return Base64.getEncoder()
     .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
 
} catch (Exception e) {
 System.out.println("Error while encrypting: " + e.toString());
}
return null;
}


public  String decrypt(String strToDecrypt) {
	    try {
	    	
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	      
	      cipher.init(Cipher.DECRYPT_MODE, secretKey(), ivspec);
	     
	      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	      
	    } catch (Exception e) {
	    	
	      System.out.println("Error while decrypting: " + e.toString());
	      
	    }
	    return null;
	  }
}