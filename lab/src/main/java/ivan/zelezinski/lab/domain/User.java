package ivan.zelezinski.lab.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends UuidBaseEntity {

    private String firstName;

    private String secondName;

    private String email;

    private String password;

    @OneToMany(mappedBy = "uuid")
    private List<Bookcase> bookcases;
}
