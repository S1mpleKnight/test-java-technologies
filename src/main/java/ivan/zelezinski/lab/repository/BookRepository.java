package ivan.zelezinski.lab.repository;

import ivan.zelezinski.lab.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository <Book, UUID> {

    Boolean existsByName(String name);
}
