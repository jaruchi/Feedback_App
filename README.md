# Feedback_App

### Introduction

This App allows a user(students) to provide feedbacks for the daily class they are attending and everyday homework.

ER Diagram
![Feedback](assets/erd.png)

### User Stories

## User Model
- A user can register as a student or instructor
- A user(instructor) can create a Class 
- A user(instructor) can create Homework 
- A user(student) can submit feedback for a class.
- A user(student) can submit feed for the homework they get.


## Class Model
- As a user(instructor), I should be able to create a class.
- As a user, I should be able to read a class.
- As a user(instructor), I should be able to update a class.
- As a user(instructor), I should be able to delete a class.


## Homework Model
- As a user(instructor), I should be able to create a homework.
- As a user, I should be able to read a homework.
- As a user(instructor), I should be able to update a homework.
- As a user(instructor), I should be able to delete a homework.


## Classroom Feedback Model
- As a user(student), I should be able to create feedback for a course.
- As a user(student), I should be able to update course feedback.
- As a user(student), I should be able to read feedback for a course.
- As a user(student), I should be able to delete feedback for a course.
- As a user(student), I should be able to see all my course feedbacks.
- As a user(instructor), can see feedback for a class by all students. (future)


## Homework Feedback Model
- As a user(student), I should be able to create a homework feedback.
- As a user(student), I should be able to read a homework feedback.
- As a user(student), I shoule be able to see all the feedbacks
- As a user(student), I should be able to update a homework feedback.
- As a user(student), I should be able to delete a homework feedback.
- As a user(instructor), can see feedback for a homework by all students.(future)

## EndPoint

### Class Feedback endpoint 
| Request_Type| URL                       |Request Body  |Request Header            | Action                 | Access       |
| - | - | - | - | - | - |
| GET         | api/classfeedback         |none          |Authorization Bearer TOKEN| get all class feed     |private       |
|GET          |api/classfeedback/{classid}|none          |Authorization Bearer TOKEN|get a single class feed |private       |
|POST         | api/classfeedback         |body          |Authorization Bearer TOKEN|create a single feed    |private       |
|PUT          |api/classfeedback/{classid}|body          |Authorization Bearer TOKEN|feedback info           |private       |
|DELETE       |api/classfeedback/{classid}|none          |Authorization Bearer TOKEN|Delete single class feed|private       |

### Class endpoint 
| Request_Type| URL           |Request Body  |Request Header| Action       | Access       |
| - | - | - | - | - | - |
| GET   | api/class |none|Authorization Bearer TOKEN| get all class |private|
|GET|api/class/{classid}|none |Authorization Bearer TOKEN|get a single class|private|
|POST| api/class|class single feedback |Authorization Bearer TOKEN |create a single class|private|
|PUT|api/class/{classid}|none|Authorization Bearer TOKEN|class info|private|
|DELETE | api/class/{classid}|none | Authorization Bearer TOKEN| Delete single class |private|

### Homework endpoint 
| Request_Type| URL           |Request_Body  |Request_Header| Action       | Access       |
| - | - | - | - | - | - |
| GET   | api/homework |none|Authorization Bearer TOKEN| get all homework |private|
|GET|api/homework/{homeworkid}|none |Authorization Bearer TOKEN|get a single homework |private|
|POST| api/homework|homework single |Authorization Bearer TOKEN |create a single homework |private|
|PUT|api/homework/{homeworkid}|none|Authorization Bearer TOKEN|homework info|private|
|DELETE | api/homework/{homeworkid}|none | Authorization Bearer TOKEN| Delete single homework |private|


### Homework Feedback endpoint 
| Request_Type| URL           |Request_Body  |Request_Header| Action       | Access       |
| - | - | - | - | - | - |
| GET   | api/homeworkfeedback |none|Authorization Bearer TOKEN| get all homework feed|private|
|GET|api/homeworkfeedback/{homeworkid}|none |Authorization Bearer TOKEN|get a single homework homework|private|
|POST| api/homeworkfeedback|homework single feedback |Authorization Bearer TOKEN |create a single homework feed|private|
|PUT|api/homeworkfeedback/{homeworkid}|none|Authorization Bearer TOKEN|feedback info|private|
|DELETE | api/homeworkfeedback/{homeworkid}|none | Authorization Bearer TOKEN| Delete single homework feed |private|

### User Auth endpoint 
| Request_Type| URL           |Request_Body  |Request_Header| Action       | Access       |
| - | - | - | - | - | - |
| GET   | auth/user |none|Authorization Bearer TOKEN| get all user |private|
|GET|auth/user/{userid}|none |Authorization Bearer TOKEN|get a single user |private|
|POST| auth/user/register |register user |Authorization Bearer TOKEN |register user  |private|
|POST| auth/user/login |login user |Authorization Bearer TOKEN |Login User |private|
|PUT| auth/user/{userid}|none|Authorization Bearer TOKEN|update user|private|
|DELETE | auth/user/{userid}|none | Authorization Bearer TOKEN| Delete user|private|


