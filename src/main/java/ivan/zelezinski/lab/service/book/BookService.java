package ivan.zelezinski.lab.service.book;

import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.mapper.filter.BookFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    Page<BookDto> findAllBooks(Pageable pageable, BookFilterDto fiilter);

    BookDto create(BookDto dto);

    BookDto update(UUID uuid, BookDto dto);

    void delete(UUID uuid);

    BookDto findByUuid(UUID uuid);
}
