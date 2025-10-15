package ivan.zelezinski.lab.mapper.user;

import ivan.zelezinski.lab.domain.User;
import ivan.zelezinski.lab.mapper.book.BookDtoMapper;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {BookDtoMapper.class, BookcaseDtoMapper.class})
public interface UserDtoMapper {

    UserDto toDto(User user);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    User updateEntity(@MappingTarget User user, UserDto dto);

}
