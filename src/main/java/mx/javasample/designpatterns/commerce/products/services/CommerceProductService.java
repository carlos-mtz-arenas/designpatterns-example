package mx.javasample.designpatterns.commerce.products.services;

import mx.javasample.designpatterns.commerce.dtos.CommerceProductDto;

import java.util.Optional;

public interface CommerceProductService {

    /**
     * Gets a product with all the required data: product info and valid price.
     *
     * @param code The code of the product to be fetched.
     * @return A wrapper of the product and price entities.
     */
    Optional<CommerceProductDto> getValidProductForCode(final String code);
}
