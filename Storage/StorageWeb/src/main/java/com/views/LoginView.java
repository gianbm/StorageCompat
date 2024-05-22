package com.views;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.api.schemas.LoginCreateDTO;
import com.api.schemas.ProductoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named("loginView")
@ViewScoped
public class LoginView implements Serializable{

	private LoginCreateDTO login;
	
	@PostConstruct
	public void init() {
		
		login = new LoginCreateDTO();
	}

	public LoginCreateDTO getLogin() {
		return login;
	}

	public void setLogin(LoginCreateDTO login) {
		this.login = login;
	}

	public void createLogin() throws IOException, InterruptedException {

		ObjectMapper om = new ObjectMapper();

		try {
			String opJSON = om.writeValueAsString(login);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/StorageWeb/api/v1/login"))
					.header("Content-Type", "application/json").POST(BodyPublishers.ofString(opJSON)).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			int statusCode = response.statusCode();
			System.out.println("HTTP Status Code: " + statusCode);
			if(statusCode == 200) {
				FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/StorageWeb/views/listadoproductos.xhtml");
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al iniciar la sesión Código " + statusCode));
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public void closeSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/StorageWeb/views/login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
