package mx.javasample.designpatterns.dto;

import mx.javasample.designpatterns.model.Price;
import mx.javasample.designpatterns.model.Product;

public class ValidProductDto {
    private Product product;
    private Price price;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
