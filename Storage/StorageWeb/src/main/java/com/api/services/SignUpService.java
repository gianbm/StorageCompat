package com.api.services;

import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.ignore.IgnoreType;
import com.api.schemas.ClienteDTO;
import com.api.schemas.UsuarioReadDTO;
import com.beans.ClienteBeanRemote;
import com.beans.OperadorBeanRemote;
import com.beans.UsuarioBeanRemote;
import com.entities.Cliente;
import com.entities.Usuario;
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
		System.out.println(newClienteDTO);
		ObjectMapper om = new ObjectMapper();
		Cliente newCliente = om.convertValue(newClienteDTO, Cliente.class);
		Usuario newUser = newCliente.getUsuario();
		int exitCode = usuarioBean.create(newUser); 
		exitCode = clienteBean.create(newCliente);
		if(exitCode != 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		newUser = usuarioBean.selectUserBy(newUser.getUsuario());
		
		om.addMixIn(Set.class, IgnoreType.class);
		UsuarioReadDTO usuarioRead = om.convertValue(newUser, UsuarioReadDTO.class); 
		return Response.status(Response.Status.CREATED).entity(usuarioRead).build();
		
	}
}
