package mx.javasample.designpatterns.commerce.products.services.impl;

import mx.javasample.designpatterns.commerce.dtos.CommerceProductDto;
import mx.javasample.designpatterns.commerce.models.Price;
import mx.javasample.designpatterns.commerce.models.Product;
import mx.javasample.designpatterns.commerce.prices.services.CommercePriceService;
import mx.javasample.designpatterns.commerce.products.services.CommerceProductService;
import mx.javasample.designpatterns.commerce.products.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CommerceProductServiceImpl implements CommerceProductService {
    private static final Logger LOG = LoggerFactory.getLogger(CommerceProductServiceImpl.class);

    private final ProductService productService;
    private final CommercePriceService commercePriceService;


    public CommerceProductServiceImpl(final ProductService productService,
                                      final CommercePriceService commercePriceService) {
        this.productService = productService;
        this.commercePriceService = commercePriceService;
    }

    /**
     * @see CommerceProductService#getValidProductForCode(String).
     */
    @Override
    public Optional<CommerceProductDto> getValidProductForCode(final String code) {
        final var productOpt = productService.getProductForCode(code);

        if (productOpt.isEmpty()) {
            LOG.warn("Product [{}] does not exist in our local system", code);
            return Optional.empty();
        }

        final var product = productOpt.get();
        final var priceOpt = getValidPriceForProduct(product);

        if (priceOpt.isEmpty()) {
            LOG.warn("The product [{}] does not have a valid price", product.getCode());
            return Optional.empty();
        }

        final var wrapper = new CommerceProductDto();
        wrapper.setProduct(product);
        wrapper.setPrice(priceOpt.get());

        return Optional.of(wrapper);
    }

    private Optional<Price> getValidPriceForProduct(final Product product) {
        return commercePriceService.getValidPriceForProduct(product.getCode());
    }
}
