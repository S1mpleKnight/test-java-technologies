package ivan.zelezinski.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Bookcase controller", description = "Bookcase management API")
public class BookcaseController {
    private final BookcaseService bookcaseService;

    @Operation(summary = "Find bookcase")
    @GetMapping(Url.UUID)
    public BookcaseDto findByUuid(@PathVariable UUID uuid) {
        return bookcaseService.findByUuid(uuid);
    }

    @Operation(summary = "Find all bookcases")
    @GetMapping
    public Page<BookcaseDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return bookcaseService.findAll(pageable);
    }

    @Operation(summary = "Create bookcase")
    @PostMapping
    public BookcaseDto create(@RequestBody @Valid BookcaseDto dto) {
        return bookcaseService.create(dto);
    }

    @Operation(summary = "Update bookcase by id")
    @PutMapping(Url.UUID)
    public BookcaseDto update(@PathVariable UUID uuid, @RequestBody BookcaseDto dto) {
        return bookcaseService.update(uuid, dto);
    }

    @Operation(summary = "Delete bookcase by id")
    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID uuid) {
        bookcaseService.delete(uuid);
    }
}
