package ivan.zelezinski.lab.mapper.book;

import ivan.zelezinski.lab.domain.Book;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {BookcaseDtoMapper.class})
public interface BookDtoMapper {

    @Mapping(target = "bookcaseId", source = "bookcase.uuid")
    BookDto toDto(Book book);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "bookcase", ignore = true)
    Book dtoToEntity(BookDto dto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "bookcase", ignore = true)
    Book updateEntity(@MappingTarget Book book, BookDto dto);
}
