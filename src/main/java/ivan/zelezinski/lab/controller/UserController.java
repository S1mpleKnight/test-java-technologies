package ivan.zelezinski.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ivan.zelezinski.lab.mapper.user.UserDto;
import ivan.zelezinski.lab.service.user.UserService;
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
@Tag(name = "User controller", description = "User management API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Find user by id")
    @GetMapping(Url.UUID)
    public UserDto findByUuid(@PathVariable UUID uuid) {
        return userService.findByUuid(uuid);
    }

    @Operation(summary = "Find all users")
    @GetMapping
    public Page<UserDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @Operation(summary = "Create user")
    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto dto) {
        return userService.create(dto);
    }

    @Operation(summary = "Update user by id")
    @PutMapping(Url.UUID)
    public UserDto update(@RequestBody @Valid UserDto dto, @PathVariable UUID uuid) {
        return userService.update(uuid, dto);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
    }
}
