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
import com.api.schemas.ClienteDTO;
import com.api.schemas.OperadorDTO;
import com.api.schemas.ProductoDTO;
import com.api.schemas.UsuarioReadDTO;
import com.beans.ClienteBeanRemote;
import com.beans.OperadorBeanRemote;
import com.beans.UsuarioBeanRemote;
import com.entities.Cliente;
import com.entities.Operador;
import com.entities.Producto;
import com.entities.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/signup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignUpService {
	
	@EJB
	private UsuarioBeanRemote usuarioBean;
	
	@EJB
	private ClienteBeanRemote clienteBean;
	
	@EJB 
	private OperadorBeanRemote operadorBean;

	
	@POST
	@Path("/cliente")
	public Response createUser(ClienteDTO newClienteDTO) {
		ObjectMapper om = new ObjectMapper();
		Cliente newCliente = om.convertValue(newClienteDTO, Cliente.class);
		Usuario newUser = newCliente.getUsuario();
		
		int exitCode = usuarioBean.create(newUser); 
		newUser = usuarioBean.selectUserBy(newUser.getUsuario());
		newCliente.setUsuario(newUser);
		exitCode = clienteBean.create(newCliente);
		if(exitCode != 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
		newUser = usuarioBean.selectUserBy(newUser.getUsuario());
		
		om.addMixIn(Set.class, IgnoreType.class);
		UsuarioReadDTO usuarioRead = om.convertValue(newUser, UsuarioReadDTO.class); 
		return Response.status(Response.Status.CREATED).entity(usuarioRead).build();
		
	}
	
	@POST
	@Path("/operador")
	public Response createOp(OperadorDTO newOperadorDTO) {
		ObjectMapper om = new ObjectMapper();
		Operador newOp = om.convertValue(newOperadorDTO, Operador.class);
		Usuario newUser = newOp.getUsuario();
		
		int exitCode = usuarioBean.create(newUser);
		newUser = usuarioBean.selectUserBy(newUser.getUsuario());
		newOp.setUsuario(newUser);
		exitCode = operadorBean.create(newOp);
		if(exitCode != 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		newUser = usuarioBean.selectUserBy(newUser.getUsuario());
		
		om.addMixIn(Set.class, IgnoreType.class);
		UsuarioReadDTO usuarioRead = om.convertValue(newUser, UsuarioReadDTO.class); 
		return Response.status(Response.Status.CREATED).entity(usuarioRead).build();
		
	}
	
	@GET
	@Path("/operadores")
	public Response listOperadores() {
		ObjectMapper om = new ObjectMapper();

		om.addMixIn(Set.class, IgnoreType.class);
		om.addMixIn(List.class, IgnoreType.class);
		
		List<Operador> operadores = operadorBean.selectAll();
		List<OperadorDTO> operadoresDB = om.convertValue(operadores, new TypeReference<List<OperadorDTO>>(){});
		
		return Response.ok(operadoresDB).build();
	}
	
	@GET
	@Path("/clientes")
	public Response listClientes() {
		ObjectMapper om = new ObjectMapper();

		om.addMixIn(Set.class, IgnoreType.class);
		om.addMixIn(List.class, IgnoreType.class);
		
		List<Cliente> clientes = clienteBean.selectAll();
		List<ClienteDTO> clientesDB = om.convertValue(clientes, new TypeReference<List<ClienteDTO>>(){});
		
		return Response.ok(clientesDB).build();
	}
	
}
