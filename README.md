# napptilus_oompa_loompa
Napptilus_OompaLoompa


 ![Alt text](Doc/structure.png?raw=true "Structure of the project:")

The project was built following the next structures.
 1. Entities
 2. Repository
 3. Service
 4. Controller
 5. Assemblers
 6. Remote Client
 7. Docker
 8. Libraries
 
 
 
 ## Entities
 ![Alt text](Doc/entities.png?raw=true "Structure of the project:")
 
 Here I describe the entities that we are going to need in the project. There are 2 entities
 1. OompaLoompa: This entity manage all the data related to the Oompa Loompas,  ID, Name, Age, Job, Height, Weight,Description
 2. ShortOompa: This class is used to retorn a subset of all the attributes of the OompasLoompas. ID, Name, Age, Job
  
 ## Repository
  ![Alt text](Doc/repository.png?raw=true "Repository:")
  
OompaLoompaRepository contains all the method to interact with the data base. Additionally to the
default methods, I have added 2 more methods, the first method is findByName, 
this method search for an Oompa by his name, the second method is findAll, this method recives a
pageable object. 

 ## Service
   ![Alt text](Doc/interfaz.png?raw=true "Interfaz:")
Here I have defined the service interface, 5 method were defined: 
1. litAllOmpas which return a list of Paged OompaLoompas
2. getOompaLooma: Return an oompa by his id.
3. update: Update an oompa
4. create: Create a oompa
5. extermalOompa: This method is used to comunicated with a different OompaLoompa web servirce in order
to test the circuit braker.  
 
 
  ## Controller
  ![Alt text](Doc/controller.png?raw=true "Controller:")

There are 2 main clases in this package:
1. ControllerOompaLoompa 
2. ErrorMessage

ControllerOompaLoompa: In this class is defined how I managed the different kind of request (GET,PUT,DELETE,...) 
 As you will notice the endpoint is /oompas.
 
  Some of the request use BindingResult, the bindingResult let us to validate the inputs of the controller.
  For example in the method createOompaLoom an Object of the type OompaLoompa is received, this object is 
  validated following the constraints defined in the model OompaLoompa. 
  
  ErrorMessage: It is just a class to manage the error that should be shown to the user in case
  than an error is generated. This error could be generated because one of the constraints could by
  violated. 
  
  ## Assemblers
  
   ![Alt text](Doc/assembler.png?raw=true "Controller:")
   These classes play an importan roll to managed the information returned by the controller.
   As you will notice there are 2 main methods that shoul be implemented when a class extends from a
   RepresentationModelAssemblerSupport class.
  1. toCollectionModel: Here it is define how we paginated the collection of objects returned by the controllers.
  Because we dont want to return an OomaLoompa entity, a ShotOompa is returned instead. This is to be in compliance 
  of the requirement "Get the list of Oompa Loompas. For that list, only name, age and job are required per Oompa Looma."
  ![Alt text](Doc/getListOompas.png?raw=true "client folder:")
  
  
  2. toModel: In this method we add the links to the object OompaLoooma
  ![Alt text](Doc/getMethodApi.png?raw=true "client folder:")
  
  
  ## Remote client
  ![Alt text](Doc/client_content.png?raw=true "client folder:")
  
  In the remote clien I have defined the interface that I will use to comunicate with an external
  web service. For this example I have only implemented one of the method of the remote service,
  the get method, which return a list of the OompasLoomas.
 
 
   ![Alt text](Doc/InterfazRemoteService.png?raw=true "client folder:")
   
   In the implementation of the interface I applying HystrixCommand to execute the fallback method in 
   case that a problem with the communications with the externa web service occurs. In this case
   I'm implementing a very basic circuit baker, instead of use a web service client like Feign I'm using 
   a RestTemplate client. 
   ![Alt text](Doc/fallback.png?raw=true "fallback:")
   
   
   ## Docker
   To create the different docker images , I¶m using a DockerfilePara 
     ![Alt text](Doc/docker.png?raw=true "client folder:")
     
   You can create the images using the next command
   
   docker build -t spring/napptilus_h2  .
   
   Then you can execute the image using: 
   
   docker run -p 8080:8080 spring/napptilus_h2 --server.port=8080
   
   If you click the links you will be able to download the 2 images that I have created
   There are 2 images, 
   1. napptilus_h2 uses a H2 database, an in memory db
   2. napptilus_mariadb uses a MariadbServer 
   If you decide to use the image with mariadb you will need first to create
   a squema called db_test , take a look in the file creation.sql
   
   CREATE SCHEMA `db_test` ;

   docker imagen h2:
   
   https://drive.google.com/file/d/1-abXy_bkUFQ1NQ6Xs9aqhizyy0BjRNDz/view?usp=sharing
   
  docker imagen Mariadb: https://drive.google.com/file/d/1uok5R1zPOJTuFZnUiDaw0aMEqkAdVDR-/view?usp=sharing   
 
   ## Libraries
  h2database : To use the in memory db, could be use for testing not for production.
  
  Spring boot framework
  
  lombok: To decorated the entities and the attributes to be able to autogenerate getters and setters
  
  JUnit: to Create unit tests
  
  spring-cloud-starter-netflix-hystrix: To show a simple example of circuit braker
  
  slf4j: For create logs
  
  mariadb-java-client: To comunicate with a mariadb server
  
  spring-boot-starter-hateoas<: To manage the links in the answere of the api.
  
  ## Databases
  In the project we are able to use 2 databases.
  H2: In memory db really usefull for testing
  Mariadb: A transactional DB
  
  If you want to switch from on engine to the other, you should put the next properties
  in the application.properties file.
  
  for H2:
  
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  
  For Mariadb:
  
  spring.datasource.url=jdbc:mariadb://172.17.0.2:3306/db_test
  spring.datasource.username=root
  spring.datasource.password=root
  spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
  spring.jpa.hibernate.ddl-auto=update

  
  
  
 
Author: Luis Sosa