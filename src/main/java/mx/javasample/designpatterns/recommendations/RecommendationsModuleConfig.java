package mx.javasample.designpatterns.recommendations;

import mx.javasample.designpatterns.recommendations.dtos.ProductRecommendationDto;
import mx.javasample.designpatterns.commerce.dtos.CommerceProductDto;
import mx.javasample.designpatterns.core.mappers.Mapper;
import mx.javasample.designpatterns.recommendations.facades.impl.ProductRecommendationFacadeImpl;
import mx.javasample.designpatterns.recommendations.mappers.ValidProduct2RecommendationMapper;
import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import mx.javasample.designpatterns.recommendations.services.impl.RecommendationClientImpl;
import mx.javasample.designpatterns.commerce.products.services.CommerceProductService;
import mx.javasample.designpatterns.recommendations.services.mockups.MockRecommendationClient;
import mx.javasample.designpatterns.users.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RecommendationsModuleConfig {

    @Value("${app.services.productRecommendation.url}")
    private String productRecommendationsEndpoint;

    @Bean
    @Profile("mocks")
    public RecommendationClient recommendationClient() {
        return new MockRecommendationClient();
    }

    @Bean
    @Profile("!mocks")
    public RestTemplate recommendationServiceClient() {
        return new RestTemplate();
    }

    @Bean
    @Profile("!mocks")
    public RecommendationClient recommendationClient(final RestTemplate recommendationServiceClient) {
        return new RecommendationClientImpl(recommendationServiceClient, productRecommendationsEndpoint);
    }

    @Bean
    public ValidProduct2RecommendationMapper validProduct2RecommendationMapper() {
        return new ValidProduct2RecommendationMapper();
    }

    @Bean
    public ProductRecommendationFacadeImpl productRecommendationFacade(final UserService userService,
                                                                       final CommerceProductService commerceProductService,
                                                                       final RecommendationClient recommendationClient,
                                                                       final Mapper<CommerceProductDto, ProductRecommendationDto> validProduct2ProductRecommendationMapper) {
        return new ProductRecommendationFacadeImpl(userService,
                commerceProductService,
                recommendationClient,
                validProduct2ProductRecommendationMapper);
    }
}
