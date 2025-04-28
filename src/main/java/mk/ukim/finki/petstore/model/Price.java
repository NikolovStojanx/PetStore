package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;

@Embeddable
public class Price {
    private double price;
    public Price(double price) {
        this.price = price;
    }

    public Price() {

    }

    public double getPrice() {
        return price;
    }
}
