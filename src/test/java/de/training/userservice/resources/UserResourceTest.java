package de.training.userservice.resources;

import de.training.userservice.persistence.UserEntity;
import de.training.userservice.persistence.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

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
        var firstUserId = UUID.randomUUID();
        var secondUserId = UUID.randomUUID();
        var firstUserFirstName = "someFirstName";
        var secondUserFirstName = "secondFirstName";
        Mockito.when(repository.findAll()).thenReturn(
                List.of(
                        new UserEntity(firstUserId, firstUserFirstName, "someLastName", "some@email.de"),
                        new UserEntity(secondUserId, secondUserFirstName, "secondLastName", "second@email.de"))
        );
        when().get("/users")
                .then().log().all()
                .statusCode(200)
                .body("id", hasItems(firstUserId.toString(), secondUserId.toString()),
                        "firstName", hasItems(firstUserFirstName, secondUserFirstName),
                        "size()", is(2));
    }
}
