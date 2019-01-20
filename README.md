Spring Boot "Microservice" Example Project

This is a sample Java / Maven / Spring Boot application that can be used as a starter for creating a microservice and much more. I hope it helps you.

How to Run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

Clone this repository
Make sure you are using JDK 1.8 and Maven 3.x
You can build the project and run the tests by running mvn clean package
Once successfully built, you can run the service by one of these two methods:
        java -jar -Dspring.profiles.active=test target/swagger-spring-1.0.0
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
Check the stdout or .logfile file to make sure no exceptions are thrown# task-todo

About the Service
The service is just a simple task-todo crud REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in io.swagger.api.TasksApi.java and TodoApi.java on port 8080.

Here is what this little application demonstrates:

Full integration with the latest Spring Framework: inversion of control, dependency injection, etc.
Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the java -jar command.
All APIs are "self-documented" by Swagger2 using annotations
Automatic CRUD functionality against the data source using Spring Repository pattern.


About Spring Boot
Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

To view your H2 in-memory datbase
The 'todolist' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8080/test/1.0/h2-console. Default username is 'sa' with a blank password.





Questions and Comments: dgpats@gmail.com

