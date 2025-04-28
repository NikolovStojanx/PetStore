package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Value;

@Embeddable
public class Rating {
    int rating;

    public Rating(int rating) {
        this.rating = rating;
    }

    public Rating() {

    }

    public int getRating() {
        return rating;
    }
}
