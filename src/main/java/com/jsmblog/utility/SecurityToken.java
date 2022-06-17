package com.jsmblog.utility;

public class SecurityToken {
	
	public String createNewApiToken(String header, int hasingCode) {
		String token ="abcd" + header + hasingCode;
		return token;
	}

}
