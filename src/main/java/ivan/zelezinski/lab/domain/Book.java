package ivan.zelezinski.lab.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends UuidBaseEntity {

    private String name;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "bookcase_uuid")
    private Bookcase bookcase;
}
