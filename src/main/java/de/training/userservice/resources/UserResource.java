package de.training.userservice.resources;

import de.training.userservice.model.User;
import de.training.userservice.persistence.UserEntity;
import de.training.userservice.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserResource {
    private final UserRepository repository;

    private final Set<User> users = new HashSet<>(Set.of(
            new User(UUID.fromString("ffd36e3c-a658-48e5-b33b-0b4017ee1a7e"), "Tom", "Jones", "tom.jones@mail.com"),
            new User(UUID.fromString("363a48d8-bc82-4d83-b708-42d6303b537c"), "Gunnar", "Wild", "gunnar.wild@somemail.com"
            )));

    public UserResource(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public Set<User> getUsers(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        log.info("Get users called with firstName={} and lastName={}", firstName, lastName);
        var users = repository.findAll();
        log.info("Get users result: {}", users.stream().map(Objects::toString).collect(Collectors.joining(", ")));
        Predicate<User> hasFirstName = user -> firstName == null || user.firstName().equals(firstName);
        Predicate<User> hasLastName = user -> lastName == null || user.lastName().equals(lastName);
        return users.stream()
                .map(UserEntity::from)
                .filter(hasFirstName.and(hasLastName))
                .collect(Collectors.toSet());
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
                .ifPresentOrElse(users::remove, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }
}
