package mx.javasample.designpatterns.controller;

import mx.javasample.designpatterns.dto.ProductRecommendationDto;
import mx.javasample.designpatterns.service.ProductRecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

@RequestMapping("/products/recommendations")
@RestController
public class ProductRecommendationsController {

    @Resource(name = "productRecommendationService")
    private ProductRecommendationService productRecommendationService;

    @GetMapping("/{userId}")
    public Collection<ProductRecommendationDto> getRecommendationsForUser(
            @PathVariable(required = false) final String userId) {
        return productRecommendationService.getRecommendedProductsForUser(userId);
    }
}
