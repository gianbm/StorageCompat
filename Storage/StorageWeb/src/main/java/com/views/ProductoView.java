package com.views;


import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.net.http.HttpRequest.BodyPublishers;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


import com.api.schemas.ProductoDTO;
import com.entities.Producto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named("productoView")
@ViewScoped
public class ProductoView implements Serializable{
	
	private Producto newProducto;
	private List<Producto> listProducts;

	
	@PostConstruct
	public void init() {

		newProducto = new Producto();

	       
		try {
			obtenerProductos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public Producto getProducto() {
		return newProducto;
	}

	public void setProducto(Producto newProducto) {
		this.newProducto = newProducto;
	}

	
	public void crearProducto() throws IOException, InterruptedException {

		ObjectMapper om = new ObjectMapper();

		try {
			ProductoDTO newProcutoDTO = om.convertValue(newProducto, ProductoDTO.class);
			String opJSON = om.writeValueAsString(newProcutoDTO);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/StorageWeb/api/v1/producto/new"))
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
	
	
	public void obtenerProductos() throws IOException, InterruptedException {
	    try {
	        // Crear la solicitud GET
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create("http://localhost:8080/StorageWeb/api/v1/producto/listado"))
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
	            listProducts = objectMapper.readValue(responseBody, new TypeReference<List<Producto>>() {});
	            
	        } else {
	            System.out.println("Error: " + statusCode);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<Producto> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<Producto> listProducts) {
		this.listProducts = listProducts;
	}
	

}
