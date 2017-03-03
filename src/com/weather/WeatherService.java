package com.weather;


//import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.owlike.genson.stream.JsonType;

@Path("/historical")
public class WeatherService {
	
	
	
	
@GET
@Produces({MediaType.APPLICATION_JSON})
public List<WeatherDate> getWeatherDate() throws Exception 
{
	WeatherDao w=new WeatherDao();
	//System.out.println(w.getAllDates());
	return w.getAllDates();
}


@GET
@Path("/{DATE}")
@Produces(MediaType.APPLICATION_JSON) 
public Response getWeatherDetails(@PathParam("DATE") String DATE) throws Exception{ 
	//System.out.println(DATE);
	WeatherDao w=new WeatherDao();
	System.out.println(DATE);
	Weather wee=w.getWeatherByDate(DATE);
	
	if(wee.getDate()==null)
		return Response.status(Response.Status.NOT_FOUND).build();
		
	
	else
		return Response.status(Response.Status.OK).entity(wee).build();
	

}

@POST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public Response addWeather(@FormParam("DATE") String DATE, 
	      @FormParam("TMAX") double TMAX, 
	      @FormParam("TMIN") double TMIN,
	      @Context HttpServletResponse servletResponse) throws Exception
	      { 
		 
	      WeatherDao w=new WeatherDao();
	      Weather weather = new Weather();
	      weather.setDate(DATE);
	      weather.setTMax(TMAX);
	      weather.setTMin(TMIN);
	      System.out.println(JsonType.valueOf(weather.getDate()));
	      
	      int result=w.addWeather(weather);
	      System.out.println(result);
	      if(result==1)
	    	  return Response.status(Response.Status.CREATED).entity(weather.getDate()).build();
	      else
	    	return Response.status(Response.Status.BAD_REQUEST).build();
	      
	   }  




@DELETE 
@Path("/{DATE}") 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteUser(@PathParam("DATE") String DATE) throws Exception{ 
	WeatherDao w=new WeatherDao();
   int result=w.deleteWeather(DATE);
   if(result == 1)
      return "Successfully deleted"; 
   
   else
	   return "Deletion not successful";

}


}