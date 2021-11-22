package mx.javasample.designpatterns.service.impl;

import mx.javasample.designpatterns.model.Product;
import mx.javasample.designpatterns.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProductServiceImpl implements ProductService {
    private static List<Product> productDb;

    public ProductServiceImpl(final String fakeProductList) {
        productDb = Arrays.stream(fakeProductList.split(","))
                .map(this::product)
                .collect(Collectors.toList());
    }

    private Product product(final String code) {
        final var p = new Product();

        final var name = code + " - name";
        final var description = code + " - description";

        p.setCode(code);
        p.setName(name);
        p.setDescription(description);

        return p;
    }

    @Override
    public Optional<Product> getProductForCode(final String code) {
        return productDb.stream()
                .filter(p -> p.getCode().equals(code))
                .findAny();
    }
}
