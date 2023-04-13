package de.training.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 12.04.23.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
