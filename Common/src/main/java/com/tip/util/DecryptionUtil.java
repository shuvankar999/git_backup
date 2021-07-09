package com.tip.util;

import java.util.Base64;

public class DecryptionUtil {

	 public String decrypt(String encryptedString) {
		 
		 byte[] base64decodedBytes = Base64.getDecoder().decode(encryptedString);
		 
		 String decodedPassword=null;
		 if(null!=base64decodedBytes){
			 decodedPassword= new String(base64decodedBytes);
		  }
		 return decodedPassword;
	 }
	 
	 public static String decryptPassword(String passwordToDecrypt){
		 DecryptionUtil td = new DecryptionUtil();
			return td.decrypt(passwordToDecrypt);
		}
}
