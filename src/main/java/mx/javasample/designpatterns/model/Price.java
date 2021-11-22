package mx.javasample.designpatterns.model;

import java.time.LocalDate;

public class Price {
    private String product;
    private LocalDate validFrom;
    private LocalDate validThrough;
    private Double value;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidThrough() {
        return validThrough;
    }

    public void setValidThrough(LocalDate validThrough) {
        this.validThrough = validThrough;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
