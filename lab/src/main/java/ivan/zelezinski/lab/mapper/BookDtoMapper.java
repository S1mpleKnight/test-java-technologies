package ivan.zelezinski.lab.mapper;

import ivan.zelezinski.lab.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper
public interface BookDtoMapper {

    BookDto toDto(Book book);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Book dtoToEntity(BookDto dto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Book updateEntity(@MappingTarget Book book, BookDto dto);

    Page<BookDto> getDtos(Page<Book> books);
}
