package de.training.userservice.model;

import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
public record User(UUID id, String firstName, String lastName, String email) {
}
