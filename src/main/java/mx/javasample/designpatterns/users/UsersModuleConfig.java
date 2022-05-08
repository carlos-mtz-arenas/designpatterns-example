package mx.javasample.designpatterns.users;

import mx.javasample.designpatterns.users.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the users services.
 */
@Configuration
public class UsersModuleConfig {

    @Value("${app.validUsers}")
    private String validUsers;

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(validUsers);
    }

}
