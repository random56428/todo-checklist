# Simple To-Do Checklist Project

A simple to-do checklist project made from Java for CPSC210.

## Proposal

- *What will the application do?*
    - Keep track of tasks through entering them manually into individual text boxes. Check off items that are finished
      and keep a history of them. Able to edit tasks by clicking on the task to make changes and able to delete tasks.
- *Who will use it?*
    - Anyone could use it, this application is not only tailored to students but anyone who needs to keep track of
      something. For example, you can keep track of groceries to purchase, important meeting dates, and daily routines.
- *Why is this project of interest to you?*
    - This is the first coding project I will have ever done while learning Java. This project interests me because of
      its simplicity and functionality. I've been using notepad to keep track of tasks I have to do. While notepad is
      great for keeping track of small amount of tasks, a large amount of homework, labs, and things to do easily gets
      really cluttered. Hopefully, this project will eliminate that.

## User Stories

- As a user, I want to be able to add a task to my to-do list
- As a user, I want to be able to delete a task from my to-do list
- As a user, I want to be able to edit a task from my to-do list
- As a user, I want to be able to check a task and mark it as done from my to-do list
- As a user, I want to be able to uncheck a task and move it back to my to-do list
- As a user, I want to be able to clear the completed tasks
- As a user, I want to be able to view the list of tasks currently in my to-do list
- As a user, I want to be able to view the list of tasks completed
- As a user, I want to be able to view the number of incomplete and complete tasks in my to-do list
- As a user, I want to be able to save my to-do list into a file
- As a user, I want to be able to load my to-do list from a file

## Phase 4: Task 2

Sample of events:
```
Sun Nov 21 23:21:48 PST 2021
[ADD] "Breakfast 7am" todo task added to to-do list
Sun Nov 21 23:21:52 PST 2021
[ADD] "CPSC lecture 9am" todo task added to to-do list
Sun Nov 21 23:21:57 PST 2021
[ADD] "CPSC lab 12pm" todo task added to to-do list
Sun Nov 21 23:22:01 PST 2021
[ADD] "Lunch 1pm" todo task added to to-do list
Sun Nov 21 23:22:06 PST 2021
[ADD] "Dinner 6pm" todo task added to to-do list
Sun Nov 21 23:22:14 PST 2021
[ADD] "Buy groceries: chicken, egg, rice" todo task added to to-do list
Sun Nov 21 23:22:18 PST 2021
[CHECK] "Breakfast 7am" todo task checked from to-do list
Sun Nov 21 23:22:22 PST 2021
[CHECK] "CPSC lecture 9am" todo task checked from to-do list
Sun Nov 21 23:22:29 PST 2021
[CLEAR-ALL] Cleared all completed tasks in completed to-do list
Sun Nov 21 23:22:39 PST 2021
[EDIT] "CPSC lab 12pm" changed to "CPSC lab 1pm" todo task in to-do list
Sun Nov 21 23:22:40 PST 2021
[CHECK] "CPSC lab 1pm" todo task checked from to-do list
Sun Nov 21 23:22:42 PST 2021
[UNCHECK] "CPSC lab 1pm" todo task unchecked from completed to-do list
Sun Nov 21 23:22:46 PST 2021
[DELETE] "CPSC lab 1pm" todo task deleted from to-do list
Sun Nov 21 23:22:48 PST 2021
[CHECK] "Lunch 1pm" todo task checked from to-do list
Sun Nov 21 23:22:52 PST 2021
[EDIT] "Dinner 6pm" changed to "Dinner 5pm" todo task in to-do list
Sun Nov 21 23:22:54 PST 2021
[CHECK] "Dinner 5pm" todo task checked from to-do list
Sun Nov 21 23:22:55 PST 2021
[CLEAR-ALL] Cleared all completed tasks in completed to-do list
Sun Nov 21 23:22:57 PST 2021
[CHECK] "Buy groceries: chicken, egg, rice" todo task checked from to-do list
Sun Nov 21 23:22:58 PST 2021
[UNCHECK] "Buy groceries: chicken, egg, rice" todo task unchecked from completed to-do list
Sun Nov 21 23:23:06 PST 2021
[EDIT] "Buy groceries: chicken, egg, rice" changed to "Buy groceries: chicken, egg, rice, pasta" todo task in to-do list
Sun Nov 21 23:23:11 PST 2021
[CHECK] "Buy groceries: chicken, egg, rice, pasta" todo task checked from to-do list
Sun Nov 21 23:23:13 PST 2021
[CLEAR-ALL] Cleared all completed tasks in completed to-do list
```