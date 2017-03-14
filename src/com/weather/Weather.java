package com.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weather {
	
	@XmlElement public String DATE;
	@XmlElement public double TMAX;
	@XmlElement public double TMIN;
	
	public Weather()
	{
		
	}
	
	public Weather(String DATE, double TMAX, double TMIN)
	{
		this.DATE=DATE;
		this.TMAX=TMAX;
		this.TMIN=TMIN;
	}
	

}
