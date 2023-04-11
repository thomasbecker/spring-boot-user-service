package de.training.userservice.resources;

import de.training.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserResource {
    private Set<User> users = new HashSet<>(Set.of(
            new User(UUID.randomUUID(), "Tom", "Jones", "tom.jones@mail.com"),
            new User(UUID.randomUUID(), "Gunnar", "Wild", "gunnar.wild@somemail.com")
    ));

    @GetMapping
    public Set<User> getUsers() {
        log.info("Get users called: {}", users.stream().map(Objects::toString).collect(Collectors.joining(", ")));
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("Create user called: {}", user);
        users.add(user);
        return user;
    }
}
