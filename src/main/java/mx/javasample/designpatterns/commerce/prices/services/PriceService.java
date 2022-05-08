package mx.javasample.designpatterns.commerce.prices.services;

import mx.javasample.designpatterns.commerce.models.Price;

import java.util.Collection;

public interface PriceService {
    /**
     * Gets all the prices for the given product.
     *
     * @param product The product to which all the prices are related to.
     * @return The list of the prices or empty collection if there are no prices for the product.
     */
    Collection<Price> getPricesForProduct(final String product);
}
