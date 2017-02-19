package com.sensorcomponent.listener;

import java.sql.Timestamp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mongodb.BasicDBObject;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.sensorcomponent.constants.Constants;
import com.sensorcomponent.dao.MongoDBDao;
import com.sensorcomponent.dao.impl.MongoDBDaoImpl;

public class StartUpListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("############ Sensor Component ############");
		System.out.println("   1. Initial Context Set-Up - Start");
		setUpPi();
		System.out.println("   2. Initial Pi Set-Up - Complete");
		System.out.println("#######################################");
	}
	
	private void setUpPi(){
		try{
			System.out.println("    * GPIO - Pin Set Up Started");
			final GpioController gpioSensor = GpioFactory.getInstance();
			final GpioPinDigitalInput  hall_sensor_pin = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
			final GpioPinDigitalInput  bedrm_sensor_pin = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);
			final GpioPinDigitalInput  gstrm_sensor_pin = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
			
			hall_sensor_pin.addListener(new GpioPinListenerDigital() {           
			    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {        
			        if(event.getState().isHigh()){  
			            System.out.println("Motion Detected at - " + Constants.HALL_SENSOR); 
			            //Set the data
			            BasicDBObject sensorData = new BasicDBObject();
			            sensorData.put("sensorId", Constants.HALL_SENSOR);
			            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			            System.out.println(timestamp);
			            sensorData.put("timestamp", timestamp.toString());
			            //Save the data
			            MongoDBDao dao = new MongoDBDaoImpl();
			            dao.saveSensorData(Constants.HALL_SENSOR, sensorData);
			        }   

			        if(event.getState().isLow()){   
			            //System.out.println("All is quiet...");
			        }   
			    }       
			});  
			
			bedrm_sensor_pin.addListener(new GpioPinListenerDigital() {           
			    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {        
			        if(event.getState().isHigh()){  
			        	System.out.println("Motion Detected at - " + Constants.MSTRM_SENSOR); 
			        	//Set the data
			            BasicDBObject sensorData = new BasicDBObject();
			            sensorData.put("sensorId", Constants.MSTRM_SENSOR);
			            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			            System.out.println(timestamp);
			            sensorData.put("timestamp", timestamp.toString());
			            //Save the data
			            MongoDBDao dao = new MongoDBDaoImpl();
			            dao.saveSensorData(Constants.MSTRM_SENSOR, sensorData);
			        }   

			        if(event.getState().isLow()){   
			            //System.out.println("All is quiet...");
			        }   
			    }       
			});
			
			gstrm_sensor_pin.addListener(new GpioPinListenerDigital() {           
			    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {        
			        if(event.getState().isHigh()){  
			        	System.out.println("Motion Detected at - " + Constants.GSTRM_SENSOR); 
			        	//Set the data
			            BasicDBObject sensorData = new BasicDBObject();
			            sensorData.put("sensorId", Constants.GSTRM_SENSOR);
			            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			            System.out.println(timestamp);
			            sensorData.put("timestamp", timestamp.toString());
			            //Save the data
			            MongoDBDao dao = new MongoDBDaoImpl();
			            dao.saveSensorData(Constants.GSTRM_SENSOR, sensorData);
			        }   

			        if(event.getState().isLow()){   
			            //System.out.println("All is quiet...");
			        }   
			    }       
			});

			System.out.println("    * GPIO - Pin Set Up Completed");
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("############ Ape's Console ############");
		System.out.println("System Shut Down");
		final GpioController gpio = GpioFactory.getInstance();
		gpio.shutdown();
		System.out.println("############ Ape's Console ############");
	}
}
