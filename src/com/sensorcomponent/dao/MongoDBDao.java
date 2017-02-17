package com.sensorcomponent.dao;

import com.mongodb.BasicDBObject;

public interface MongoDBDao {
	
	/*
	 * Raspberry Pi 
	 */
	public int saveSensorData(final String sensorId, final BasicDBObject sensorData);
	
}
