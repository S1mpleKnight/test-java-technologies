package ivan.zelezinski.lab.mapper.bookcase;

import ivan.zelezinski.lab.domain.Bookcase;
import ivan.zelezinski.lab.mapper.book.BookDtoMapper;
import ivan.zelezinski.lab.mapper.user.UserDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class, BookDtoMapper.class})
public interface BookcaseDtoMapper {

    @Mapping(target = "userId", source = "user.uuid")
    BookcaseDto toDto(Bookcase bookcase);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    Bookcase toEntity(BookcaseDto dto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    Bookcase updateEntity(@MappingTarget Bookcase entity, BookcaseDto dto);
}
