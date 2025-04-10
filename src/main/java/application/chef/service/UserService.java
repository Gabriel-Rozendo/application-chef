package application.chef.service;

import application.chef.model.UserModel;
import application.chef.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserModel userModel) {
        return  userRepository.save(userModel);
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }
}
