package mx.javasample.designpatterns.commerce.dtos;

import mx.javasample.designpatterns.commerce.models.Price;
import mx.javasample.designpatterns.commerce.models.Product;

public class CommerceProductDto {
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
