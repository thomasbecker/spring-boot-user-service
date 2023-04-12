package de.training.userservice.resources;

import de.training.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            new User(UUID.fromString("ffd36e3c-a658-48e5-b33b-0b4017ee1a7e"), "Tom", "Jones", "tom.jones@mail.com"),
            new User(UUID.fromString("363a48d8-bc82-4d83-b708-42d6303b537c"), "Gunnar", "Wild", "gunnar.wild@somemail.com"
            )));

    @GetMapping
    public Set<User> getUsers() {
        log.info("Get users called: {}", users.stream().map(Objects::toString).collect(Collectors.joining(", ")));
        return users;
    }

    @GetMapping("/{id}")
    public User getUserBy(@PathVariable UUID id) {
        log.info("Get user by ID called: {}", id);
        return users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("Create user called: {}", user);
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        log.info("Delete user called: {}", id);
        users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .ifPresent(user -> users.remove(user));
    }
}
