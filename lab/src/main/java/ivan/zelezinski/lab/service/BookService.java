package ivan.zelezinski.lab.service;

import ivan.zelezinski.lab.mapper.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    Page<BookDto> findAllBooks(Pageable pageable);

    BookDto create(BookDto dto);

    BookDto update(UUID uuid, BookDto dto);

    void delete(UUID uuid);

    BookDto findByUuid(UUID uuid);
}
