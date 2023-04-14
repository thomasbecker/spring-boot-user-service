package de.training.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 12.04.23.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String lastName);

    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
