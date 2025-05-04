#### Starting the applicaion
Database configuration
The application supports connection to both H2 and PostgreSQL databases.

1. To start the application with the H2 database, simply run the application.
The H2 console is available at localhost:8080/h2-console.
Username: sa
Password: (leave empty)

2. To start the application with the PostgreSQL database, you need to modify the application.properties file.
Change: spring.profiles.active=h2
to
spring.profiles.active=prod
Next, create a PostgreSQL database, set the username, password, port and db name in application-prod.properties to match your database and then run the application.

### API END POINTS

UserRestController

localhost:8080/api/users GET *returns all users

localhost:8080/api/users/generate POST *generates users, count specified in Constants

localhost:8080/api/users/buy POST *for each user buys pet


PetRestController

localhost:8080/api/pets GET *returns all pets

localhost:8080/api/pets/generate POST *generates pets, count specified in Constants


HistroyLogController

localhost:8080/historyLogs GET *returns histroy logs
