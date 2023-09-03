# DataSpringCafeteriaProject

## Project Description
DataSpringCafeteriaProject is a web application developed using the Spring Boot technology, designed to manage workers and their roles in a cafeteria.

## Technologies
In this project, the following technologies and libraries were used:

**Spring Boot:** The primary technology for building the server-side of the application, including the following starters:

      - `spring-boot-starter-data-jpa`: For working with JPA and the database.
      - `spring-boot-starter-thymeleaf`: For creating HTML pages and rendering them.
      - `spring-boot-starter-validation`: For server-side data validation.
      - `spring-boot-starter-web`: For developing the web application.

**MySQL:** The database where information about workers and their roles is stored.

**Hibernate:** An ORM framework for working with the database.

## Database Connection Configuration
To configure the connection to the database, the `application.yml` file is used, which contains the following parameters:

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/cafeteriadb
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        
```

## Functionality
### The DataSpringCafeteriaProject provides the following features:
***Get List of Workers:*** Ability to view a list of all workers in the cafeteria.

***Get List of Roles:*** Ability to view a list of all available roles.

***View Worker Details:*** Ability to view details of a specific worker, including name, surname, age, role, and more.

***Add New Worker:*** Ability to add a new worker with all the necessary details.

***Edit Worker:*** Ability to edit information about an existing worker, including name, surname, age, role, and more.

***Delete Worker:*** Ability to remove a worker from the system.

***Search Worker by Name or Surname:*** Ability to search for a worker by first name, last name, or their combination.

## Project Structure
### The main components of the project include:
***models:*** Package where data models such as Worker and Role are located.

***repository:*** Package where repositories for working with the database are located, such as WorkerRepository and RoleRepository.

In the WorkerRepository, there is a method created to search for workers by first name or last name.

```java

@Query("SELECT w FROM Worker w WHERE lower(w.firstName) LIKE lower(concat('%', :query, '%')) OR lower(w.lastName) LIKE lower(concat('%', :query, '%'))")
List<Worker> searchByNameOrLastName(@Param("query") String query);

```

This method uses the @Query annotation, which allows you to define your custom SQL query for execution. The method uses a custom SQL query to search for workers by first name or last name. It takes a query parameter representing the search string and returns a list of workers whose names or last names contain a portion of this string (case-insensitive). This allows users to search by part of the worker's name or last name.

***service:*** Package where services for the application logic are located, such as CafeteriaService.

***controller:*** Package where controllers for handling HTTP requests are located.

## Description of Methods
### Description of methods implemented in the CafeteriaService class:
***getAllWorkers():*** Returns a list of all workers.

***getAllRoles():*** Returns a list of all roles.

***getWorkerById(Long id):*** Returns a worker with the specified identifier.

***saveWorker(Worker worker):*** Saves information about a worker.

***saveRole(Role role):*** Saves information about a role.

***deleteWorker(Long id):*** Deletes a worker with the specified identifier.

***searchByFullName(String fullName):*** Searches for workers by name or last name.

## HTML Pages
### The project uses the Thymeleaf template engine to create the following HTML pages:
***workers.html:*** Displays the list of workers.

***add-worker.html:*** A form for adding a new worker.

***worker-details.html:*** Displays details of a specific worker.

***edit-worker.html:*** A form for editing worker information.

***search-form.html:*** A form for searching for a worker by first name or last name.

***search-results.html:*** Displays the search results for workers.

---

#### The DataSpringCafeteriaProject demonstrates how to apply the basics of Spring Boot to develop web applications with basic functionality for creating, reading, updating, and deleting data using a database.