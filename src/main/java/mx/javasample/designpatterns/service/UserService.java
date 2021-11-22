package mx.javasample.designpatterns.service;

import mx.javasample.designpatterns.model.User;

import java.util.Optional;

public interface UserService {

    /**
     * Retrieves the information related to the user id.
     *
     * @param userId The user id to be gathered from the DB.
     * @return An existing user or empty in case it doesn't exists.
     */
    Optional<User> getUserForId(final String userId);
}
