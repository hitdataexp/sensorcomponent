# sensorcomponent
The Sensor component - ( JAVA ) sits on an Application Server and listens to the Raspberry pi GPIO pins using the pi4j API.
```
                         ____________________
 __________             |                    |                      ___________
|          |            |  Raspberry Pi 3    |                     |           |
|  Sensor  |----------->|      Running       |-------/ LAN /------>|  MongoDB  | 
|__________|            |  Tomcat Server7    |                     |___________|
                        |                    |
                        |____________________|                
                        
```
### Project Set up
* 1. Download Code
* 2. Download
     * a. pi4j-core-1.2-SNAPSHOT.jar
     * b. javax.servlet-api-3.1.0.jar
     * c. mongo-java-driver-2.13.3.jar
* 3. Drop the 3 jars in the /lib directory
* 4. Create directory - > /home/pi/PlayGround/output-war
* 5. Run ANT Build
* 6. The generated war file will be in /home/pi/PlayGround/output-war
* 7. Copy war file into /var/lib/tomcat7/webapps/
