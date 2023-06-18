# dev-assessment

Backend API Assessment to build school administrative system

## Background

A school needs a system where administrators can perform administrative functions for teachers and students. Teachers and students are identified by their email addresses.

## Tech Stack

- Spring Boot
- Postgres with JPA for data access
- Junit, Mockito

## Architecture

The architecture used was Model-Controller-Service-Repository.

- model: Domain object (Known as Entity in Spring Boot)
- controller: Management of API REST interface to the business logic.
- service: Business logic implementations.
- repository: Management of data sources access.

The database consists of three tables namely: student, teacher and registration.

- student: A table that manages the student data.
- teacher: A table that manages the teacher data.
- registration: A table that manages the relationship between a student and a teacher.

The database UML is as follows, where one student can have many registration (One-Many) and one teacher can have many registration (One-Many):

![UML](/readme_graphics/uml.png)

## Structure

The codebase was structured according to the domains for scalability, and ensure cleaner code and easier maintenance. For each domain folder, the files were further categorised according to the architecture: controller, model, repository and service.

The codebase was structured as follows:

- error
  - registration
  - student
  - teacher
- registration
  - controller
  - model
  - repository
  - service
- student
  - controller
  - model
  - repository
  - service
- teacher
  - controller
  - model
  - repository
  - service

## Getting Started

To get a local copy up and running, follow these steps:

### Prerequisites

- Java 1.8 or later
- Gradle 7.5+
- PostgresSQL

### Installation

Clone the repository locally.

### Usage

1. Create a postgres database named `administrative`. Alternatively, you may update `spring.datasource.url` in `application.properties`.
2. Update `spring.datasource.username` and `spring.datasource.password` in `application.properties` accordingly.
3. Run the following command in a terminal window.
   ./gradlew bootRun
4. You may set `spring.jpa.hibernate.ddl-auto` to `update` after table creation.

## User Stories Assumptions

### 3. As an administrator, I want to register one or more students to a specified teacher.

If the student does not exist in the database, the student is skipped and continued to the next student without error handling mechanisms.

### 5. As an administrator, I want to retrieve a list of students common to a given list of teachers (i.e. retrieve students who are registered to ALL of the given teachers).

A list of unique students who are registered with all the given teachers is returned. If the given teacher does not exist in the database, the teacher is skipped and continued to the next teacher without error handling mechanisms.

## HTTPS Types

- success: `OK`
- creation success: `CREATED`
- conflict (eg. processing results in conflict): `CONFLICT`
- catch exception errors: `INTERNAL_SERVER_ERROR`

## Error Responses Handling

Enumerations for all the custom error messages were created according to the domains. All the errors follow the JSON response body as follows:
{ "message": "Some meaningful error message" }

## Areas for Improvements

### Commenting the code

Could have added meaningful comments to explain the business logic in depth.

### More Unit Tests

Only wrote simple unit tests to cover simple cases. Could have written unit tests to cover all the methods and use cases; would require more exporation on this.

### Introduce REST API unit testing

Could have added in REST API unit testing to unit test all the API methods.

### Follow the architecture more strictly

Some of the business logics are housed in a controller when they should be in service according to the architecture. In addition, the APIs have multiple overlappings between the student domain and teacher domain. These APIs could have been housed in the `RegistrationController` instead since registration manages the relationship between a student and a teacher.
