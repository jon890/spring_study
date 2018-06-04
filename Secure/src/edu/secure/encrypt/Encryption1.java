package edu.secure.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Encryption1 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		String password = "password";

		MessageDigest md1 = MessageDigest.getInstance("MD5");
		MessageDigest md2 = MessageDigest.getInstance("SHA-1");
		MessageDigest md3 = MessageDigest.getInstance("SHA-256");
		md1.update(password.getBytes());
		md2.update(password.getBytes());
		md3.update(password.getBytes());

		byte[] bytes1 = md1.digest();
		byte[] bytes2 = md2.digest();
		byte[] bytes3 = md3.digest();

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();

		for (int i = 0; i < bytes1.length; i++) {
			sb.append(Integer.toString((bytes1[i]), 16).substring(1));
			sb1.append(Integer.toString((bytes1[i] & 0xff) + 0x100, 16)
					.substring(1));
			sb2.append(Integer.toString((bytes2[i] & 0xff) + 0x100, 16)
					.substring(1));
			sb3.append(Integer.toString((bytes3[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		System.out.println(sb.toString());
		System.out.println("md5     : " + sb1.toString());
		System.out.println("sha-1   : " + sb2.toString());
		System.out.println("sha-256 : " + sb3.toString());
	}

}
