package mx.javasample.designpatterns.service.impl;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;
import mx.javasample.designpatterns.model.Price;
import mx.javasample.designpatterns.model.Product;
import mx.javasample.designpatterns.model.User;
import mx.javasample.designpatterns.service.PriceService;
import mx.javasample.designpatterns.service.ProductRecommendationService;
import mx.javasample.designpatterns.service.ProductService;
import mx.javasample.designpatterns.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRecommendationServiceImpl implements ProductRecommendationService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRecommendationServiceImpl.class);

    private final UserService userService;
    private final String productRecommendationEndpoint;
    private final RestTemplate recommendationServiceClient;
    private final ProductService productService;
    private final PriceService priceService;

    public ProductRecommendationServiceImpl(final UserService userService,
                                            final String productRecommendationEndpoint,
                                            final PriceService priceService,
                                            final ProductService productService,
                                            final RestTemplate recommendationServiceClient) {
        this.userService = userService;
        this.productRecommendationEndpoint = productRecommendationEndpoint;
        this.priceService = priceService;
        this.productService = productService;
        this.recommendationServiceClient = recommendationServiceClient;
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

        final var recommendedProductCodes = getRecommendedProductsFromService(user.getEmail());

        return recommendedProductCodes.stream()
                .map(this::enrichWithLocalProductInfo)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ProductRecommendationDto> enrichWithLocalProductInfo(final String productCode) {
        final var productOpt = productService.getProductForCode(productCode);

        if (productOpt.isEmpty()) {
            LOG.warn("Product [{}] does not exist in our local system", productCode);
            return Optional.empty();
        }

        final var product = productOpt.get();
        final var priceOpt = getValidPriceForProduct(product);

        if (priceOpt.isEmpty()) {
            LOG.warn("The product [{}] does not have a valid price", product.getCode());
            return Optional.empty();
        }

        final var recommendationDto = convertToRecommendationDto(product, priceOpt.get());
        return Optional.of(recommendationDto);
    }

    private ProductRecommendationDto convertToRecommendationDto(final Product product, final Price price) {
        final var recommendation = new ProductRecommendationDto();
        recommendation.setCode(product.getCode());
        recommendation.setName(product.getName());
        recommendation.setDescription(product.getDescription());
        recommendation.setPrice(price.getValue());
        return recommendation;
    }

    private List<String> getRecommendedProductsFromService(final String username) {
        LOG.debug("Fetching recommendations for user [{}]", username);

        final var response =
                recommendationServiceClient.getForEntity(productRecommendationEndpoint, String[].class);

        final var elements = response.getBody();

        if (elements == null) {
            LOG.warn("There was an issue getting the response from the recommendation service");
            return Collections.emptyList();
        }

        return Arrays.asList(elements);
    }

    private Optional<Price> getValidPriceForProduct(final Product product) {
        final var prices = priceService.getPricesForProduct(product.getCode());

        final var today = LocalDate.now();

        return prices.stream()
                .filter(p -> (p.getValidFrom() != null && p.getValidFrom().isBefore(today)) &&
                        (p.getValidThrough() != null && p.getValidThrough().isAfter(today)))
                .findAny();
    }
}
