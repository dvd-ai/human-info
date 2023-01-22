package com.testtask.humaninfo.user;

import com.testtask.humaninfo.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.testtask.humaninfo.user.constants.UserDto__TestConstants.USER_DTO;
import static com.testtask.humaninfo.user.constants.UserEntity__TestConstants.USER_ENTITY;
import static com.testtask.humaninfo.user.constants.UserErrorMessage__TestConstants.USER_NOT_FOUND_ERR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void findById__userFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(USER_ENTITY));

        assertEquals(USER_DTO, userService.findById(1L));
    }

    @Test
    void findById__userNotFound() {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty())
        ;

        String errorMessage = assertThrows(
                NotFoundException.class, () -> userService.findById(1L)
        ).getMessage();

        assertEquals(USER_NOT_FOUND_ERR_MESSAGE, errorMessage);
    }
}