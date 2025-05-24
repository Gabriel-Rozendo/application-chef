package application.chef.validation;

import application.chef.dto.InUser;
import org.springframework.stereotype.Component;

import static io.micrometer.common.util.StringUtils.isEmpty;


@Component
public class UserValidator {

    private static void validateName(String name){
        if (isEmpty(name)){
            throw new RuntimeException("Por favor inserir um nome");
        }
    }

    private static void validatePassword(String password){
        if (isEmpty(password)){
            throw new RuntimeException("Por favor inserir uma senha");
        }
    }

    private static void validateEmail(String email){
        //pode utilizar o endsWith para tratar o email
        if (isEmpty(email)){
            throw new RuntimeException("Por favor inserir um email");
        }
    }


    public static void validateUserFields(InUser inUser){
        validateName(inUser.getName());
        validatePassword(inUser.getPassword());
    }

}
