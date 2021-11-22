package mx.javasample.designpatterns.service;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;

import java.util.Collection;

public interface ProductRecommendationService {
    /**
     * Gets the recommended products for the given user.
     *
     * @param userId The id of the user to give recommendations.
     * @return The list of valid products or an empty list if the user does not exist.
     */
    Collection<ProductRecommendationDto> getRecommendedProductsForUser(final String userId);
}
