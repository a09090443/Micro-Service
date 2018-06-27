package com.zipe.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {
	public static String encrypt(String str) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(str);

		return hashedPassword;
	}
	public static Boolean checkMatch(String rawStr, String encodeStr) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder.matches(rawStr, encodeStr);
	}
}
