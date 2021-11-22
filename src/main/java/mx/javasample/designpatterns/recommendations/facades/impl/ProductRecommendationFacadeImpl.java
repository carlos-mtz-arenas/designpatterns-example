package mx.javasample.designpatterns.recommendations.facades.impl;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;
import mx.javasample.designpatterns.dto.ValidProductDto;
import mx.javasample.designpatterns.mappers.Mapper;
import mx.javasample.designpatterns.model.User;
import mx.javasample.designpatterns.recommendations.exceptions.HttpClientException;
import mx.javasample.designpatterns.recommendations.facades.ProductRecommendationFacade;
import mx.javasample.designpatterns.recommendations.service.RecommendationClient;
import mx.javasample.designpatterns.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRecommendationFacadeImpl implements ProductRecommendationFacade {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRecommendationFacadeImpl.class);

    private final UserService userService;
    private final CommerceProductService commerceProductService;
    private final RecommendationClient recommendationClient;
    private final Mapper<ValidProductDto, ProductRecommendationDto> validProduct2ProductRecommendationMapper;

    public ProductRecommendationFacadeImpl(final UserService userService,
                                           final CommerceProductService commerceProductService,
                                           final RecommendationClient recommendationClient,
                                           final Mapper<ValidProductDto, ProductRecommendationDto> validProduct2ProductRecommendationMapper) {
        this.userService = userService;
        this.commerceProductService = commerceProductService;
        this.recommendationClient = recommendationClient;
        this.validProduct2ProductRecommendationMapper = validProduct2ProductRecommendationMapper;
    }

    @Override
    public Collection<ProductRecommendationDto> getRecommendedProductsForUser(final String userId) {
        final var userOpt = userService.getUserForId(userId);

        if (userOpt.isEmpty()) {
            LOG.trace("Won't fetch recommended products since there's not user in the session");
            return Collections.emptyList();
        }

        final var user = userOpt.get();

        return getRecommendationsForUser(user);
    }

    private Collection<ProductRecommendationDto> getRecommendationsForUser(final User user) {
        LOG.debug("Fetching recommendations for user [{}]", user.getEmail());

        final Collection<String> recommendedProductCodes;

        try {
            recommendedProductCodes = recommendationClient.fetchRecommendations(user);
        } catch (HttpClientException ex) {
            LOG.error("There was an error while trying to get the recommendations for user [{}]", user.getEmail(), ex);
            return Collections.emptyList();
        }

        return recommendedProductCodes.stream()
                .map(this::enrichWithLocalProductInfo)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ProductRecommendationDto> enrichWithLocalProductInfo(final String productCode) {
        final var validProductOpt = commerceProductService.getValidProductForCode(productCode);

        if (validProductOpt.isEmpty()) {
            LOG.debug("No valid product was found for product code [{}]", productCode);
            return Optional.empty();
        }

        final var validProduct = validProductOpt.get();

        return Optional.of(validProduct2ProductRecommendationMapper.map(validProduct));
    }


}
