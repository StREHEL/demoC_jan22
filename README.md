# afk_offertest

This application manages a user. You can create one user and then requesting it with it's identifier.

## Running

After navigating into the root directory of the project (which is containing the pom.xml file) just type the follwing command in order to run the spring boot application.

```
./mvnw spring-boot:run
```

## Accessing the onboarded database console

Navigate to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your browser.
You could need to enter these values to make it lauch : 
  * Driver class: _org.h2.Driver_
  * JDBC URL: _jdbc:h2:file:~/offertest_
  * User Name: _sa_
  * Password: (not filled)

## Using the API
To create and read users you can import into Postman the collection which is under the 'src/main/resources/postman_collection' sub-directory.

## Building for production

### Packaging as jar

To build the final jar and optimize the afk_test application for production, run:

```
./mvnw -Pprod clean verify
```

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

```
java -jar target/*.jar
```

Then navigate to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your browser.

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

## Testing

Application's tests are located in [src/test/java/](src/test/java/) and can be run with:

```
./mvnw test
```
