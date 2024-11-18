# Task Management System

# Steps to install the project

1. Make sure to have IDE like IntelliJ with Maven and Java version 21
2. Clone the repository from GitHub to your local machine
3. Make sure you have all the following libraries / dependencies in pom.xml file.
4. By default when you clone the project it should be availabe else download from MAVEN repository
5. Maven Repository - https://mvnrepository.com/
6. Run the project and test it on a REST Client software like Postman( Explained further).

---
# Libraries / Dependencies Needed 

1. Spring Web
2. Spring JPA
3. Spring Security
4. H2 Database ( In Memory DB as Mentioned )

---
# Software's Needed

1. JVM 21 
2. IntelliJ
3. Postman
---

# How to run the project ?
1. After downloading to your local machine extract the files.
2. Open the project in IntelliJ and start the server.
3. Make sure to have some JSON data as test cases before hand.
4. Now open postman client and add the login credentials which are mentioned below for test purpose only.
---
# Login Credentials
- Username - wellness360
- Password - wellness360
---
# Login Credentials for H2 DB console

**To view database redirect to -  http://localhost:8080/h2-console**
- Username - admin 
- Password - 1234

---
# Testing the features of the project

1. Testing the security feature - send the JSON data with out entering credentials i.e selecting No Auth option in Postman
2. Now when you try to send the data to post mapper for task creation, it gives an error - 401 UnAuthorized.
3. After selecting Basic Auth option and giving the valid credentials, it gives a messsage of 201 - CREATED i.e Task Created.
4. Check it in H2 DB by redirecting to this url - http://localhost:8080/h2-console and run the SQL query command -> SELECT * FROM TASK which lists the tasks created.
5. Use the following URL's for all the CRUD operations  features in postman for testing
---
# HTTP Request & URL's

**Some test cases are given below for tesing HTTP requests** 
1. GET - localhost:8080/tasks/1 -> Give Task data of ID 1 .

2. GET - localhost:8080/tasks -> Gives all the tasks present in DB.

3. POST - localhost:8080/tasks -> Creates a task.

4. PUT - localhost:8080/tasks/taskId -> Updating a particular with TaskID.

5. DELETE - localhost:8080/tasks/taskId -> Delete a specific task using TaskID.

6. PATCH - localhost:8080/tasks/taskId -> Marking the status of the task as COMPLETED ( as mentioned in the requirement ). 

---
# HTTP Status Codes Used

1. 200 - OK => Success Operation for Task Deletion/ Updation/ Marked/ Fetch
2. 201 - CREATED => Task Created
3. 404 - NOT FOUND => Task Not Found / Not Exsit
4. 409 - CONFLICT => Task Already Exsist ( When same task is sent to post mapper )

---

# JSON data as test cases

{
"taskId":1,
"title": "Fix Bugs in Application #56112112",
"description": "Resolve the issues reported in the application bug tracker.",
"due_date": "2024-11-25",
"status":"PENDING",
"createdAt": "2024-11-14T15:30:00",
"updatedAt": "2024-11-15T16:00:00"
}


{
"taskId":2,
"title": "Fix Bugs in Application #1232321312",
"description": "Fix Bugs.",
"due_date": "2024-11-25",
"status":"IN_PROGRESS",
"createdAt": "2024-11-14T15:30:00",
"updatedAt": "2024-11-15T16:00:00"
}

**NOTE - In database the status is shown as 0 - PENDING, 1 - IN_PROGRESS, 2 - COMPLETED.**
