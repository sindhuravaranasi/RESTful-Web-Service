package com.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WeatherPojo {
	
	@XmlElement public String DATE;
	
	public WeatherPojo()
	{
		
	}
	
	public WeatherPojo(String DATE)
	{
		this.DATE=DATE;
	}
	
	

}
