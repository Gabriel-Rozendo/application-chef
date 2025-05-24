package application.chef.service;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static application.chef.validation.UserValidator.validateUserFields;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public OutUser createUser(InUser inUser) {
        //TODO: campos como name, email, password não podem ser vazio
        //TODO: não podemos criar um usuário com o mesmo email
        //TODO: validar se o email termina com @...

        validateUserFields(inUser);

        var userSaved = userRepository.save(new UserModel(inUser.getName(),
                inUser.getEmail(),
                inUser.getPassword()));

        return new OutUser(userSaved.getId(),
                userSaved.getName(),
                userSaved.getEmail());
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getById(UUID id){
        return userRepository.findById(id);
    }

    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }

    public OutUser updateUser(InUser inUser, UUID id) {
        var userResponse = userRepository.findById(id);

        if (userResponse.isEmpty()) {
            throw new ObjectNotFoundException(UserModel.class, "não encontrado");
        }

        userResponse.get().setName(inUser.getName());
        userResponse.get().setEmail(inUser.getEmail());
        userResponse.get().setPassword(inUser.getPassword());

        var userSaved = userRepository.save(userResponse.get());

        return new OutUser(userSaved.getId(),
                userSaved.getName(),
                userSaved.getEmail());
    }
}
