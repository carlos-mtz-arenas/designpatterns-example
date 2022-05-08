package mx.javasample.designpatterns.commerce.prices.services.impl;

import mx.javasample.designpatterns.commerce.models.Price;
import mx.javasample.designpatterns.commerce.prices.services.CommercePriceService;
import mx.javasample.designpatterns.commerce.prices.services.PriceService;
import mx.javasample.designpatterns.commerce.prices.strategies.ValidPriceStrategy;

import java.util.Objects;
import java.util.Optional;

public class CommercePriceServiceImpl implements CommercePriceService {

    private final PriceService priceService;
    private final ValidPriceStrategy validPriceStrategy;

    public CommercePriceServiceImpl(final PriceService priceService,
                                    final ValidPriceStrategy validPriceStrategy) {
        this.priceService = priceService;
        this.validPriceStrategy = validPriceStrategy;
    }

    /**
     * @see CommercePriceService#getValidPriceForProduct(String).
     */
    @Override
    public Optional<Price> getValidPriceForProduct(final String productCode) {
        final var prices = priceService.getPricesForProduct(productCode);

        return prices.stream()
                .filter(Objects::nonNull)
                .filter(validPriceStrategy::isPriceValid)
                .findAny();
    }
}
