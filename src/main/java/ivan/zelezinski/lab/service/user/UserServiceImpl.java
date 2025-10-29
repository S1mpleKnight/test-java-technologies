package ivan.zelezinski.lab.service.user;

import ivan.zelezinski.lab.domain.User;
import ivan.zelezinski.lab.exceptions.BadRequestException;
import ivan.zelezinski.lab.exceptions.NotFoundException;
import ivan.zelezinski.lab.mapper.user.UserDto;
import ivan.zelezinski.lab.mapper.user.UserDtoMapper;
import ivan.zelezinski.lab.repository.UserRepository;
import ivan.zelezinski.lab.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserDtoMapper userDtoMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto findByUuid(UUID uuid) {
        return userDtoMapper.toDto(findByUuidAndGet(uuid));
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        if (Utils.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return userRepository.findAll(pageable).map(userDtoMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto create(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("User with ${dto.email} already exists");
        }
        User user = userDtoMapper.toEntity(dto);
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        log.info("User with email: {} created", user.getEmail());
        return userDtoMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto update(UUID uuid, UserDto dto) {
        User user = findByUuidAndGet(uuid);
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("User with ${dto.email} already exists");
        }
        user = userDtoMapper.updateEntity(user, dto);
        user.setUpdatedDate(LocalDateTime.now());
        log.info("User with email: {} updated", user.getEmail());
        return userDtoMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        User user = findByUuidAndGet(uuid);
        log.info("User with email: {} deleted", user.getEmail());
        userRepository.delete(user);
    }

    private User findByUuidAndGet(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("User with ${uuid} not found"));
    }
}
