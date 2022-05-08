package mx.javasample.designpatterns.recommendations.services.impl;

import mx.javasample.designpatterns.users.models.User;
import mx.javasample.designpatterns.recommendations.exceptions.HttpClientException;
import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RecommendationClientImpl implements RecommendationClient {
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationClientImpl.class);

    private final RestTemplate recommendationServiceClient;
    private final String productRecommendationEndpoint;

    public RecommendationClientImpl(final RestTemplate recommendationServiceClient,
                                    final String productRecommendationEndpoint) {
        this.recommendationServiceClient = recommendationServiceClient;
        this.productRecommendationEndpoint = productRecommendationEndpoint;
    }

    /**
     * @see RecommendationClient#fetchRecommendations(User).
     */
    @Override
    public Collection<String> fetchRecommendations(User user) throws HttpClientException {
        LOG.debug("Fetching recommendations for user [{}]", user.getEmail());

        final var response =
                recommendationServiceClient.getForEntity(productRecommendationEndpoint, String[].class);

        final var elements = response.getBody();

        if (elements == null) {
            LOG.warn("There was an issue getting the response from the recommendation service");
            return Collections.emptyList();
        }

        return Arrays.asList(elements);
    }
}
