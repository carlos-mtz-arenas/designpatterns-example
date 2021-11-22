package mx.javasample.designpatterns.service;

import mx.javasample.designpatterns.dto.ValidProductDto;

import java.util.Optional;

public interface CommerceProductService {

    /**
     * Gets a product with all the required data: product info and valid price.
     *
     * @param code The code of the product to be fetched.
     * @return A wrapper of the product and price entities.
     */
    Optional<ValidProductDto> getValidProductForCode(final String code);
}
