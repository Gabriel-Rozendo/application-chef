package application.chef.controller;

import application.chef.model.UserModel;
import application.chef.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping
    public List<UserModel> findAllUsers() {
        return userService.findAllUsers();
    }

}
