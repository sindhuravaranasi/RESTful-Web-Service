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
	
	public ArrayList<WeatherDate> getAllDates() throws Exception
	{
		ArrayList<WeatherDate> w=new ArrayList<WeatherDate>();
		try {
			
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from daily");
            while (rs.next())
            {
            	WeatherDate wv=new WeatherDate();
				Date DATE = rs.getDate(1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String format = formatter.format(DATE);
				wv.setDate(format);
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
	            	 
	                w.setDate(sm.format(rs.getDate(1)));
	                w.setTMax(rs.getDouble(2));
	                w.setTMin(rs.getDouble(3));
	            	
	               
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
			String DATE=weather.getDate();
			double TMAX=weather.getTMax();
			double TMIN=weather.getTMin();
			
			SimpleDateFormat sm=new SimpleDateFormat("yyyyMMdd");
			
	            PreparedStatement preparedStatement = con.prepareStatement("select * from daily where dailydate=?");
	           Date d=sm.parse(DATE);
	           preparedStatement.setDate(1,(new java.sql.Date( d.getTime())));
	           
	            ResultSet rs = preparedStatement.executeQuery();
	            boolean flag=rs.last();
	            int r=-1;
	            if(flag)
	            	r=rs.getRow();
	            if(r==0)
	            {
	            	PreparedStatement ps = con.prepareStatement("insert into daily(DAILYDATE,TMAX,TMIN) values (?, ?, ?)");
	            	ps.setDate(1, (new java.sql.Date(d.getTime())));
	            	ps.setDouble(2, TMAX);
	            	ps.setDouble(3,TMIN);
	            	ps.executeUpdate();
	            	
	            }
	            else 
	            {
	            	PreparedStatement p = con.prepareStatement("update daily set TMAX=?, TMIN=?" + "where DAILYDATE=?");
	            	p.setDouble(1,TMAX);
	            	p.setDouble(2,TMIN);
	            	p.setDate(3,(new java.sql.Date(d.getTime())));
	                p.executeUpdate();
	                
	            }
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
	
	
	
	
	
	

