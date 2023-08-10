# Bank API To Get Information about Account and Transactions
___
### Spring Boot Application with Rest Apis

### Summary
The assessment consists of an API to be used for deposit or credit transactions of Account.

The application has 1 api
* AccountAPI

```EndPoints
POST /account/create/{owner}/{accountNumber} - creates a new account
GET /account/{accountId} - retrieves a account with transactions
POST /account/credit - {"accountNumber":?,"amount":?}
POST /account/debit - {"accountNumber":?,"amount":?}
```

### Tech Stack

---
- Java 17
- Spring Boot
- Spring Data JPA
- Restful API
- Swagger
- Lombok
- JUnit 5

### Run & Build


#### Gradle

$ cd simplebanking <br />
$ ./gradlew build <br />
$ ./gradlew bootRun <br />

```
### Swagger UI will be run on this url
`http://localhost:${PORT}/swagger-ui.html`