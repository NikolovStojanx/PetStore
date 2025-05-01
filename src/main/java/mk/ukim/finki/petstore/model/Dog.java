package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.embeddable.Rating;
import mk.ukim.finki.petstore.model.enumerations.Currency;
import mk.ukim.finki.petstore.model.enumerations.Type;

import java.util.Date;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Pet {
    @Column(nullable = false)
    private Rating rating;
    public Dog(String name, Type type, String description, Date dateOfBirth, Rating rating) {
        super(name, type, description, dateOfBirth);
        this.rating = rating;
    }

    public Dog() {

    }

    @Override
    public Money getPrice() {
        int age = this.getAge();
        return new Money(age + rating.getRating(), Currency.EUR);
    }

    @Override
    public String petSuccessfullyBoughtMessage() {
        return "Woof, dog " + this.getName() + " has owner " + this.getOwner().getFirstName();
    }
}
