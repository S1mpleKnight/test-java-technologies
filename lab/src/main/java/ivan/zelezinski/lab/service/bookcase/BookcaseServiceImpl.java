package ivan.zelezinski.lab.service.bookcase;

import ivan.zelezinski.lab.domain.Bookcase;
import ivan.zelezinski.lab.domain.User;
import ivan.zelezinski.lab.exceptions.BadRequestException;
import ivan.zelezinski.lab.exceptions.NotFoundException;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDto;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDtoMapper;
import ivan.zelezinski.lab.repository.BookcaseRepository;
import ivan.zelezinski.lab.repository.UserRepository;
import ivan.zelezinski.lab.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookcaseServiceImpl implements BookcaseService {
    private final BookcaseRepository bookcaseRepository;
    private final BookcaseDtoMapper bookcaseDtoMapper;
    private final UserRepository userRepository;

    @Override
    public Page<BookcaseDto> findAll(Pageable pageable) {
        if (Utils.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return bookcaseRepository.findAll(pageable).map(bookcaseDtoMapper::toDto);
    }

    @Override
    public BookcaseDto findByUuid(UUID uuid) {
        return bookcaseDtoMapper.toDto(findByUuidAndGet(uuid));
    }

    @Override
    @Transactional
    public BookcaseDto create(BookcaseDto dto) {
        if (bookcaseRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Bookcase with ${name} already exists");
        }
        Bookcase bookcase = bookcaseDtoMapper.toEntity(dto);
        if (!Utils.isNull(dto.getUser())) {
            if (!userRepository.existsById(dto.getUser().getUuid())){
                throw new BadRequestException("User with ${uuid} does not exist");
            } else {
                User user = userRepository.findById(dto.getUser().getUuid()).get();
                bookcase.setUser(user);
            }
        }
        return bookcaseDtoMapper.toDto(bookcaseRepository.save(bookcase));
    }

    @Override
    @Transactional
    public BookcaseDto update(UUID uuid, BookcaseDto dto) {
        Bookcase bookcase = findByUuidAndGet(uuid);
        if (bookcaseRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Bookcase with ${name} already exists");
        }
        bookcase = bookcaseDtoMapper.updateEntity(bookcase, dto);
        if (!Utils.isNull(dto.getUser())) {
            if (!userRepository.existsById(dto.getUser().getUuid())){
                throw new BadRequestException("User with ${uuid} does not exist");
            } else {
                User user = userRepository.findById(dto.getUser().getUuid()).get();
                bookcase.setUser(user);
            }
        }
        return bookcaseDtoMapper.toDto(bookcaseRepository.save(bookcase));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Bookcase bookcase = findByUuidAndGet(uuid);
        bookcaseRepository.delete(bookcase);
    }

    private Bookcase findByUuidAndGet(UUID uuid) {
        return bookcaseRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Bookcase with ${uuid} not found"));
    }
}
