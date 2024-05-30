A basic Job app based on microservice architecture.

### Technologies
-   Spring Boot
-   MySQL



### How to run

To run this project
-   You need to set your own credentials for Job microservice's database and
Company microservice's database at src/main/resources/application.properties

```
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
-   For Zipkin dependency start docker and in any terminal run that command:

    docker run -d -p 9411:9411 openzipkin/zipkin
-   To set up Spring Cloud Config Server add a new repository called
    application-config to github and add the url of this repository to
    application.properties of the configServer file.
    
