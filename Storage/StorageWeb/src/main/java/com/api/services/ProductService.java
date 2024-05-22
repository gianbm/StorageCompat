package com.api.services;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.ignore.IgnoreType;
import com.api.schemas.ProductoDTO;
import com.beans.ProductoBeanRemote;
import com.entities.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/producto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
	
	@EJB
	private ProductoBeanRemote productoBean;
	
	@POST
	@Path("/new")
	public Response createProduct(ProductoDTO newProductoDTO) {
		ObjectMapper om = new ObjectMapper();
		Producto newProducto = om.convertValue(newProductoDTO, Producto.class);
		
		int exitCode = productoBean.create(newProducto);
		if(exitCode != 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.CREATED).build();

	}
	
	@GET
	@Path("/listado")
	public Response listProducts() {
		ObjectMapper om = new ObjectMapper();

		om.addMixIn(Set.class, IgnoreType.class);
		om.addMixIn(List.class, IgnoreType.class);
		
		List<Producto> productos = productoBean.selectAll();
		List<ProductoDTO> productosDB = om.convertValue(productos, new TypeReference<List<ProductoDTO>>(){});
		
		return Response.ok(productosDB).build();
	}
	
}
