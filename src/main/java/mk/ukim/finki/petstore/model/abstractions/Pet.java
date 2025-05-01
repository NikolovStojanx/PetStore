package mk.ukim.finki.petstore.model.abstractions;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.petstore.model.embeddable.Money;
import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.enumerations.Currency;
import mk.ukim.finki.petstore.model.enumerations.Type;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;
    @Column (nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private Type type;
    String description;
    @Temporal(TemporalType.DATE)
    @Column (nullable = false)
    Date dateOfBirth;

    public Pet(String name, Type type, String description, Date dateOfBirth) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public Pet() {

    }

    public int getAge() {
        return Period.between(
                new java.sql.Date(dateOfBirth.getTime()).toLocalDate(),
                LocalDate.now()
        ).getYears();
    }


    public abstract Money getPrice();

    public void setOwner(User owner) {
        this.owner = owner;
        System.out.println(petSuccessfullyBoughtMessage());
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public abstract String petSuccessfullyBoughtMessage();
}
