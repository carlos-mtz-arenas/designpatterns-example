package mx.javasample.designpatterns.recommendations;

import mx.javasample.designpatterns.recommendations.services.RecommendationClient;
import mx.javasample.designpatterns.recommendations.services.mockups.MockRecommendationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("mocks")
public class MockThirdPartyConfig {

    @Bean
    public RecommendationClient recommendationClient() {
        return new MockRecommendationClient();
    }

}
