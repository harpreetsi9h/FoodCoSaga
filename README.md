# FoodCoSaga Spring Boot Application
This FoodCoSaga is Food Delivery application is combination of different independent services working together using SAGA pattern. 
It is a demonstration of MVC, Event Driven & Saga architecture combined.


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.happydev.FoodCoService.FoodCoServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
``` 
Start the Axon Server first before running the application
## Requirements

For building and running the application you need:

- [JDK 17](https://jdk.java.net/java-se-ri/17) (Minimum)
- [Maven 3](https://maven.apache.org/download.cgi)
- H2 Database
- [Axon Server](https://developer.axoniq.io/download)


### Running the Axon Server locally

Download & Go to the Axon Server directory like

```
cd AxonServer-2023.1.1
```
for example (with path)
```
cd C:\Users\harpr\Softwares\AxonServer-2024.2.1
```
and then run this command to start the server

```
java -jar axonserver.jar
```
### Opening Axon Server Console
After running the Axon Server in Command Line/Terminal it will give you the port number on which
it is running. Open the console in the browser by typing following URL with port number

```
http://localhost:8024/#overview
```

### Opening the H2 Console

After running the project go to the h2 console in browser

[H2 Console](http://localhost:8081/h2-console)

Port number will be same on which your spring boot application is running
```
http://localhost:8081/h2-console
```