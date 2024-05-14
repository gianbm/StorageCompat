package com.api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beans.CargoBeanRemote;
import com.entities.Cargo;


@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CargoServices {

	@EJB
	private CargoBeanRemote cargoBean;
	
	
	@GET
	@Path("/cargos")
	public Response getCargos() {
		List<Cargo> cargosAll = cargoBean.selectAll();
		List<Map<String, Object>> cargosInfo = new ArrayList<>();
		for(Cargo car : cargosAll) {
			Map<String, Object> cargoInfo = new HashMap<>();
			cargoInfo.put("Id", car.getIdCargo());
			cargoInfo.put("Nombre", car.getNombre());
			cargosInfo.add(cargoInfo);	
		}
		
		return Response.ok(cargosInfo).build();
	}
	


	
}
