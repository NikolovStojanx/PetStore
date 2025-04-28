package mk.ukim.finki.petstore.model.abstractions;

import jakarta.persistence.*;
import mk.ukim.finki.petstore.model.Price;
import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.enumerations.Type;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (cascade = CascadeType.ALL)
    private User owner;
    @Column (nullable = false)
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(insertable = false, updatable = false)
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

    public int getAge(){
        LocalDate birthDate = dateOfBirth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public abstract Price getPrice();

    public void setOwner(User owner) {
        this.owner = owner;
        petSuccessfullyBoughtMessage();
    }

    public String getName() {
        return name;
    }

    public Optional<User> getOwner() {
        return Optional.ofNullable(owner);
    }

    public abstract String petSuccessfullyBoughtMessage();
}
