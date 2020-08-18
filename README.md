# harry-potter-api
[![CircleCI](https://circleci.com/gh/RodrigoRP/harry-potter-api.svg?style=svg)](https://circleci.com/gh/RodrigoRP/harry-potter-api)

## About the API

The challenge here is to create a REST API that fulfils some requirements.

We made a partnership with this website (of course the partnership is only to be more friendly in text) it provides us a lot of information about the Harry Potter series.

We propose you create a CRUD application for harry potter characters, in our opinion an amazing film.

Your application must be able to:

    Create a new character
    Read a single character or all characters
    Update a single character
    Delete a single character

But, to save the correct house which the character belongs to, that can be achieve by integrating with the potter api mentioned earlier. The API main URL `/api/v1`.

## Features

This API provides HTTP endpoint's and tools for the following:

* Create a Character: `POST /api/v1/characters/`
* Update a Character: `PATCH /api/v1/characters/{id}`
* Delete a Character (by id): `DELETE/api/v1/characters/{id}`
* Get report of all characters created: `GET/api/v1/characters/`
* Find a unique character by id: `GET/api/v1/characters/{charactersId}`
* Find characters by HouseID: `GET/api/v1/characters`
* Get houses name from https://www.potterapi.com by HouseId : `GET/api/v1/potterapi/houses/{houseId}`

### Details

`POST/api/v1/characters/`

This endpoint is called to create a new transaction.

**Body:**

```json
{
    "name": "Harry Potter",
    "role": "student",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "house": "5a05e2b252f721a3cf2ea33f",
    "patronus": "stag"
}
```

**Where:**

`name` - String

`role` - String

`school` - String

`house` – String (`A valid ID is required for the character to be saved in the database.`)

`patronus` – String


Returns an empty body with one of the following:

* 201 - Created: Everything worked as expected.
* 400 - Bad Request: the request was unacceptable, often due to missing a required parameter or invalid JSON.
* 404 - Not Found: The requested resource doesn't exist.
* 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
* 422 – Unprocessable Entity: if any of the fields are not parsable or the transaction date is in the future.
* 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential backoff of your requests.
* 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).

`PATCH/api/v1/characters/{id}`

This endpoint is called to update a character.

**Body:**

```json
{
    "name": "Ronaldo Silva"
}
```

All fields can be updated except the house attribute.

```json
{
    "name": "Ronaldo Silva",
    "role": "student",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "house": "5a05e2b252f721a3cf2ea33f",
    "patronus": "stag"
}
```

`GET/api/v1/characters/`

This endpoint returns all characters created.

`DELETE/api/v1/characters/{id}`

This endpoint causes a transaction for a specific id to be deleted.

**Where:**

`id` - character id to be deleted.

`GET/api/v1/potterapi/houses/5a05e2b252f721a3cf2ea33f`

This endpoint returns houses name from potterapi.

**Returns:**

```json
 {
    "_id": "5a05e2b252f721a3cf2ea33f",
    "name": "Gryffindor"
  }
```
 
**Where:**

`_id` – Houses id

`name` – Name of the house


### Technologies used

This project was developed with:

* **Java 8**
* **Spring Boot 2.3.3**
* **Circle CI**
* **H2**
* **Maven**
* **JUnit**
* **RestAssured**
* **WebClient**
* **Swagger 2.9.2**
* **Model Mapper 2.3.0**
* **Lombok**
* **Redis**
* **Docker**

### Compile and Package

The API also was developed to run with an `jar`. In order to generate this `jar`, you should run:

```bash
mvn package
```

It will clean, compile and generate a `jar` at target directory, e.g. `harry-potter-api-0.0.1-SNAPSHOT.jar`

### Execution

You need to have **REDIS** installed on your machine to run the API, console the follow command:

```bash
docker run -it --name redis -p 6379:6379 redis:5.0.3
```

In the test profile, the application uses **H2** database (data in memory).

### Test

* For unit test phase, you can run:

```bash
mvn test
```

* To run all tests (including Integration Tests):

```bash
mvn integration-test
```

### Run

In order to run the API First yout need to run Redis as following:

```bash
docker start redis
```
    
and

```bash
mvnw spring-boot:run
```

By default, the API will be available at [http://localhost:8080/api/v1](http://localhost:8080/api/v1/)

### Documentation

* Swagger : http://localhost:8080/swagger-ui.html





