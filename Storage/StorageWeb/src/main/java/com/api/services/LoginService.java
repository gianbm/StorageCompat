package com.api.services;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.schemas.LoginCreateDTO;
import com.api.schemas.TokenDTO;
import com.api.utils.JWTservices;
import com.beans.UsuarioBeanRemote;
import com.entities.Usuario;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {

    @EJB
    private UsuarioBeanRemote userBeanRemote; 
    @Context
    private HttpServletRequest request; 

    @POST
    public Response doLogin(LoginCreateDTO requestBody) {
    	// TODO: devolver el token en la resp o como cookie, no manejar la sesión.
        Usuario user = userBeanRemote.selectUserBy(requestBody.getUsuario()); // buscamos el usuario por su correo
        if (user != null && user.getContrasenia().equals(requestBody.getPassword())) { // si el usuario es válido
            String jwt = JWTservices.generateToken(user); // generamos el token jwt
            HttpSession session = request.getSession(true); // creamos la sesión
            session.setAttribute("userLogged", user); // guardamos el usuario en la sesión
            session.setAttribute("token", jwt); // guardamos el token en la sesión

            return Response.ok()
                .entity(new TokenDTO("¡Bienvenido!", jwt)) // respondemos con un mensaje de bienvenida y el token
                .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new TokenDTO("¡Oh no! Oh no no no", "El usuario o contraseña no es correcto")) // respondemos con un error si las credenciales son incorrectas
                .build();
        }
    }
    

}
