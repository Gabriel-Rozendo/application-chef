package application.chef.service;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.repository.UserRepository;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    public OutUser createUser(InUser inUser) {
        var hashPassword = passwordEncoder.encode(inUser.getPassword());
        //Salvando o userModel no banco
        var saved = userRepository.save(new UserModel(inUser.getName(), inUser.getEmail(),
                hashPassword));

        //Retornando o outUser
        return new OutUser(saved.getId(), saved.getName(), saved.getEmail());

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
        Optional<UserModel> userDataBase = userRepository.findById(id);

        if (userDataBase.isEmpty()) {
            throw new RuntimeException("Usuário não existe");
        }

        userDataBase.get().setName(inUser.getName());
        userDataBase.get().setEmail(inUser.getEmail());
        userDataBase.get().setPassword(inUser.getPassword());

        var userSaved = userRepository.save(userDataBase.get());

        return new OutUser(userSaved.getId(), userSaved.getName(), userSaved.getEmail());
    }
}
