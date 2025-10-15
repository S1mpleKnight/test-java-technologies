package ivan.zelezinski.lab.repository.book;

import ivan.zelezinski.lab.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository <Book, UUID> , CriteriaBookRepository {

    Boolean existsByName(String name);
}
