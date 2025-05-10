package application.chef.controller;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<OutUser> createUser(@RequestBody InUser inUser) {
        var user = userService.createUser(inUser);
        return ResponseEntity.status(CREATED).body(user);
    }

    @GetMapping
    public List<UserModel> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getById(@PathVariable UUID id){
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public OutUser updateUser(@RequestBody InUser inUser, @PathVariable UUID id) {
        return userService.updateUser(inUser, id);
    }

}
