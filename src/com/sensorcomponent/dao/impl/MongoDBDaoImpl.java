package com.sensorcomponent.dao.impl;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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

	public int saveSensorData(final String sensorId, final BasicDBObject sensorData) {
		try{
			DB db = client.getDB("sensordatabase");
			DBCollection table = db.getCollection("sensordata");
			//Set the Next Number as Id
			long id = getNextId(table);
			sensorData.put("id", id);
			//Insert into DB
			table.insert(sensorData);
			return 1;
		} catch(Exception e){
			System.out.println("Data not saved for - " + sensorId + ", Reason - " + e);
			return 0;
		} finally {
			if(null != client)
				client.close();
		}
	}

}
