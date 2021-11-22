package mx.javasample.designpatterns;

import mx.javasample.designpatterns.service.CommercePriceService;
import mx.javasample.designpatterns.service.PriceService;
import mx.javasample.designpatterns.service.ProductService;
import mx.javasample.designpatterns.service.impl.CommercePriceServiceImpl;
import mx.javasample.designpatterns.service.impl.CommerceProductServiceImpl;
import mx.javasample.designpatterns.strategies.ValidPriceStrategy;
import mx.javasample.designpatterns.strategies.impl.ValidPriceStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Wires the commerce-related implementations. This is, any component
 * that provides functionality to the actual commerce usability: valid products, prices, etc.
 */
@Configuration
public class CommerceServicesConfig {

    @Bean
    public CommerceProductServiceImpl commerceProductService(final ProductService productService,
                                                             final CommercePriceService commercePriceService) {
        return new CommerceProductServiceImpl(productService, commercePriceService);
    }

    @Bean
    public CommercePriceServiceImpl commercePriceService(final PriceService priceService,
                                                         final ValidPriceStrategy validPriceStrategy) {
        return new CommercePriceServiceImpl(priceService, validPriceStrategy);
    }

    @Bean
    public ValidPriceStrategyImpl validPriceStrategy() {
        return new ValidPriceStrategyImpl();
    }
}
