package application.chef.controller;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO: DESENVOLVER O PUT PARA ATUALIZAR UM USUARIO
    //TODO: USAR O DTO EM OUTRAS FUNÇÕES TAMBÉM

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public OutUser createUser(@RequestBody InUser inUser) {
        return userService.createUser(inUser);
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

}
