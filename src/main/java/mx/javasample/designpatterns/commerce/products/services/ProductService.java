package mx.javasample.designpatterns.commerce.products.services;

import mx.javasample.designpatterns.commerce.models.Product;

import java.util.Optional;

public interface ProductService {
    /**
     * Gets a product with the given code.
     *
     * @param code The code of the product to be fetched.
     * @return The product or empty in case it doesn't exist.
     */
    Optional<Product> getProductForCode(final String code);
}
