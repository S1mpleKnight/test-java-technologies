package ivan.zelezinski.lab.controller;

import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.mapper.filter.BookFilterDto;
import ivan.zelezinski.lab.service.book.BookService;
import ivan.zelezinski.lab.utils.Url;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(Url.Book.BASE)
@RequiredArgsConstructor
public class BookController {

    private final BookService service;


    @GetMapping(Url.UUID)
    public BookDto getBook(@PathVariable UUID uuid) {
        return service.findByUuid(uuid);
    }

    @GetMapping
    public Page<BookDto> getBooks(
            @RequestParam(required = false)Pageable pageable, BookFilterDto filter
    ) {
        return service.findAllBooks(pageable, filter);
    }

    @PostMapping
    public BookDto create(@RequestBody @Valid BookDto dto) {
        return service.create(dto);
    }

    @PutMapping(Url.UUID)
    public BookDto update(@RequestBody @Valid BookDto dto, @PathVariable UUID uuid) {
        return service.update(uuid, dto);
    }

    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID uuid) {
        service.delete(uuid);
    }

}
