package ivan.zelezinski.lab.service.bookcase;

import ivan.zelezinski.lab.mapper.bookcase.BookcaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookcaseService {

    Page<BookcaseDto> findAll(Pageable pageable);

    BookcaseDto findByUuid(UUID uuid);

    BookcaseDto create(BookcaseDto dto);

    BookcaseDto update(UUID uuid, BookcaseDto dto);

    void delete(UUID uuid);

}
