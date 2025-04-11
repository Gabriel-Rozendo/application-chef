package application.chef.service;

import application.chef.dto.InUser;
import application.chef.dto.OutUser;
import application.chef.model.UserModel;
import application.chef.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public OutUser createUser(InUser inUser) {
        //Salvando o userModel no banco
        var saved = userRepository.save(new UserModel(inUser.getName(), inUser.getEmail(),
                inUser.getPassword()));

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
}
