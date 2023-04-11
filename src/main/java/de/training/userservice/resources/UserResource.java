package de.training.userservice.resources;

import de.training.userservice.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping
    public Set<User> getUsers() {
        return Set.of(
                new User("Tom", "Jones", "tom.jones@mail.com"),
                new User("Gunnar", "Wild", "gunnar.wild@somemail.com")
        );
    }
}
