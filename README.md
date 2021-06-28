# Build Restful CRUD API for a simple Address Booking application using Spring Boot, H2, JPA, JUnit and API Google Maps for geolocation.

> Big picture: We're going to create a simple service that manages the address booking. We'll store address objects in a (H2 in-memory) database, and access them (via something called JPA). Then we'll wrap that with something that will allow access over the internet (called the Spring MVC layer).

> In Spring's approach to building RESTful web services, HTTP requests are handled by a controller. These components are identified by the @RestController annotation, and the AddressController handles http operations. This controller is concise and simple.


**1. Clone the application**

```bash
git clone https://github.com/diegocaldeira/restapi-addressbook.git
```

**2. Change in-memory database username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password`

**3. Add your API KEY for Google Maps Geolocation**

+ open `src/main/resources/application.properties`

+ change `app.integrations.googlemaps.key` with your key

**3. Build and run the app using maven**

```bash
mvn package
java -jar target/restapi-addressbook-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

To containerize an application, we enclose our application inside a container image and publish that image to a shared registry. The container runtime pulls this image from the registry, unpacks the image, and runs the application inside it.

Building a docker image
```bash
mvn spring-boot:build-image
```
Running the container
```bash
docker run -it -m 800M restapi-addressbook
```

## Explore Address Rest APIs

The app defines following CRUD APIs.

    GET /v1/address
    
    POST /v1/address
    
    GET /v1/address/{id}
    
    PUT /v1/address/{id}
    
    DELETE /v1/address/{id}

You can test them using postman or any other rest client.

An important facet of REST is the fact that it's neither a technology stack nor a single standard.

REST is a collection of architectural constraints that when adopted make your application much more resilient.
A key factor of resilience is that when you make upgrades to your services, your clients don't suffer from downtime.

## Testing

Testing crud operations with Spring Boot Junit and RestTemplate

## Author
Diego Caldeira

+ <dev@diegocaldeira.com>

+ www.diegocaldeira.com

