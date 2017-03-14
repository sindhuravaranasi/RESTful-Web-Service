package com.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.owlike.genson.stream.JsonType;

@Path("/api")
public class WeatherService {
	
	
	
	
@GET
@Path("/historical")
@Produces({MediaType.APPLICATION_JSON})
public List<WeatherPojo> getWeatherDate() throws Exception 
{
	WeatherDao w=new WeatherDao();
	//System.out.println(w.getAllDates());
	return w.getAllDates();
}


@GET
@Path("/historical/{DATE}")
@Produces(MediaType.APPLICATION_JSON) 
public Response getWeatherDetails(@PathParam("DATE") String DATE) throws Exception{ 
	//System.out.println(DATE);
	WeatherDao w=new WeatherDao();
	//System.out.println(DATE);
	Weather wee=w.getWeatherByDate(DATE);
	System.out.println(wee);
	//if(wee.getDATE()==null)
	if(wee.DATE==null)
		return Response.status(Response.Status.NOT_FOUND).build();
		
	
	else
		return Response.status(Response.Status.OK).entity(wee).build();
	

}

@POST
@Path("/historical")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response addWeather(/*@FormParam("DATE") String DATE, 
	      @FormParam("TMAX") double TMAX, 
	      @FormParam("TMIN") double TMIN,
	      @Context HttpServletResponse servletResponse*/ Weather weather) throws Exception
	      { 
		 
	      WeatherDao w=new WeatherDao();
	      //Weather weather = new Weather();
	      WeatherPojo wp=new WeatherPojo();
	      
	      wp.DATE=weather.DATE;
	    
	    /* weather.setDATE(DATE);
	      weather.setTMAX(TMAX);
	      weather.setTMIN(TMIN);*/
	      //System.out.println(JsonType.valueOf(weather.getDATE()));
	      
	      int result=w.addWeather(weather);
	      //System.out.println(result);
	      if(result==1)
	    	  return Response.status(Response.Status.CREATED).entity(wp).build();
	      else
	    	return Response.status(Response.Status.BAD_REQUEST).build();
	      
	   }  




@DELETE 
@Path("/historical/{DATE}") 
@Produces(MediaType.TEXT_PLAIN) 
public Response deleteUser(@PathParam("DATE") String DATE) throws Exception{ 
	WeatherDao w=new WeatherDao();
   int result=w.deleteWeather(DATE);
   if(result == 1)
	   return Response.status(204).build();
   else
	   return Response.status(Response.Status.NOT_FOUND).build();

}

public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}




//@SuppressWarnings("unused")
@GET
@Path("/forecast/{dat}")
@Produces(MediaType.APPLICATION_JSON)
public Response forcastClimate(@PathParam("dat") int dat) {
	System.out.println(" "+dat);
	try{
		List<Weather> lt=new ArrayList<Weather>();
		String temp = Integer.toString(dat);
		char c[]=temp.toCharArray();
		String dt=""+c[0]+""+c[1]+""+c[2]+""+c[3]+"-"+c[4]+""+c[5]+"-"+c[6]+""+c[7];
		java.sql.Date dd = java.sql.Date.valueOf( dt );
		for(int i=0;i<7;i++)
		{

			String urrl="https://api.darksky.net/forecast/78b2848aa646a9c4c2556b870723478c/39.103118,-84.512020,"+dd+"T12:00:00";
			JSONObject json=readJsonFromUrl(urrl);
			JSONObject jj=json.getJSONObject("daily");
			Weather d=new Weather();
			JSONArray jr4= jj.getJSONArray("data");
			JSONObject jo=jr4.getJSONObject(0);
			d.DATE=Integer.toString(dat);
			//d.setDATE(Integer.toString(dat));
	    	double tmax=jo.getLong("temperatureMax");
			//d.setTMAX(tmax);
			d.TMAX=tmax;
	    	double tmin=jo.getLong("temperatureMin");
	    	d.TMIN=tmin;
	    	//d.setTMIN(tmin);
	    	lt.add(d);
			dat++;
			dd = new Date(dd.getTime() + (1000 * 60 * 60 * 24));

		}
		System.out.println(lt);
		if(lt!=null)
			return Response.status(Response.Status.OK).entity(lt).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
		
			//return Response.status(Response.Status.OK).entity(lt).build(); 
		//return lt;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return Response.status(Response.Status.BAD_REQUEST).build(); 
		
		
	}		
}	
}
