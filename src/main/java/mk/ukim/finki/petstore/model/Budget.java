package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;

@Embeddable
public class Budget {
    private double balance;

    public Budget() {
    }


    public double getBalance() {
        return balance;
    }


}
