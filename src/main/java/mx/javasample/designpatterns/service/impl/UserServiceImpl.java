package mx.javasample.designpatterns.service.impl;

import mx.javasample.designpatterns.model.User;
import mx.javasample.designpatterns.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static List<String> usersDB;

    public UserServiceImpl(final String validUsers) {
        usersDB = Arrays.asList(validUsers.split(","));
    }

    @Override
    public Optional<User> getUserForId(final String userId) {
        if (!usersDB.contains(userId)) {
            return Optional.empty();
        }

        final var user = new User();
        user.setEmail(userId);
        user.setName("fake");
        user.setBirthdate(LocalDate.now().minusYears(23));

        return Optional.of(user);
    }

}
