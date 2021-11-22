package mx.javasample.designpatterns.service;

import mx.javasample.designpatterns.model.Price;

import java.util.Optional;

public interface CommercePriceService {

    /**
     * Gets a valid price for the given product.
     *
     * @param productCode The code of the product for which the prices are being looked for.
     * @return The first valid price, or empty if non is found/valid.
     */
    Optional<Price> getValidPriceForProduct(final String productCode);
}