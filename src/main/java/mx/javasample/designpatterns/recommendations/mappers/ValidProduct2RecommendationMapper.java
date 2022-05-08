package mx.javasample.designpatterns.recommendations.mappers;

import mx.javasample.designpatterns.recommendations.dtos.ProductRecommendationDto;
import mx.javasample.designpatterns.commerce.dtos.CommerceProductDto;
import mx.javasample.designpatterns.core.mappers.Mapper;
import org.springframework.util.Assert;

public class ValidProduct2RecommendationMapper implements Mapper<CommerceProductDto, ProductRecommendationDto> {

    @Override
    public ProductRecommendationDto map(final CommerceProductDto validProduct) {
        Assert.notNull(validProduct, "Valid product can't be null");
        Assert.notNull(validProduct.getProduct(), "Product can't be null");
        Assert.notNull(validProduct.getPrice(), "Price can't be null");

        final var recommendation = new ProductRecommendationDto();

        final var product = validProduct.getProduct();

        recommendation.setCode(product.getCode());
        recommendation.setName(product.getName());
        recommendation.setDescription(product.getDescription());

        recommendation.setPrice(validProduct.getPrice().getValue());

        return recommendation;
    }
}
