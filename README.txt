
==============================================
== RSS feed parser application ==
==============================================

@author Dmitrijs Leonenko


1. MOTIVATION

This simple  application illustrates my skills dealing with Java, Spring framework,
Spring Hibernate and MySQL as data storage database.
 
2. SOME APPLICATION FEATURES

- No using .xml files as app configuration.
- Application connections with DB via Spring Hibernate
- RSS feed parsing using StAX library
- integration of Spring's web MVC, Hibernate and some Java 8 features.
- using CSS and JSTL in views.

3. DATA ACCESS STRATEGIES

RSS feed parser application uses DAO implementations and application
configurations for Hibernate and JPA, with MySQL as target
databases. The default application configuration file is "project/resources/properties.database",
which contains all needed properties for MySQL,this allow working with any
defined database.
Hibernate bean configuration file is "project/config/SpringConfig.java".
For MySQL, you'll need to execute the SQL script in the
"databaseCreationScript/databaseCreationScript.sql" subdirectory. 

4.INSTALLIG INSTRUCTIONS

Clone the repository:
clone https://github.com/dmitrijsgithub/exercise_pearl_v1.git

To run this application:
Use databaseCreationScript (3) to create DB in MySQL. In case of change DB type or 
properties use properties.database (3). 
Application is using http://localhost:8084 as default port, 1.8 JDK, and Maven build manager.
Application has been using IDE embended Apache Tomcat v8.5 server. 



