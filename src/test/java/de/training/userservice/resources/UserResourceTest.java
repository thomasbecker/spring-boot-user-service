package de.training.userservice.resources;

import de.training.userservice.persistence.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 13.04.23.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResourceTest {
    @LocalServerPort
    private int port;

    @MockBean
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getUsers() {
        when().get("/users")
                .then().statusCode(200);
    }
}
