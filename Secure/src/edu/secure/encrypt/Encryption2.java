package edu.secure.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

public class Encryption2 {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String password = "password";
		
		AES256Util aes = AES256Util.getInstance();
		String pa = aes.encrypt(password);
		String en = aes.decrypt(pa);
		System.out.println(pa);
		System.out.println(en);
	}
}
