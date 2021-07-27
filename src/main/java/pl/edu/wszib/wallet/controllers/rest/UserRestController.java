package pl.edu.wszib.wallet.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.services.IUserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final IUserService userService;

    @Autowired
    UserRestController(final IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addNewUser(@RequestBody User user) {
        this.userService.addNewUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable final Long id) {
        return this.userService.getUserById(id);
    }

}
