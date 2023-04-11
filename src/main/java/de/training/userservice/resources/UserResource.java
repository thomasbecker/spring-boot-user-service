package de.training.userservice.resources;

import de.training.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserResource {
    @GetMapping
    public Set<User> getUsers() {
        var users = Set.of(
                new User("Tom", "Jones", "tom.jones@mail.com"),
                new User("Gunnar", "Wild", "gunnar.wild@somemail.com")
        );
        log.info("Get users called: {}", users.stream().map(Objects::toString).collect(Collectors.joining(", ")));
        return users;
    }
}
