package ivan.zelezinski.lab.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Bookcase extends UuidBaseEntity {

    private String name;

    private String shopName;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToMany(mappedBy = "uuid", fetch = FetchType.LAZY)
    private List<Book> books;
}
