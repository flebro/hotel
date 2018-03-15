package com.iia.webservices.groupa.hotel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.data.MemoryDataAccess;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

@Path("/hotels")
public class HotelService {		
	private DataAccess dataAccess = MemoryDataAccess.getInstance();

	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotelDisponible(@QueryParam("dateDeb") String dateDeb,@QueryParam("dateFin") String dateFin){
		if (dateDeb == null || dateFin== null) {
			return Response.ok().entity(dataAccess.listHotels()).build();
		} else {
			return Response.ok().entity(dataAccess.listHotelsDisponibles(LocalDateUtil.parse(dateDeb), (LocalDateUtil.parse(dateFin)))).build();
		}
	}
}
