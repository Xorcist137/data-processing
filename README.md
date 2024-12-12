# in-memory database with transactions

this is a simple java implementation of an in-memory key-value store that supports basic transaction operations.

## how to run

1. make sure you have java installed (i used java 11)
2. clone this repo
3. compile the java files:
   javac *.java
4. run the main class:
   java Main

## what it does

- stores key-value pairs (string keys, integer values)
- supports transactions (begin, commit, rollback)
- all changes happen in transactions
- get operations work anytime
- put operations only work in transactions

## how this assignment could be better

The assignment would benefit from clearer specifications about the expected behavior when getting non-existent keys - whether they should return null or 0, as this could affect the way it is graded. 
Adding a requirement for basic exception handling classes would make the code more production-ready and teach better practices. 
The grading could be improved by providing a standardized test suite that checks edge cases and common scenarios.