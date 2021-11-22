package mx.javasample.designpatterns.strategies;

import mx.javasample.designpatterns.model.Price;

public interface ValidPriceStrategy {

    /**
     * Verifies if the given price is valid within the price range.
     *
     * @param price The price to be checked, can't be null.
     * @return True if the price is valid, false otherwise.
     */
    boolean isPriceValid(final Price price);
}
