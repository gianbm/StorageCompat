package com.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1/")
public class ApiConfiguration extends Application{
	
	public ApiConfiguration() {
		
	}
}
