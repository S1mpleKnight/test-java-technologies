package ivan.zelezinski.lab.repository;

import ivan.zelezinski.lab.domain.Book;
import ivan.zelezinski.lab.domain.Bookcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookcaseRepository extends JpaRepository<Bookcase, UUID> {
}
