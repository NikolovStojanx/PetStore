package mk.ukim.finki.petstore.web.rest;

import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.dto.UserDTO;
import mk.ukim.finki.petstore.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<UserDTO> getAll() {
        return userService.listUsers();
    }


    @GetMapping("/generate")
    public List<User> generateUsers() {
        List<User> users = userService.createUsers();
        return users;
    }

    @GetMapping("/buy")
    public void buyPets() {
            userService.buy();
    }

}
