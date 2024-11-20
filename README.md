# Task Management System
**A task system built using Java & Spring Boot Framework that helps to keep track of all the tasks need to be done and manage them at one place.**

-  📹 [Video Link](https://drive.google.com/file/d/1aEgeRSemJB-1ZydVFxXQtKNvS_92hnTM/view?usp=sharing)
## Features
- Authenticated users can only access the project.
- Input validations are handled using exceptions.
- Can add, delete, update and mark the status of the task as PENDING, COMPLETED, IN_PROGRESS.
- Can fetch  all the tasks as well as specific task using task ID.
- Accepts cross-origin requests.

## Table of Contents
- [Software's Need](#softwares-needed)
- [Project Structure](#project-structure)
- [Project Structure Overview](#project-structure-overview)
- [Assumptions](#assumptions)
- [Installation Guide](#steps-to-install-the-project)
- [Dependencies](#libraries--dependencies-needed-)
- [How to run ?](#how-to-run-the-project-)
- [Steps to test features](#testing-the-features-of-the-project)
- [Http requests & URL end points](#http-requests--urls)
- [Http status code's used](#http-status-codes-used)
- [JSON test cases](#json-data-as-test-cases)
- [Future scope of improvements](#future-scope-of-improvements)
- [Contact](#contact)

## Software's Needed

- [JDK 21](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe (sha256))
- [IntelliJ with Maven](https://www.jetbrains.com/idea/download/?section=windows)
- [Postman](https://www.postman.com/downloads/)

## Project Structure


```plaintext



├───.idea
├───Backend-Task-Management-System
│   ├───.idea
│   ├───.mvn
│   │   └───wrapper
│   ├───src
│   │   ├───main
│   │   │   ├───java
│   │   │   │   └───com
│   │   │   │       └───wellnes360
│   │   │   │           └───Task_Management_System
│   │   │   │               ├───Config  -                    #Contains Cors and security files.
│   │   │   │               ├───Controller                   #Contains task controller which handles http requests
│   │   │   │               ├───DAO                          #Repository layer - DB Connection ( JPA )
│   │   │   │               ├───Exceptions                   #Custom exceptions like TaskNotFoundException, TaskFoundException, InputFieldException
│   │   │   │               ├───Model                        #Entities -  Task, Api Response.
│   │   │   │               ├───Service                      #Business logic - TaskService
│   │   │   │               └───TaskValidation               #Validating input tasks
│   │   │   └───resources
│   │   │       ├───static
│   │   │       └───templates
│   │   └───test
│   │       └───java
│   │           └───com
│   │               └───wellnes360
│   │                   └───Task_Management_System
│   └───target
│       ├───classes
│       │   └───com
│       │       └───wellnes360
│       │           └───Task_Management_System
│       │               ├───Config
│       │               ├───Controller
│       │               ├───DAO
│       │               ├───Exceptions
│       │               ├───Model
│       │               ├───Service
│       │               └───TaskValidation
│       └───generated-sources
│           └───annotations
└───target
└───generated-sources
└───annotations
```
## Project Structure Overview

### Backend-Task-Management-System
 - Controller -  Handles all HTTP request from client.
 - Service - All the logic is written here.
 - DAO - Database layer to connect with DB.
 - Model - Contains  entities of the project.
 - TaskValidation - Validation of inputs
 - Exceptions - Contains all custom exceptions.
 - Config - Security & Cors configuration are present in this package.




## Assumptions
- As per the requirement, it is assumed that only one task is created at a time.
- And one task is marked as COMPLETE by default when PATCH request is sent.


##  Steps to install the project

1. Make sure to have IDE like IntelliJ with Maven and Java version 21.

2. Clone the repository from GitHub to your local machine, [Help](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository).

3. Make sure you have all the following libraries / dependencies in pom.xml file.

4. By default when you clone the project it should be availabe, else download from MAVEN repository.

5. Maven Repository - https://mvnrepository.com/

6. Run the project and test it on a REST client software like Postman( Explained further).


## Libraries / Dependencies Needed 

1. Spring Web
2. Spring JPA
3. Spring Security
4. H2 Database ( In Memory DB as Mentioned )



## How to run the project ?
1. After downloading to your local machine extract the files.

2. Open the project in IntelliJ and start the server.

3. Make sure to have some JSON data as test cases before hand.

4. Now open postman client and add the login credentials which are mentioned below for `TESTING PURPOSE` only.

### Login Credentials
- Username - wellness360
- Password - wellness360

### Login Credentials for H2 DB console

**To view database redirect to -  http://localhost:8080/h2-console**
- Username - admin 
- Password - 1234


## Testing the features of the project

1. Testing the security feature - send the JSON data with out entering credentials i.e selecting No Auth option in Postman

2. Now when you try to send the data to post mapper for task creation, it gives an `error - 401 UnAuthorized`.

3. After selecting basic auth option and giving the valid credentials, it gives a messsage of `201 - CREATED` i.e Task Created.

4. If the request body doesn't contain all the required fields, it throws an error with status code `400 - BAD REQUEST`.

5. If task already exsists in DB then it throws error with status code `409 - CONFLICT`.

6. Check it in `H2 DB` by redirecting to this url - http://localhost:8080/h2-console and run the SQL query command  `SELECT * FROM TASK` which lists the tasks created.

7. Use the following URL's for all the CRUD operations  features in postman for testing.

8. `ApiResponse` class handles the status codes and error message.
- ***NOTE - In database the status is shown as 0 - PENDING, 1 - IN_PROGRESS, 2 - COMPLETED. Also maintain status marking in CAPITAL LETTERS, example - IN_PROGRESS in JSON data.***


## HTTP Requests & URL's End Points

**Some test cases are given below for tesing HTTP requests**

| HTTP Request | API End Point         | Description                                  |
|--------------|-----------------------|----------------------------------------------|
| `GET`       | /tasks/taskId         | Gives Task Data of specific ID               |
| `GET`         | /tasks                | Gives all the tasks present in DB            |
| `POST`      | /tasks                | Creates a task.                              |
| `PUT`         | /tasks/taskId         | Updating a particular task with Task ID.     |
| `DELETE`         | /tasks/taskId         | Delete a specific task using Task ID.        |
| `PATCH`          | /tasks/taskId | Marking the status of the task as COMPLETED. |



## HTTP Status Code's Used
| Status Code     | Message | Description |
|-----------------|---------|-------------|
| 200    | OK      | Success Operation for Task Deletion/ Updation/ Marked/ Fetch |
| 201 | CREATED |  Task Created |
|400| BAD REQUEST|Missing Input Fields|
|404|NOT FOUND| Task Not Found / Not Exsit|
| 409 |CONFLICT|Task Already Exsist ( When same task is sent to post mapper )|
|500|INTERNAL SERVER ERROR |Unexpected error on server side|
 


## JSON data as test cases
```
- Task 1

{
"taskId":1,
"title": "Fix Bugs in Application #56112112",
"description": "Resolve the issues reported in the application bug tracker.",
"due_date": "2024-11-25",
"status":"PENDING",
"createdAt": "2024-11-14T15:30:00",
"updatedAt": "2024-11-15T16:00:00"
}


- Task 2

{
"taskId":2,
"title": "Fix issue #1232321312",
"description": "Fix Bugs.",
"due_date": "2024-11-25",
"status":"IN_PROGRESS",
"createdAt": "2024-11-14T15:30:00",
"updatedAt": "2024-11-15T16:00:00"
}

- Task 3 ( with some empty fields )

{
"taskId":null,
"title": "",
"description": "Fix Bugs.",
"due_date": "",
"status":"IN_PROGRESS",
"createdAt": "2024-11-14T15:30:00",
"updatedAt": "2024-11-15T16:00:00"
}

```

## Future Scope of Improvements


- Can improve security using JWT tokens.

- Store the data in permanent DB like Postgres.

- Integrate front-end and back-end.


## Contact
- Email - phanisaisrinivasmadiraju@gmail.com
- [Linkedin](https://www.linkedin.com/in/phani-sai-srinivas-madiraju-010553191/)


## Thanks for reading :)

