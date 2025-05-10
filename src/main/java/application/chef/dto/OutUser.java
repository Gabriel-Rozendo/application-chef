package application.chef.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OutUser {
    private UUID id;
    private String name;
    private String email;

    public OutUser(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
