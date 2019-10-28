# GA Reddit API Monolith

This project is a back-end application using Spring for the object dependencies, Hibernate for the tables and queries, and PostgreSQL to store the appliation's data; The application connects a front-end web application to a back-end using REST api; and uses Tomcat to run the app locally.  We also implemented security using Mockito mocking.

### Pivotal Tracker

https://www.pivotaltracker.com/n/projects/2407497

### ERD Diagram
![Image of ERD](https://github.com/magfurulabeer/ga-reddit-api-monolith/blob/master/erd.png)

### Approach

The project utilized pair programming to develop an API.  We wrote our user stories using Pivotal tracker. Then we created an ERD diagram to map out the Entities (tables), fields, and table relationships.

We created separate packages for the controller, service, DAO and enities. After setting up the layers for an entity, the connections were tested in Postman before creating the next entity, to make sure the database was responding correctly.  At first we set-up the back-end without security to make it easier to test.  After testing a few endpoints successfully we decided to implement security so we would not have to refactor the code later to handle security.  After the application was mostly completed, we worked on connecting it to the front end.  We then tested the application to make sure the application was working correctly.  The last step was to set-up testing for each package.

The major hurdles we encountered were configuring the security so it would only allow the user to access data the user was authorizedd to see. Another hurdle was getting the endpoints of the application to match the front-end.  This required re-working the applications field names and endpoints.  We also found the testing to be challenging,

### Timeline
https://drive.google.com/file/d/1_DPQLLwmWmwzuoKRKP7xNwYRb9hWcmLF/view?usp=sharing

### Installation
The application requires setting up and running PostgreSQL and Tomcat locally, specifying the database location in the application's configuration file, and modifying the front end to use the correct HTTP addresses.
