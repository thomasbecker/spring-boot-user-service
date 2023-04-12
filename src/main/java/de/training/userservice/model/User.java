package de.training.userservice.model;

import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 11.04.23.
 */
public record User(UUID id, String firstName, String lastName, String email) {
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
