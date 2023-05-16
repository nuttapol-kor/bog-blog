package project.box.model;
import project.box.validation.PasswordValueMatch;
import project.box.config.AttributeEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = AttributeEncryptor.class)
    private String username;

    private String password;

//    private String confirmPassword;

    @Convert(converter = AttributeEncryptor.class)
    private String firstName;

    @Convert(converter = AttributeEncryptor.class)
    private String lastName;

    @Convert(converter = AttributeEncryptor.class)
    private String email;

    private String role;
    private Instant createdAt;
}
