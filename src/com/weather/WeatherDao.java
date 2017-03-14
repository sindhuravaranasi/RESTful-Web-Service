package com.weather;


import java.sql.*;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class WeatherDao {
	
	private Connection con;
	
	
	
	public WeatherDao() throws Exception {
		DbUtil sq=new DbUtil();
		con=sq.getConnection();
	}
	
	public ArrayList<WeatherPojo> getAllDates() throws Exception
	{
		ArrayList<WeatherPojo> w=new ArrayList<WeatherPojo>();
		try {
			
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from daily");
           
            
            while (rs.next())
            {
            	 
            	WeatherPojo wv=new WeatherPojo();
				Date DATE = rs.getDate(1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String f = formatter.format(DATE);
				//wv.setDATE(format);
				wv.DATE=f;
				w.add(wv);
            }
            
		
		return w;
        }
		catch(Exception e)
		{
			throw e;
		}
	
	
	
	
	}
	
	public Weather getWeatherByDate(String DATE) throws Exception{ 
		 
		 try {
			
			Weather w=new Weather();
			 SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
			 
	            PreparedStatement preparedStatement = con.prepareStatement("select * from daily where dailydate=?");
	           Date d=sm.parse(DATE);
	            preparedStatement.setDate(1,(new java.sql.Date( d.getTime())));
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	            	 
	               /*w.setDATE(sm.format(rs.getDate(1)));
	                w.setTMAX(rs.getDouble(2));
	                w.setTMIN(rs.getDouble(3));
	                */
	                w.DATE=sm.format(rs.getDate(1));
	                w.TMAX=rs.getDouble(2);
	                w.TMIN=rs.getDouble(3);
	                
	            	
	            	
	            	
	               
	            }
	            return w;
	        } catch (SQLException e) {
	            throw e;
	        }
		
	         
	   } 
	
	public int addWeather(Weather weather) throws Exception
	{
		try 
		{
			//String date=weather.getDATE();
			
			
			SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
			
	          
	           Date d=sm.parse(weather.DATE);
	           //preparedStatement.setDate(1,(new java.sql.Date( d.getTime())));
	           
	          //  ResultSet rs = preparedStatement.executeQuery();
	           
	            	PreparedStatement ps = con.prepareStatement("insert into daily(dailydate,TMAX,TMIN) values (?, ?, ?)");
	            	ps.setDate(1, (new java.sql.Date(d.getTime())));
	            	ps.setDouble(2,weather.TMAX);
	            	ps.setDouble(3,weather.TMIN);
	            	ps.executeUpdate();
	            	
	           
	                
	            
	            return 1;
	            
		} 
		catch (SQLException e) 
		{
            e.printStackTrace();
        }
		return 0;
		
	}
	
	public int deleteWeather(String DATE) throws Exception
	{
		 try {
			 
			 SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
			 
	            PreparedStatement preparedStatement = con.prepareStatement("select * from daily where dailydate=?");
	            Date d=sm.parse(DATE);
	            preparedStatement.setDate(1,(new java.sql.Date( d.getTime())));
	            ResultSet rs = preparedStatement.executeQuery();
	            boolean flag=rs.last();
	            int r=-1;
	            if(flag)
	            	r=rs.getRow();
	            if(r==1)
	            {
	           PreparedStatement ps = con.prepareStatement("delete from daily where dailydate=?");
	          
	           ps.setDate(1,(new java.sql.Date( d.getTime())));
	           ps.executeUpdate();
	           return 1;
	            }
	            else
	            	return 0;
	            
	           
	        } 
		 catch (SQLException e) {
	          throw e;
	        }
	}
		 
		
		
		 
	}
	
	
	
	
	
	

