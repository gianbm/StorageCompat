package com.api.schemas;

public class TokenDTO {
	private String message;
    private String token;

    public TokenDTO(String message, String token) {
        this.message = message; // mensaje usuario
        this.token = token; // el token jwt
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
