package ivan.zelezinski.lab.repository.book;

import ivan.zelezinski.lab.domain.Book;
import ivan.zelezinski.lab.mapper.filter.BookFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CriteriaBookRepository {

    Page<Book> findAll(Pageable pageable, BookFilterDto filter);
}
