package com.testtask.humaninfo.user;

import com.testtask.humaninfo.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private int calculateUserAge(User user) {
        return Period.between(user.getBirthdate(), LocalDate.now())
                .getYears();
    }

    public UserDto findById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return new UserDto(
                    user.getFirstName(), user.getLastName(),
                    calculateUserAge(user)
            );
        } else throw new NotFoundException("user with id " + userId + " not found");
    }
}
