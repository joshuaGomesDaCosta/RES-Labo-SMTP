# Client SMTP for Pranks :D
#### Author: Joshua Gomes da Costa & Michaël da Silva

## Description
This Java application allow you to play pranks on a list of victims. Via a list of victims you provide (MailPoolAddress.txt), the group size you want, the SMTP server address and the port (Config.txt), the application will send premaid messages (Pranks.txt) without them knowing you did that.

## Mock SMTP server
Perhaps before using this client into a real server and be blacklisted you want to experiment and see how our app works. To do that we provided a mock SMTP server, a "fake" SMTP server which allow you to see the emails created by the client.  

The mock server we choosed is the [MockMock Server](https://github.com/tweakers/MockMock). It is simple to use and has a web interface to see the fake emails sended. To use the MockMock server, you just need to use the following command (where the .jar is): 
 
`java -jar MockMock.jar`  

If you have Docker installed you can build and run the `MockMock.jar` in the Docker directory. To start the MockMock server type the following command:  

`docker build -t mockmockserver .`  
This command will build your container with the DockerFile in the directory

`docker run -p 8282:8282 -p 2525:2525 -t mockmockserver`  
This final command starts the server with the correct ports opened.

You can test the mock server via the command `telnet localhost 2525` to interact with it and go to [http://localhost:8282]() for the web interface. Follow the [RFC  5321 Verifying and Sending Scenario](https://tools.ietf.org/html/rfc5321#appendix-D) example to "send" your first email to the MockMock server.

## Client SMTP 
For the SMTP client , first you have to create the .jar of the application. To do that, go to the directory where the repository is and write the following command:

`mvn clean install`  
This command require `maven` to be installed. When the message `BUILD SUCCESS` 
is displayed then the app is ready to start. Go to the directory `target` and launch  the command `java -jar ClientSMTP-1.0-SNAPSHOT-standalone.jar` (after the server is up).

If you want to change the email addresses used, you can change them in the file `MailPoolAdress.txt`. If you want to change the pranks sended to the victims then go to the `Pranks.txt` file. Finally, if you want to change the SMTP server address or the port or the size of the groups of victims used for the client, you have to modify the file `Config.txt`. Watch out that the group size has to be lower than the total number of email address available (obviously).

All files are in the `src/resources` directory.

## Implementation of the app
