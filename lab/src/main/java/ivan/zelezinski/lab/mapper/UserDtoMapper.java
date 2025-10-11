package ivan.zelezinski.lab.mapper;

import ivan.zelezinski.lab.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper
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

    Page<UserDto> findAll(Page<User> users);
}
