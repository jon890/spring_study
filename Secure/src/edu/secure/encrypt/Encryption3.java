package edu.secure.encrypt;


public class Encryption3 {
	public static void main(String[] args) {
		String password = "password";
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String ep = bc.encode(password);
		
		System.out.println(ep);
		System.out.println(bc.matches(password, ep));
	}
	
}
