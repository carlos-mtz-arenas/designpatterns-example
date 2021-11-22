package mx.javasample.designpatterns;

import mx.javasample.designpatterns.service.impl.PriceServiceImpl;
import mx.javasample.designpatterns.service.impl.ProductServiceImpl;
import mx.javasample.designpatterns.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configures the lowest-level beans for the business to work,
 * primarily CRUD operations.
 */
@Configuration
public class CoreServicesConfig {

    @Value("${app.fakeProductList}")
    private String fakeProductList;
    @Value("${app.productsWithoutPrices}")
    private String productsWithoutPrices;
    @Value("${app.validUsers}")
    private String validUsers;

    @Bean
    public ProductServiceImpl productService() {
        return new ProductServiceImpl(fakeProductList);
    }

    @Bean
    public PriceServiceImpl priceService() {
        return new PriceServiceImpl(fakeProductList, productsWithoutPrices);
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(validUsers);
    }


}
