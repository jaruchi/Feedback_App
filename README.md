# Feedback_App

### Introduction

This App allows a user(student) to provide feedbacks for the daily class they are attending and everyday homework.

ER Diagram
![Feedback](assets/erd.png)

### MVP
Our minimum viable product is a working database for:
- User, Course, CourseFeedback, Homework, HomeworkFeedback models.
- All the models except User will have the four CRUD endpoints created using REST conventions.
- When invalid requests are made, the user will be notified by proper error messages.
- A user can register and login to perform various operations.

### User Stories

## User Model
- A user can register as a student or instructor
- A user can login
- A user can retrieve their data


## Course Model
- As a user(instructor), I should be able to create a class.
- As a user, I should be able to read a class.
- As a user(instructor), I should be able to update a class.
- As a user(instructor), I should be able to delete a class.


## Homework Model
- As a user(instructor), I should be able to create a homework.
- As a user, I should be able to read a homework.
- As a user(instructor), I should be able to update a homework.
- As a user(instructor), I should be able to delete a homework.


## Course Feedback Model
- As a user(student), I should be able to create feedback for a course.
- As a user(student), I should be able to update course feedback.
- As a user(student), I should be able to read feedback for a course.
- As a user(student), I should be able to delete feedback for a course.
- As a user(student), I should be able to see all my course feedbacks.
- As a user(instructor), I should be able to see feedback for a course by all students. (future)


## Homework Feedback Model
- As a user(student), I should be able to create feedback for a homework.
- As a user(student), I should be able to update homework feedback.
- As a user(student), I should be able to read feedback for a homework.
- As a user(student), I should be able to delete feedback for a homework.
- As a user(student), I should be able to see all my homework feedbacks.
- As a user(instructor), I should be able to see feedback for a homework by all students. (future)

## EndPoint

### Course Feedback endpoint 
| Request_Type| URL                       |Request Body  |Request Header            | Action                 | Access       |
| - | - | - | - | - | - |
| GET         |api/coursefeedbacks                 |none          |Authorization Bearer TOKEN| get all course feed     |private       |
|GET          |api/coursefeedback/course/{courseId}|none          |Authorization Bearer TOKEN|get a single course feed |private       |
|POST         |api//coursefeedback/course/{courseId}|body          |Authorization Bearer TOKEN|create a single course feed    |private       |
|PUT          |api/coursefeedback/course/{courseId}|body          |Authorization Bearer TOKEN| Update a single course feed |private       |
|DELETE       |api/coursefeedback/course/{courseId}|none          |Authorization Bearer TOKEN|Delete single class feed|private       |

### Course endpoint 
| Request_Type| URL           |Request Body  |Request Header| Action       | Access       |
| - | - | - | - | - | - |
| GET   | api/courses |none|Authorization Bearer TOKEN| get all course |private|
|GET|api/course/{courseId}|none |Authorization Bearer TOKEN|get a single course|private|
|POST| api/course|class single feedback |Authorization Bearer TOKEN |create a single course|private|
|PUT|api/course/{courseId}|none|Authorization Bearer TOKEN|update course single course |private|
|DELETE |api/course/{courseId}|none | Authorization Bearer TOKEN| Delete single course |private|

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
|GET|api/homeworkfeedback/course/{homeworkId}|none |Authorization Bearer TOKEN|get a single homework homework|private|
|POST| api/homeworkfeedback/homework/{homeworkId}|homework single feedback |Authorization Bearer TOKEN |create a single homework feed|private|
|PUT|api/homeworkfeedback/{homeworkid}|none|Authorization Bearer TOKEN|feedback info|private|
|DELETE | api/homeworkfeedback/homework/{homeworkId}|none | Authorization Bearer TOKEN| Delete single homework feed |private|

### User Auth endpoint 
| Request_Type| URL           |Request_Body  |Request_Header| Action       | Access       |
| - | - | - | - | - | - |
|POST| auth/users/register |register user |Authorization Bearer TOKEN |register user  |private|
|POST| auth/users/login |login user |Authorization Bearer TOKEN |Login User |private|
|GET| auth/users/profile|none|Authorization Bearer TOKEN|Retrieving user|private|

### Bonus
- A user can see their profile
- A user(instructor) can see all the feedbacks for a course given by students 
- A user(instructor) can see all the feedbacks for a homework given by students 


### Hurdles
- Merging the code was the greatest hurdle for us
- Major setback was that only 1 machine has a database connectivity, which took most of the time in debugging issues 
  when merging code.

### Wins
- Got confidence in working with Spring Boot Framework
- Learned how to customize Json response
- Learned a lot about git merging/rebase, creating branches, solving merge conflicts

### Future Implementations
- A user can see all the given feedbacks related to course and homework at same place
- A user can update their profile
- Add unit tests
- Allow a user to update their password, profile

### Technologies Used
- Lucidchart - to create the ERD
- IntelliJ
- Spring Boot
- Maven
- pgAdmin
- postgres
- Postman - used to test our end points
- JWT - used as security for user login

### Installation Instructions
- Fork and clone the repository.
- Using postgres, create a database called myfeedbackapp
- Open the file Feedback_app/src/main/resources/application-dev.properties and change lines 2 to the 
  proper port number (9092 recommended) and 4 & 5 to be your postgres username and password

### Resources
- [git rebase] (https://www.w3docs.com/snippets/git/how-to-rebase-git-branch.html)
- Lesson videos


