package ivan.zelezinski.lab.controller;

import ivan.zelezinski.lab.mapper.UserDto;
import ivan.zelezinski.lab.service.UserService;
import ivan.zelezinski.lab.utils.Url;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(Url.User.BASE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(Url.UUID)
    public UserDto findByUuid(@PathVariable UUID uuid) {
        return userService.findByUuid(uuid);
    }

    @GetMapping
    public Page<UserDto> findAll(@RequestParam(required = true) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto dto) {
        return userService.create(dto);
    }

    @PutMapping(Url.UUID)
    public UserDto update(@RequestBody @Valid UserDto dto, @PathVariable UUID uuid) {
        return userService.update(uuid, dto);
    }

    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
    }
}
