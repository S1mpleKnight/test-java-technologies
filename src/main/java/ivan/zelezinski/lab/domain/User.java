package ivan.zelezinski.lab.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "base_user")
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends UuidBaseEntity {

    private String firstName;

    private String secondName;

    private String email;

    //todo: encrypt password
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bookcase> bookcases;
}
