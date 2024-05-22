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
import com.entities.Producto;
import com.entities.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

@Named("usuarioView")
@ViewScoped
public class UsusarioView implements Serializable {


	private Usuario newUsuario;
	private Operador newOperador;
	private Cliente newCliente;
	
	private List<Operador> operadores;
	private List<Cliente> clientes;


	@PostConstruct
	public void init() {

		newUsuario = new Usuario();
		newOperador = new Operador();
		newCliente = new Cliente();
		newOperador.setUsuario(newUsuario);
		newCliente.setUsuario(newUsuario);
		
		try {
			obtenerOperadores();
			obtenerClientes();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void obtenerOperadores() throws IOException, InterruptedException {
	    try {
	        // Crear la solicitud GET
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create("http://localhost:8080/StorageWeb/api/v1/signup/operadores"))
	                .header("Content-Type", "application/json")
	                .GET()
	                .build();

	        HttpClient client = HttpClient.newHttpClient();
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        int statusCode = response.statusCode();
	        System.out.println("HTTP Status Code: " + statusCode);

	        if (statusCode == 200) {
	            String responseBody = response.body();
	            ObjectMapper objectMapper = new ObjectMapper();
	            operadores = objectMapper.readValue(responseBody, new TypeReference<List<Operador>>() {});
	            
	        } else {
	            System.out.println("Error: " + statusCode);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void obtenerClientes() throws IOException, InterruptedException {
	    try {
	        // Crear la solicitud GET
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create("http://localhost:8080/StorageWeb/api/v1/signup/clientes"))
	                .header("Content-Type", "application/json")
	                .GET()
	                .build();

	        HttpClient client = HttpClient.newHttpClient();
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        int statusCode = response.statusCode();
	        System.out.println("HTTP Status Code: " + statusCode);

	        if (statusCode == 200) {
	            String responseBody = response.body();
	            ObjectMapper objectMapper = new ObjectMapper();
	            clientes = objectMapper.readValue(responseBody, new TypeReference<List<Cliente>>() {});
	            
	        } else {
	            System.out.println("Error: " + statusCode);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
