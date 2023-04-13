package de.training.userservice.resources;

import de.training.userservice.model.User;
import de.training.userservice.persistence.UserEntity;
import de.training.userservice.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        var filteredUsers = users.stream()
                .map(UserEntity::from)
                .filter(hasFirstName.and(hasLastName))
                .collect(Collectors.toSet());
        if (filteredUsers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return filteredUsers;
    }


    @GetMapping("/{id}")
    public User getUserBy(@PathVariable UUID id) {
        log.info("Get user by ID called: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .from();
    }

    @PostMapping
    public UUID createUser(@RequestBody User user) {
        log.info("Create user called: {}", user);
        if (user.id() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New users must not have an ID set.");
        }
        return repository.save(UserEntity.to(user)).getId();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        log.info("Delete user called: {}", id);
        var user = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(user);
    }
}
