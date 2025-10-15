package ivan.zelezinski.lab.service.book;

import ivan.zelezinski.lab.domain.Book;
import ivan.zelezinski.lab.domain.Bookcase;
import ivan.zelezinski.lab.exceptions.BadRequestException;
import ivan.zelezinski.lab.exceptions.NotFoundException;
import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.mapper.book.BookDtoMapper;
import ivan.zelezinski.lab.mapper.filter.BookFilterDto;
import ivan.zelezinski.lab.repository.book.BookRepository;
import ivan.zelezinski.lab.repository.BookcaseRepository;
import ivan.zelezinski.lab.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;
    private final BookcaseRepository bookcaseRepository;

    @Override
    public Page<BookDto> findAllBooks(Pageable pageable, BookFilterDto filter) {
        if (Utils.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        if (Objects.nonNull(filter)) {
            return bookRepository.findAll(pageable, filter).map(bookDtoMapper::toDto);
        }
        return bookRepository.findAll(pageable).map(bookDtoMapper::toDto);
    }

    @Transactional
    @Override
    public BookDto create(BookDto dto) {
        if (bookRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Book with ${dto.name} exists");
        }
        Book book = bookDtoMapper.dtoToEntity(dto);
        if (!Utils.isNull(dto.getBookcaseId())) {
            Bookcase bookcase = bookcaseRepository.findById(dto.getBookcaseId())
                    .orElseThrow(() -> new NotFoundException("Bookcase with id: ${uuid} not found"));
            book.setBookcase(bookcase);
        }
        book.setCreatedDate(LocalDateTime.now());
        book.setUpdatedDate(LocalDateTime.now());
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public BookDto update(UUID uuid, BookDto dto) {
        Book book = findByUuidAndGet(uuid);
        if (!book.getName().equals(dto.getName())) {
            if (bookRepository.existsByName(dto.getName())) {
                throw new BadRequestException("Book with ${dto.name} exists");
            }
        }
        if (!Utils.isNull(dto.getBookcaseId())) {
            Bookcase bookcase = bookcaseRepository.findById(dto.getBookcaseId())
                    .orElseThrow(() -> new NotFoundException("Bookcase with id: ${uuid} not found"));
            book.setBookcase(bookcase);
        }
        book.setUpdatedDate(LocalDateTime.now());
        book = bookDtoMapper.updateEntity(book, dto);
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Transactional
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
