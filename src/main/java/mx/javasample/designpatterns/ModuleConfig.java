package mx.javasample.designpatterns;

import mx.javasample.designpatterns.service.PriceService;
import mx.javasample.designpatterns.service.ProductService;
import mx.javasample.designpatterns.service.UserService;
import mx.javasample.designpatterns.service.impl.PriceServiceImpl;
import mx.javasample.designpatterns.service.impl.ProductRecommendationServiceImpl;
import mx.javasample.designpatterns.service.impl.ProductServiceImpl;
import mx.javasample.designpatterns.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ModuleConfig {

    @Value("${app.fakeProductList}")
    private String fakeProductList;
    @Value("${app.productsWithoutPrices}")
    private String productsWithoutPrices;
    @Value("${app.services.productRecommendation.url}")
    private String productRecommendationsEndpoint;
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

    @Bean
    public RestTemplate recommendationServiceClient() {
        return new RestTemplate();
    }

    @Bean
    public ProductRecommendationServiceImpl productRecommendationService(final UserService userService,
                                                                         final PriceService priceService,
                                                                         final ProductService productService,
                                                                         final RestTemplate recommendationServiceClient) {
        return new ProductRecommendationServiceImpl(userService,
                productRecommendationsEndpoint,
                priceService,
                productService,
                recommendationServiceClient);
    }
}
