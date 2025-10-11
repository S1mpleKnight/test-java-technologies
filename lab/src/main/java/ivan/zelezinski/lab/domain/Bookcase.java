package ivan.zelezinski.lab.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name = "uuid")
    private User owner;

    @OneToMany(mappedBy = "uuid")
    private List<Book> books;
}
