package mx.javasample.designpatterns.recommendations.services;

import mx.javasample.designpatterns.users.models.User;
import mx.javasample.designpatterns.recommendations.exceptions.HttpClientException;

import java.util.Collection;

public interface RecommendationClient {

    /**
     * Fetches all the recommendations for the given user.
     *
     * @param user The user from the DB. Can't be null.
     * @return A collection of product codes that are this user's recommendations. An empty list if there are no recommendations.
     * @throws HttpClientException If there's any issue with the connection or the deserialization of the response.
     */
    Collection<String> fetchRecommendations(final User user) throws HttpClientException;
}
