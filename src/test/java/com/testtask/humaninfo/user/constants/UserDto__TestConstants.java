package com.testtask.humaninfo.user.constants;

import com.testtask.humaninfo.user.UserDto;

import static com.testtask.humaninfo.user.constants.UserEntity__TestConstants.USER_ENTITY;

public class UserDto__TestConstants {

    public static final UserDto USER_DTO = new UserDto(
            USER_ENTITY.getFirstName(),
            USER_ENTITY.getLastName(),
            23
    );
}
