package de.training.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 12.04.23.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM Person u " +
            "WHERE (:firstName IS NULL OR u.firstName = :firstName) " +
            "AND (:lastName IS NULL OR u.lastName = :lastName)")
    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

}
