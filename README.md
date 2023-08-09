# library App Java
API Test Project Java
<br>
<br>
<br>

## Run Project
### Already with docker installed and in the root of the project

- First you must provision the postgres database
  - `docker-compose up -d` 
- Sure you have java version 17, compile the project run:
  - `mvn compile`
- Done, now run the project:
  - `mvn spring-boot:run`
<br>
<br>

 
### To create a book use this example in the body:
``` 
{
  "title": "A lei do Triunfo",
  "author": "Napoleon Hill",
  "availableQuantity": 10,
  "numberOfPages": 672,
  "availability": true
}

```
```
for this route (Method: POST): `localhost:8080/books`
```
<br>
<br>

### To create a user use this example in the body:
``` 
{
    "name": "Daniel",
    "borrowedBooks": []
}

```
```
for this route (Method: POST): `localhost:8080/users`
```
<br>
<br>


### To list of users:
```
Route (Method: GET): `localhost:8080/users`
```
<br>
<br>


### To list of books:
```
Route (Method: GET): `localhost:8080/books`
```
<br>
<br>


### To borrow a book:
```
Route (Method: POST): `localhost:8080/books/loan/{userId}/{bookId}`
```
<br>
<br>


### To giveback a book:
```
Route (Method: POST): `localhost:8080/books/giveback/{userId}/{bookId}`
```










