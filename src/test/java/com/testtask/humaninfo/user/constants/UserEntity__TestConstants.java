package com.testtask.humaninfo.user.constants;

import com.testtask.humaninfo.user.User;

import java.time.LocalDate;

public class UserEntity__TestConstants {
    public static final User USER_ENTITY = new User(
            1L, "Petr", "Kolbaskin",
            LocalDate.of(2000, 1, 22)
    );

    public static final User USER_ENTITY_NO_ID = new User(
            USER_ENTITY.getFirstName(), USER_ENTITY.getLastName(),
            USER_ENTITY.getBirthdate()
    );

}
