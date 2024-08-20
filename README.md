# Java Spring Boot Bookstore API

Add a new book.
Update book
Find books by title and/or author (Exact Match)
Delete book (Restricted permission)

Notes:

A book is identified uniquely by its ISBN and can have more than 1 author.

All books info (Book information like title, ISBN, Year of Publication, Author(s), Price, Genre etc.) must be stored in a database.

No UI is required, but you are expected to demonstrate the output from each operation. Handling of errors is expected as well.

Design your API as a protected resource

The delete book API should be restricted to only authorized role/user

Implementation of your API should be done in Java

## How to Access Application

- **REST client**
  <br/>```http://localhost:8081/book```


- **cURL**
  <br/>```curl --request GET 'http://localhost:8081/book'```
