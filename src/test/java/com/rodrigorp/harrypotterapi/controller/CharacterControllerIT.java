package com.rodrigorp.harrypotterapi.controller;

import com.rodrigorp.harrypotterapi.service.impl.CharacterServiceImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CharacterControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }

    @Test
    void should_Return_Status_200_When_Getting_Character() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/characters/")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void should_Return_Character_When_GetByID() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/characters/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Harry Potter"));
    }

    @Test
    void should_return_404_getById() {
        given()
                .pathParam("id", 99)
                .accept(ContentType.JSON)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_return_200_getAllByHouseID() {
        given()
                .pathParam("id", "5a05e2b252f721a3cf2ea33f")
                .accept(ContentType.JSON)
                .when()
                .get("/characters?houseId={id}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void should_return_404_getAllByHouseID() {
        given()
                .pathParam("id", 99)
                .accept(ContentType.JSON)
                .when()
                .get("/characters?houseId={id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_return_Status_201_When_Create_Character () {
        String characterNew = "{\n" +
                "    \"name\": \"Harry Potter junior\",\n" +
                "    \"role\": \"student\",\n" +
                "    \"school\": \"Hogwarts School of Witchcraft and Wizardry\",\n" +
                "    \"house\": \"5a05e2b252f721a3cf2ea33f\",\n" +
                "    \"patronus\": \"stag\"\n" +
                "}";

        given()
                .body(characterNew)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/characters/")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void should_return_204_when_deleteByID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .delete("/characters/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void should_return_404_when_deleteNotFoundByID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 11)
                .when()
                .delete("/characters/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_Return_Houses_Name_When_GetByHouseID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("houseId", "5a05e2b252f721a3cf2ea33f")
                .when()
                .get("/potterapi/houses/{houseId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItems("Gryffindor"));
    }

    @Test
    void should_Return_404_When_GetByHouseID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("houseId", "aaaa11")
                .when()
                .get("/potterapi/houses/{houseId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
