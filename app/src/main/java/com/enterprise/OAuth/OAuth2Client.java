package com.enterprise.OAuth;

public class OAuth2Client {

	private final String username;
	private final String password;
	private final String clientId;
	private final String clientSecret;
	private final String site;
	
	public OAuth2Client(String username, String password, String clientId, String clientSecret, String site) {
		this.username = username;
		this.password = password;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.site = site;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getClientId() {
		return clientId;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public String getSite() {
		return site;
	}
	

}
