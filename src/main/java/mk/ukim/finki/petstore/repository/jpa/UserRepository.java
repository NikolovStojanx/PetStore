package mk.ukim.finki.petstore.repository.jpa;

import mk.ukim.finki.petstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
