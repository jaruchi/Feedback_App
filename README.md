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
- As a user(student), I should be able to create a class feedback.
- As a user(student), I should be able to read a class feedback.
- As a user(student), I shoule be able to see all the feedbacks
- As a user(student), I should be able to update a class feedback.
- As a user(student), I should be able to delete a class feedback.
- As a user(instructor), can see feedback for a class by all students.(future)
- 

## Homework Feedback Model
- As a user(student), I should be able to create a homework feedback.
- As a user(student), I should be able to read a homework feedback.
- As a user(student), I shoule be able to see all the feedbacks
- As a user(student), I should be able to update a homework feedback.
- As a user(student), I should be able to delete a homework feedback.
- As a user(instructor), can see feedback for a homework by all students.(future)

## EndPoint

| Request_Type | URL           | Body  | Header| Action       | Access       |
| - | - | - | - | - | - |
| Classfeedback controller | - | - | - | - | - |
| GET   | api/classfeedback   |none|Authorization Bearer TOKEN| get all class feed|private|
|GET|api/classfeedback/{classid}|none |Authorization Bearer TOKEN|get a single feed|private|
|POST| api/classfeedback|class single feedback |Authorization Bearer TOKEN |post a single feed|private|
|PUT|api/classfeedback/{classid}|none|Authorization Bearer TOKEN|feedback info|private|

