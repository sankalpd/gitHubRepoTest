package com.ewt.dashboardpoint.framework.password;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



/**
 * utily class for encrypting and decrypting any string
 * @author sankalp
 *
 */
public class SecurityUtil {

	private static byte[] sharedvector = {
		0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
	};

	public static String encryptText(String rawText)
	{
		String encText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		String key = "dashboardpoint";
		byte[] toEncryptArray = null;

		try
		{

			toEncryptArray =  rawText.getBytes("UTF-8");        
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));

			if(temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for(int i=temporaryKey.length;i< 24;i++)
				{                   
					keyArray[i] =  temporaryKey[index];
				}
			}        

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");            
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
			byte[] encrypted = c.doFinal(toEncryptArray);            
			encText = new String(Base64.encodeBase64(encrypted), "UTF-8"); 

		}
		catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
		{
			JOptionPane.showMessageDialog(null, NoEx);
			NoEx.printStackTrace();
		}

		return encText;        
	}
	public static String decryptText(String encText)
	{

		String rawText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		String key = "dashboardpoint";
		byte[] toEncryptArray = null;

		try
		{
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));           
			toEncryptArray =  encText.getBytes("UTF-8");
			if(temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for(int i=temporaryKey.length;i< 24;i++)
				{                  
					keyArray[i] =  temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
			byte[] decrypted = c.doFinal(Base64.decodeBase64(toEncryptArray));   

			rawText = new String(decrypted, "UTF-8");                    
		}
		catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
		{
			JOptionPane.showMessageDialog(null, NoEx);
			NoEx.printStackTrace();
		}      

		return rawText; 

	}
/*
	public static void main(String args[]){

		
		String string = encryptText("charmi");
		System.out.println("string "+string);
		
		String string1 = decryptText(string);
		System.out.println("string1 "+string1);
		//String string1 = DecryptText("T4vYDDtMjjJe4TdVvEVoKA==");
	}
*/
}

