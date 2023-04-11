package de.training.userservice.model;

import lombok.Value;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
@Value
public class User {
    String firstName;
    String lastName;
    String email;
}
