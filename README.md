#sensorcomponent
	
```
                         ____________________
 __________             |                    |                      ___________
|          |            |  Raspberry Pi 3    |                     |           |
|  Sensor  |----------->|      Running       |-------/ LAN /------>|  MongoDB  | 
|__________|            |  Tomcat Server7    |                     |___________|
                        |    (Java App)      |
                        |____________________|                
                        
```
<div>
	<div>
		<h1>Urban Space Occupancy Analysis - Sensor</h1>
	</div>
	<div>	
		<h3>Overview</h3>
		<p>
		  This is a ECE Senior project on IoT and data analytics. The experiment intends to give an analytical synthesis of human behavior in a given space and time.
		  <br/>
		<h3>Hardware/Software/Frameworks Used</h3>
		<p>
			<ul>
				<li>Motion Sensors (3)</li>
				<li>Raspberry Pi 3 - Model B (1)</li>
				<li>24 Volt Power Supply</li>
				<li>Network Cable </li>
				<li>Router</li>
				<li>Rasbian Ubuntu OS</li>
				<li>Java 7</li>
				<li>Eclipse IDE</li>
				<li>Tomcat Application Server 7</li>
				<li>Mongo DB</li>
			</ul>
		</p>		  
		  The project holds 3 major stages.
		  <ol>
			<li><strong>Data Collection</strong>
				<p>
					The Sensor component - ( JAVA ) sits on an Application Server and listens to the Raspberry pi GPIO pins using the pi4j API. The sensor data is saved in to MongoDB. This data collectively provides an analytical representation of the data collected over a considerable period of time.
				</p>
				<ul>
					<li>Motion Sensor to Raspberry Pi</li>
					<li>MongoDB Data Storage</li>
				</ul><br/>
			</li>
		  </ol>	  
		</p>   
	</div>
</div>

