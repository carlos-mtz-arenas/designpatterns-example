package mx.javasample.designpatterns.service.impl;

import mx.javasample.designpatterns.dto.ValidProductDto;
import mx.javasample.designpatterns.model.Price;
import mx.javasample.designpatterns.model.Product;
import mx.javasample.designpatterns.service.CommercePriceService;
import mx.javasample.designpatterns.service.CommerceProductService;
import mx.javasample.designpatterns.service.ProductService;
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
    public Optional<ValidProductDto> getValidProductForCode(final String code) {
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

        final var wrapper = new ValidProductDto();
        wrapper.setProduct(product);
        wrapper.setPrice(priceOpt.get());

        return Optional.of(wrapper);
    }

    private Optional<Price> getValidPriceForProduct(final Product product) {
        return commercePriceService.getValidPriceForProduct(product.getCode());
    }
}
