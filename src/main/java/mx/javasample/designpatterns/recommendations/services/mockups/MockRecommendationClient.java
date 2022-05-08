package mx.javasample.designpatterns.recommendations.services.mockups;

import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import mx.javasample.designpatterns.users.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

public class MockRecommendationClient implements RecommendationClient {
    private static final Logger LOG = LoggerFactory.getLogger(MockRecommendationClient.class);

    private final Collection<String> products;

    public MockRecommendationClient() {
        this.products =
                Arrays.asList("10101", "10102", "10103", "20104", "20105");
    }

    @Override
    public Collection<String> fetchRecommendations(final User user) {
        Assert.notNull(user, "User should not ben null");

        LOG.warn("Using mock implementation to fetch recommendations for user [{}]", user.getEmail());

        return this.products;
    }
}
