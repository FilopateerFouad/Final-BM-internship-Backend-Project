# Money Transfer App
This is a Money Transfer application developed using Java Spring Boot. The application handles user registration, login, logout, and JWT authentication. Each customer can create an account, manage their account details, transfer money between accounts, and view transaction history.

## Features

1.User Registration

2.Login with Email and Password

3.Logout (Destroy JWT Token)

4.Create Account (One Account per Customer)

5.Get Customer by ID

6.Get Account by Account Number

7.Session Management 

8.Transfer Money Between Accounts

9.Update Account Info

10.Change Password (Old and New Password)

11.Add Account to Favourites

12.View Transaction History (Sorted by Date)

## Requirements

* Java 11 or higher
* Maven 3.6.0 or higher
* Postgres or any other relational databa

## Setup

* Clone the repository:
```bash
git clone https://github.com/FilopateerFouad/Final-BM-internship-Backend-Project
cd Final-BM-internship-Backend-Project
```
* Configure the database: Update the -- application.properties file in src/main/resources with your database configuration. 
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```
* Install dependencies and build the project:
```bash
mvn clean install
```
* Run the application:
```bash
mvn spring-boot:run
```
* Access Swagger UI for API Documentation:

Open your browser and navigate to http://localhost:8080/swagger-ui.html

## API Endpoints
### Authentication
* Register a new user:
```http
POST /api/v1/auth/register
{
    "name": "username",
    "email": "user@example.com",
    "password": "password",
    "confirmPassword":"password",
    "country":"anycountry",
    "dateOfBirth":"year-month-day",
    "currency":"example_Egy"
}
```
* Login:
 ```http
  POST /api/v1/auth/login
{
    "email": "user@example.com",
    "password": "password"
}
```
Response will include a JWT token and account info

* Logout:
```http
POST /api/v1/auth/logout
Authorization: Bearer <jwt_token>
```
### Account Management
* Get account by account number:
```http
GET /api/v1/account/getaccount/{accountNumber}
Authorization: Bearer <jwt_token>
```
* Get account Balance:
```http
GET /api/v1/account//balance/{accountNumber}
Authorization: Bearer <jwt_token>
```
### Customer Management
* Get customer by ID:
```http
GET /api/v1/customers/{id}
Authorization: Bearer <jwt_token>
```
* Update customer info:
any data is wanted to be changed  and the id must be sent 
```http
 PUT /api/v1/customers/data/{customerId}
 Authorization: Bearer <jwt_token>
{
  "customerId": "long",
  "name":"new_name",
  "email": "new_email"
}
```
* Change password:
```http
 PUT /api/v1/customers/password/{customerId}
 Authorization: Bearer <jwt_token>
{
"oldPassword": "your_old_password",
"newPassword": "your_new_password"
}
```
### Money Transfer
* Transfer money between accounts:
```http
POST /api/v1/account/transfer
Authorization: Bearer <jwt_token>
{
    "fromAccount": "1234567890",
    "toAccount": "0987654321",
    "amount": 100.00
}
```
### Transaction History
* View transaction history
```http
GET /api/v1/account/transactions/{accountNumber}
Authorization: Bearer <jwt_token>
```
### Favourites Management
* Add account to favourites:
```http
POST /api/v1/favourites/{accountNumber}
Authorization: Bearer <jwt_token>
{
  "recipientName":"your_favourite_name",
  "recipientAccount": "yout_favourite_account_number"
}
```
* Get favourite list
```http
GET /api/v1/favourites/{customerId}
Authorization: Bearer <jwt_token>
```
* Delete favourite
```http
DELETE /api/v1/favourites
Authorization: Bearer <jwt_token>
{
  "recipientName":"your_favourite_name",
  "recipientAccount": "yout_favourite_account_number"
}
```
## Security
* JWT tokens are used for securing the API endpoints.

## Acknowledgments
* Spring Boot
* Spring Security
* JWT
* Swagger
* Postman
## Contribution
### Filopateer:
1-Account Creation

2-Transaction History(Add Transaction to all the project)

3-Account Balance

4-Favourite Recipients

5-Testing

6-Docker File

### Marline:
1-Account Logiin

2-Account Logout

3-Session Management

4-Money Transfer

5-Deploy

6-Docker File








  
