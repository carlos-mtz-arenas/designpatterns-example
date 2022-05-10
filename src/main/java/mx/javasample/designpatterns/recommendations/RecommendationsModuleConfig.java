package mx.javasample.designpatterns.recommendations;

import mx.javasample.designpatterns.recommendations.dtos.ProductRecommendationDto;
import mx.javasample.designpatterns.commerce.dtos.CommerceProductDto;
import mx.javasample.designpatterns.core.mappers.Mapper;
import mx.javasample.designpatterns.recommendations.facades.impl.ProductRecommendationFacadeImpl;
import mx.javasample.designpatterns.recommendations.mappers.ValidProduct2RecommendationMapper;
import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import mx.javasample.designpatterns.commerce.products.services.CommerceProductService;
import mx.javasample.designpatterns.users.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RecommendationsModuleConfig {

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
