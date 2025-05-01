package mk.ukim.finki.petstore.model.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import mk.ukim.finki.petstore.functionalities.calculator.Calculator;
import mk.ukim.finki.petstore.functionalities.constans.Constants;
import mk.ukim.finki.petstore.model.abstractions.ValueObject;
import mk.ukim.finki.petstore.model.enumerations.Currency;

@Getter
@Embeddable
public class Money implements ValueObject {
    private double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money() {
    }


    public Money subtract(Money money) {
        return Calculator.calculateBalance(this, money);

    }



}
