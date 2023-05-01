1. In order to run the project, you need to setup a Spring Boot application. 
2. You need to create a Postgres Database named "Pizzasales".
3. The credentials for the database has been given in the application.properties file.
4. Once the project starts, it will automatically load data from csv to database tables. To check when import is done, check the 
   console log and wait for the "all import done" message.
5. For simplicity, the csv files are kept in the root folder of the project. You can find them in the repository here.
6. Once import is properly done, you can hit the URL "http://localhost:8080/api/viewpizzasales" in POSTMAN/browser to view JSON Data retrieved from 
   the tables. 
