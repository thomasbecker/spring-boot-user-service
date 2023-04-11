package de.training.userservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping
    public String sayHello() {
        return "Hello world!";
    }
}
