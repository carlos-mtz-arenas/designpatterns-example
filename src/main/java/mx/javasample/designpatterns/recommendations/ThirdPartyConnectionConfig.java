package mx.javasample.designpatterns.recommendations;

import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import mx.javasample.designpatterns.recommendations.services.impl.RecommendationClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("dev")
public class ThirdPartyConnectionConfig {

    @Value("${app.services.productRecommendation.url}")
    private String productRecommendationsEndpoint;



    @Bean
    public RestTemplate recommendationServiceClient() {
        return new RestTemplate();
    }

    @Bean
    public RecommendationClient recommendationClient(final RestTemplate recommendationServiceClient) {
        return new RecommendationClientImpl(recommendationServiceClient, productRecommendationsEndpoint);
    }
}
