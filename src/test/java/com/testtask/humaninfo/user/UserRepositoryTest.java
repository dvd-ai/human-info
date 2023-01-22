package com.testtask.humaninfo.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.testtask.humaninfo.user.constants.UserEntity__TestConstants.USER_ENTITY_NO_ID;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void findById__userFound() {
        addExpectedUser();

        Optional<User> user = userRepository.findById(1L);

        assertFalse(user.isEmpty());
        assertEquals(USER_ENTITY_NO_ID.getFirstName(), user.get().getFirstName());
    }

    @Test
    void findById__userNotFound() {
        Optional<User> user = userRepository.findById(1L);
        assertTrue(user.isEmpty());
    }

    void addExpectedUser() {
        testEntityManager.merge(USER_ENTITY_NO_ID);
        testEntityManager.flush();
    }

}