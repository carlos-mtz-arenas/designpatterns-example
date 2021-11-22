package mx.javasample.designpatterns.recommendations;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;
import mx.javasample.designpatterns.dto.ValidProductDto;
import mx.javasample.designpatterns.mappers.Mapper;
import mx.javasample.designpatterns.recommendations.facades.impl.ProductRecommendationFacadeImpl;
import mx.javasample.designpatterns.recommendations.service.RecommendationClient;
import mx.javasample.designpatterns.recommendations.service.impl.RecommendationClientImpl;
import mx.javasample.designpatterns.service.CommerceProductService;
import mx.javasample.designpatterns.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RecommendationsModuleConfig {

    @Value("${app.services.productRecommendation.url}")
    private String productRecommendationsEndpoint;

    @Bean
    public RestTemplate recommendationServiceClient() {
        return new RestTemplate();
    }

    @Bean
    public RecommendationClientImpl recommendationClient(final RestTemplate recommendationServiceClient) {
        return new RecommendationClientImpl(recommendationServiceClient, productRecommendationsEndpoint);
    }

    @Bean
    public ProductRecommendationFacadeImpl productRecommendationFacade(final UserService userService,
                                                                       final CommerceProductService commerceProductService,
                                                                       final RecommendationClient recommendationClient,
                                                                       final Mapper<ValidProductDto, ProductRecommendationDto> validProduct2ProductRecommendationMapper) {
        return new ProductRecommendationFacadeImpl(userService,
                commerceProductService,
                recommendationClient,
                validProduct2ProductRecommendationMapper);
    }
}
