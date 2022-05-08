package mx.javasample.designpatterns.commerce;

import mx.javasample.designpatterns.commerce.prices.services.CommercePriceService;
import mx.javasample.designpatterns.commerce.prices.services.PriceService;
import mx.javasample.designpatterns.commerce.prices.services.impl.PriceServiceImpl;
import mx.javasample.designpatterns.commerce.products.services.ProductService;
import mx.javasample.designpatterns.commerce.prices.services.impl.CommercePriceServiceImpl;
import mx.javasample.designpatterns.commerce.products.services.impl.CommerceProductServiceImpl;
import mx.javasample.designpatterns.commerce.products.services.impl.ProductServiceImpl;
import mx.javasample.designpatterns.commerce.prices.strategies.ValidPriceStrategy;
import mx.javasample.designpatterns.commerce.prices.strategies.impl.ValidPriceStrategyImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Wires the commerce-related implementations. This is, any component
 * that provides functionality to the actual commerce usability: valid products, prices, etc.
 */
@Configuration
public class CommerceModuleConfig {


    @Value("${app.fakeProductList}")
    private String fakeProductList;
    @Value("${app.productsWithoutPrices}")
    private String productsWithoutPrices;

    @Bean
    public ProductServiceImpl productService() {
        return new ProductServiceImpl(fakeProductList);
    }

    @Bean
    public PriceServiceImpl priceService() {
        return new PriceServiceImpl(fakeProductList, productsWithoutPrices);
    }



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
