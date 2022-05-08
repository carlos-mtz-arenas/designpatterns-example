package mx.javasample.designpatterns.commerce.prices.services.impl;

import mx.javasample.designpatterns.commerce.models.Price;
import mx.javasample.designpatterns.commerce.prices.services.PriceService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class PriceServiceImpl implements PriceService {
    private static Map<String, Collection<Price>> priceDB;

    private final List<String> productsWithoutPrices;

    public PriceServiceImpl(final String fakeProducts,
                            final String productsWithoutPrices) {
        this.productsWithoutPrices = Arrays.asList(productsWithoutPrices.split(","));
        priceDB = Arrays.stream(fakeProducts.split(","))
                .collect(Collectors.toMap(p -> p,
                        this::generatePricesForProduct));
    }

    private Collection<Price> generatePricesForProduct(final String p) {
        if (productsWithoutPrices.contains(p)) {
            return Collections.emptyList();
        }

        final var random = new Random();
        return Arrays.asList(
                generatePrice(p, random.nextBoolean()),
                generatePrice(p, random.nextBoolean()),
                generatePrice(p, random.nextBoolean()),
                generatePrice(p, random.nextBoolean()),
                generatePrice(p, random.nextBoolean())
        );
    }

    private Price generatePrice(final String p, final boolean isValid) {
        final var price = new Price();
        price.setProduct(p);
        price.setValidFrom(LocalDate.now().minusDays(2));
        if (isValid) {
            price.setValidThrough(LocalDate.now().plusDays(1));
        } else {
            price.setValidThrough(LocalDate.now().minusDays(1));
        }
        price.setValue(new Random().nextDouble());

        return price;
    }

    @Override
    public Collection<Price> getPricesForProduct(final String product) {
        return priceDB.getOrDefault(product, Collections.emptyList());
    }
}
