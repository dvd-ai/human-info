package com.testtask.humaninfo;

import com.testtask.humaninfo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.testtask.humaninfo.user.constants.UserDto__TestConstants.USER_DTO;
import static com.testtask.humaninfo.user.constants.UserEntity__TestConstants.USER_ENTITY_NO_ID;
import static com.testtask.humaninfo.user.constants.UserErrorMessage__TestConstants.USER_NOT_FOUND_ERR_MESSAGE;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HumanInfoApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;


    @Test
    void getUserById__userFound() throws Exception {
        userRepository.save(USER_ENTITY_NO_ID);

        this.mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$.firstName", is(USER_DTO.firstName())))
                .andExpect(jsonPath("$.lastName", is(USER_DTO.lastName())))
                .andExpect(jsonPath("$.age", is(USER_DTO.age())))
        ;

        userRepository.deleteAll();
    }

    @Test
    void getUserById__userNotFound() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.message", is(USER_NOT_FOUND_ERR_MESSAGE)))
        ;
    }

}
