package mk.ukim.finki.petstore.repository.jpa;

import mk.ukim.finki.petstore.model.HistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryLogRepository extends JpaRepository<HistoryLog, Long> {
}
