package de.training.userservice.persistence;

import de.training.userservice.model.User;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 12.04.23.
 */
@Entity(name = "Person")
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<AddressEntity> address;

    public UserEntity(UUID id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserEntity() {
    }

    public User from() {
        return new User(id, firstName, lastName, email);
    }

    public static UserEntity to(User user) {
        return new UserEntity(user.id(), user.firstName(), user.lastName(), user.email());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AddressEntity> getAddress() {
        return address;
    }

    public void setAddress(Set<AddressEntity> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
