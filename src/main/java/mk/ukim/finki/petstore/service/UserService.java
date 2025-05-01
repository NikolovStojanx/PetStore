package mk.ukim.finki.petstore.service;

import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.dto.UserDTO;

import java.util.List;

public interface UserService {
//    create max 10 users with different(random) props
    List<User> createUsers();
    List<UserDTO> listUsers();
//    go over all users, try yo buy a pet of the store for each user
    void buy();
    boolean buyPetForUser(User user);
}
