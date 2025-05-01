package mk.ukim.finki.petstore.model;

import jakarta.persistence.Entity;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.enumerations.Currency;
import mk.ukim.finki.petstore.model.enumerations.Type;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@DiscriminatorValue("CAT")
public class Cat extends Pet {
    public Cat(String name, Type type, String description, Date dateOfBirth) {
        super(name, type, description, dateOfBirth);
    }

    public Cat() {

    }

    @Override
    public Money getPrice() {
        int age = this.getAge();
        return new Money(age, Currency.EUR);
    }

    @Override
    public String petSuccessfullyBoughtMessage() {
        return "Meow, cat " + this.getName() + " has owner " + this.getOwner().getFirstName();
    }


}
