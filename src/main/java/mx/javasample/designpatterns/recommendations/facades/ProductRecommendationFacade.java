package mx.javasample.designpatterns.recommendations.facades;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;

import java.util.Collection;

public interface ProductRecommendationFacade {
    /**
     * Gets the recommended products for the given user.
     *
     * @param userId The id of the user to give recommendations.
     * @return The list of valid products or an empty list if the user does not exist.
     */
    Collection<ProductRecommendationDto> getRecommendedProductsForUser(final String userId);
}
