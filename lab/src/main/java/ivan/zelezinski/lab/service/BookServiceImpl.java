package ivan.zelezinski.lab.service;

import ivan.zelezinski.lab.domain.Book;
import ivan.zelezinski.lab.exceptions.BadRequestException;
import ivan.zelezinski.lab.exceptions.NotFoundException;
import ivan.zelezinski.lab.mapper.BookDto;
import ivan.zelezinski.lab.mapper.BookDtoMapper;
import ivan.zelezinski.lab.repository.BookRepository;
import ivan.zelezinski.lab.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    @Override
    public Page<BookDto> findAllBooks(Pageable pageable) {
        if (Utils.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return bookDtoMapper.getDtos(bookRepository.findAll(pageable));
    }

    @Override
    public BookDto create(BookDto dto) {
        if (bookRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Book with ${dto.name} exists");
        }
        Book book = bookDtoMapper.dtoToEntity(dto);
        book.setCreatedDate(LocalDateTime.now());
        book.setUpdatedDate(LocalDateTime.now());
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(UUID uuid, BookDto dto) {
        Book book = findByUuidAndGet(uuid);
        if (!book.getName().equals(dto.getName())) {
            if (bookRepository.existsByName(dto.getName())) {
                throw new BadRequestException("Book with ${dto.name} exists");
            }
        }
        book.setUpdatedDate(LocalDateTime.now());
        book = bookDtoMapper.updateEntity(book, dto);
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(UUID uuid) {
        Book book = findByUuidAndGet(uuid);
        bookRepository.delete(book);
    }

    @Override
    public BookDto findByUuid(UUID uuid) {
        return bookDtoMapper.toDto(findByUuidAndGet(uuid));
    }

    private Book findByUuidAndGet(UUID uuid) {
        return bookRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Book with ${uuid} not found"));
    }
}
