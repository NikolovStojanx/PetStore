Database configuration

The application supports connection to both H2 and PostgreSQL databases.

To start the application with the H2 database, simply run the application.
The H2 console is available at localhost:8080/h2-console.
Username: sa
Password: (leave empty)

To start the application with the PostgreSQL database, you need to modify the application.properties file.
Change: spring.profiles.active=h2
to
spring.profiles.active=prod

Next, create a PostgreSQL database named petstore and then run the application on port:8080
