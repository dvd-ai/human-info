package com.testtask.humaninfo.user;

import com.testtask.humaninfo.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.testtask.humaninfo.user.constants.UserErrorMessage__TestConstants.USER_NOT_FOUND_ERR_MESSAGE;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void getUserById__userFound() throws Exception {
        var userDto = setExpectedUserDto();
        when(userService.findById(1L)).thenReturn(userDto);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$.firstName", is(userDto.firstName())))
                .andExpect(jsonPath("$.lastName", is(userDto.lastName())))
                .andExpect(jsonPath("$.age", is(userDto.age())))
        ;

    }

    @Test
    void getUserById__userNotFound() throws Exception {
        when(userService.findById(1L)).thenThrow(new NotFoundException(USER_NOT_FOUND_ERR_MESSAGE));

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.message", is(USER_NOT_FOUND_ERR_MESSAGE)))
        ;

    }

    UserDto setExpectedUserDto() {
        return new UserDto("Oleg", "Flog", 29);
    }
}