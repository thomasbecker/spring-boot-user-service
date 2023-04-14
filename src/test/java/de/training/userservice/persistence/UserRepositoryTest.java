package de.training.userservice.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 14.04.23.
 */
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByFirstNameAndLastNameReturnsAllResultsWhenFilteringParametersAreNotSet() {
        createAndPersistBasicTestDataSet();

        var users = userRepository.findByFirstNameAndLastName(null, null);
        assertThat(users.size(), is(5));
    }

    @Test
    void findByFirstNameAndLastNameReturnsFilteredResultsWhenFilteringByFirstName() {
        // given
        createAndPersistBasicTestDataSet();

        // when
        var users = userRepository.findByFirstNameAndLastName("Tom", null);

        // then
        assertThat(users.size(), is(2));
    }

    private void createAndPersistBasicTestDataSet() {
        userRepository.save(createTestUser("Tom", "Jones", "some@email.de"));
        userRepository.save(createTestUser("John", "Jones", "some@email.de"));
        userRepository.save(createTestUser("Tom", "Wild", "some@email.de"));
        userRepository.save(createTestUser("John", "Wild", "some@email.de"));
        userRepository.save(createTestUser("Jane", "Doe", "some@email.de"));
    }

    @Test
    void saveUserPersistsUserSuccessfully() {
        var user = createTestUser("someFirstName", "someLastName", "some@email.de");
        var persistedUser = userRepository.save(user);
        assertThat(persistedUser.getId(), is(not(nullValue())));
        assertThat(userRepository.findAll().size(), is(1));
    }

    private static UserEntity createTestUser(String someFirstName, String someLastName, String email) {
        return new UserEntity(null, someFirstName, someLastName, email);
    }
}
