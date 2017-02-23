package com.sensorcomponent.dao.impl;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sensorcomponent.dao.MongoDBDao;

public class MongoDBDaoImpl implements MongoDBDao {

	private MongoClient client;
	
	public MongoDBDaoImpl(){
		try {
			client = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private long getNextId(final DBCollection table){
		DBCursor allrows = table.find();
		//Get the next number
		return 1 + allrows.count();
	}

	public int saveSensorData(final BasicDBObject sensorData) {
		try{
			DB db = client.getDB("sensordatabase");
			DBCollection table = db.getCollection("sensordata");
			//Set the Next Number as Id
			long id = getNextId(table);
			sensorData.put("id", id);
			Date date = new Date();
			sensorData.put("month", date.getMonth());
			sensorData.put("day", date.getDate());
			sensorData.put("hour", date.getHours());
	        	sensorData.put("minute", date.getMinutes());
	        	sensorData.put("second", date.getSeconds());
	        	sensorData.put("timestamp", date);			
			//Insert into DB
			table.insert(sensorData);
			return 1;
		} catch(Exception e){
			System.out.println("Data not saved for Reason - " + e);
			return 0;
		} finally {
			if(null != client)
				client.close();
		}
	}

}
