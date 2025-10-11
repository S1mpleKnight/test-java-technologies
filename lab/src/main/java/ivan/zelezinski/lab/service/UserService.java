package ivan.zelezinski.lab.service;

import ivan.zelezinski.lab.mapper.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserDto findByUuid(UUID uuid);

    Page<UserDto> findAll(Pageable pageable);

    UserDto create(UserDto dto);

    UserDto update(UUID uuid, UserDto dto);

    void delete(UUID uuid);
}
