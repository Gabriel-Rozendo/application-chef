package application.chef.service;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public OutUser createUser(InUser inUser) {
        var saved = userRepository.save(new UserModel(inUser.getName(), inUser.getEmail(),
                inUser.getPassword()));

        return new OutUser(saved.getId(),
                saved.getName(),
                saved.getEmail());
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
