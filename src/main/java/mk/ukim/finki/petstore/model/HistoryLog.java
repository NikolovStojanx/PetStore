package mk.ukim.finki.petstore.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HistoryLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date dateOfExecution;
    @Column(nullable = false)
    private int numUsersBoughtSuccessfully;
    @Column(nullable = false)
    private int numUsersBoughtFailure;

    public HistoryLog(int numUsersBoughtSuccessfully, int numUsersBoughtFailure) {
        this.dateOfExecution = new Date();
        this.numUsersBoughtSuccessfully = numUsersBoughtSuccessfully;
        this.numUsersBoughtFailure = numUsersBoughtFailure;
    }

    public HistoryLog() {

    }
}
