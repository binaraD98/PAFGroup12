package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Controller.propController;





@Path("/props")

public class propServices {
	
propController props = new propController();
	
	//.................Viewing Funds...............................
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readAllPropss() {
			return props.Viewprops();
		}

}
