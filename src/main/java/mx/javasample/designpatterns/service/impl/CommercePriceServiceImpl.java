package mx.javasample.designpatterns.service.impl;

import mx.javasample.designpatterns.model.Price;
import mx.javasample.designpatterns.service.CommercePriceService;
import mx.javasample.designpatterns.service.PriceService;
import mx.javasample.designpatterns.strategies.ValidPriceStrategy;

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
                .filter(validPriceStrategy::isPriceValid)
                .findAny();
    }
}
