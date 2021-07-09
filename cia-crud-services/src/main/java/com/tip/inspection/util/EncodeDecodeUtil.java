package com.tip.inspection.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tip.inspection.model.Keys;





/**
 * @author Shuvankar Paul Created on Jan 3, 2018
 * 
 */
public class EncodeDecodeUtil {
	
	static final Logger logger = LoggerFactory.getLogger(EncodeDecodeUtil.class);
	
	public static Keys buildKeyPair(String ssoId){
		final int keySize = 2048;
		SecureRandom sr;
		Keys keys = new Keys();
		try {
			sr = SecureRandom.getInstance(CommonConstatns.RANDOM_ALGORITHM);
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CommonConstatns.KEY_ALGORITHM);
			keyPairGenerator.initialize(keySize, sr);
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			String publicKeyAsString = publicKeyToString(keyPair.getPublic());
	        String privKeyAsString = privateKeyToString(keyPair.getPrivate());
			keys.setPublicK(publicKeyAsString);
			keys.setPrivateK(privKeyAsString);
			keys.setSsoId(ssoId);
		} catch (NoSuchAlgorithmException e) {
			logger.error("An error occurred while building key pair :"+e);
		} catch (Exception e1) {
			logger.error("An error occurred while building key pair :"+e1);
		}
		
		return keys;

	}

	public static byte[] encrypt(String publicKeyAsString, String message){

		PublicKey publicKey = stringToPublicKey(publicKeyAsString);
		Cipher cipher;
		byte[] cipherText = null;
		try {
			cipher = Cipher.getInstance(CommonConstatns.KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherText = cipher.doFinal(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			logger.error("An error occurred while encrypting text :"+e);
		} catch (NoSuchPaddingException e1) {
			logger.error("An error occurred while encrypting text :"+e1);
		} catch (IllegalBlockSizeException e2) {
			logger.error("An error occurred while encrypting text :"+e2);
		} catch (BadPaddingException e3) {
			logger.error("An error occurred while encrypting text :"+e3);
		} catch (InvalidKeyException e4) {
			logger.error("An error occurred while encrypting text :"+e4);
		}
		return cipherText;
	}

	public static String decrypt(String privKeyAsString, String encryptedString){
		logger.info("Decryption initiated...");
		logger.info("PrivateKeyAsString--"+privKeyAsString);
		PrivateKey privateKey = stringToPrivateKey(privKeyAsString);
		byte[] encrypted = base64(encryptedString);
		Cipher cipher;
		String decyptedText = null;
		try {
			cipher = Cipher.getInstance(CommonConstatns.KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decypted = cipher.doFinal(encrypted);
			decyptedText = new String(decypted);
		} catch (InvalidKeyException e) {
			logger.error("An error occurred while decrypting cipher text :"+e);
		} catch (IllegalBlockSizeException e1) {
			logger.error("An error occurred while decrypting cipher text :"+e1);
		} catch (BadPaddingException e2) {
			logger.error("An error occurred while decrypting cipher text :"+e2);
		} catch (NoSuchAlgorithmException e3) {
			logger.error("An error occurred while decrypting cipher text :"+e3);
		} catch (NoSuchPaddingException e4) {
			logger.error("An error occurred while decrypting cipher text :"+e4);
		}

		return decyptedText;
	}

	public static String base64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}

	public static String privateKeyToString(PrivateKey privateKey) {

		return base64(privateKey.getEncoded());
	}

	public static PrivateKey stringToPrivateKey(String privateKeyAsString) {

		byte[] privateKeyBytes = base64(privateKeyAsString);
		PrivateKey privateKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(CommonConstatns.KEY_ALGORITHM);
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			privateKey = keyFactory.generatePrivate(privateKeySpec);
		} catch (NoSuchAlgorithmException e) {
			logger.error("An error occurred while converting string to privatekey :"+e);
		} catch (InvalidKeySpecException e1) {
			logger.error("An error occurred while converting string to privatekey :"+e1);
		}
		return privateKey;
	}

	public static String publicKeyToString(PublicKey publicKey) {
		return base64(publicKey.getEncoded());
	}

	public static PublicKey stringToPublicKey(String publicKeyAsString) {

		byte[] publicKeyBytes = base64(publicKeyAsString);
		PublicKey publicKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(CommonConstatns.KEY_ALGORITHM);
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			publicKey = keyFactory.generatePublic(publicKeySpec);

		} catch (NoSuchAlgorithmException e) {
			logger.error("An error occurred while converting string to public key :"+e);
		} catch (InvalidKeySpecException e1) {
			logger.error("An error occurred while converting string to public key :"+e1);
		}
		return publicKey;
	}
	
	private EncodeDecodeUtil() {}
}
