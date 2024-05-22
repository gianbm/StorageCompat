package com.views;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.api.ignore.IgnoreType;
import com.api.schemas.ClienteDTO;
import com.api.schemas.OperadorDTO;
import com.beans.ClienteBeanRemote;
import com.beans.OperadorBeanRemote;
import com.beans.UsuarioBeanRemote;
import com.entities.Cliente;
import com.entities.Operador;
import com.entities.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

@Named("usuarioView")
@ViewScoped
public class UsusarioView implements Serializable {


	private Usuario newUsuario;
	private Operador newOperador;
	private Cliente newCliente;

	@PostConstruct
	public void init() {

		newUsuario = new Usuario();
		newOperador = new Operador();
		newCliente = new Cliente();
		newOperador.setUsuario(newUsuario);
		newCliente.setUsuario(newUsuario);
	}

	public Usuario getUsuario() {
		return newUsuario;
	}

	public void setUsuario(Usuario usuario) {
		this.newUsuario = usuario;
	}

	public Operador getOperador() {
		return newOperador;
	}

	public void setOperador(Operador operador) {
		this.newOperador = operador;
	}

	public Cliente getCliente() {
		return newCliente;
	}

	public void setCliente(Cliente cliente) {
		this.newCliente = cliente;
	}

	public void crearOperador() throws IOException, InterruptedException {

		ObjectMapper om = new ObjectMapper();

		try {
			OperadorDTO newOperadorDTO = om.convertValue(newOperador, OperadorDTO.class);
			String opJSON = om.writeValueAsString(newOperadorDTO);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/StorageWeb/api/v1/signup/operador"))
					.header("Content-Type", "application/json").POST(BodyPublishers.ofString(opJSON)).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			int statusCode = response.statusCode();
			System.out.println("HTTP Status Code: " + statusCode);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crearCliente() throws IOException, InterruptedException {

		ObjectMapper om = new ObjectMapper();

		try {
			ClienteDTO newClienteDTO = om.convertValue(newCliente, ClienteDTO.class);
			String opJSON = om.writeValueAsString(newClienteDTO);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/StorageWeb/api/v1/signup/cliente"))
					.header("Content-Type", "application/json").POST(BodyPublishers.ofString(opJSON)).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			int statusCode = response.statusCode();
			System.out.println("HTTP Status Code: " + statusCode);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
