package ivan.zelezinski.lab.controller;

import ivan.zelezinski.lab.mapper.bookcase.BookcaseDto;
import ivan.zelezinski.lab.service.bookcase.BookcaseService;
import ivan.zelezinski.lab.utils.Url;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Bookcase.BASE)
public class BookcaseController {
    private final BookcaseService bookcaseService;

    @GetMapping(Url.UUID)
    public BookcaseDto findByUuid(@PathVariable UUID uuid) {
        return bookcaseService.findByUuid(uuid);
    }

    @GetMapping
    public Page<BookcaseDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return bookcaseService.findAll(pageable);
    }

    @PostMapping
    public BookcaseDto create(@RequestBody @Valid BookcaseDto dto) {
        return bookcaseService.create(dto);
    }

    @PutMapping(Url.UUID)
    public BookcaseDto update(@PathVariable UUID uuid, @RequestBody BookcaseDto dto) {
        return bookcaseService.update(uuid, dto);
    }

    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID uuid) {
        bookcaseService.delete(uuid);
    }
}
