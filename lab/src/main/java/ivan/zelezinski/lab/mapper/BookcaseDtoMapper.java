package ivan.zelezinski.lab.mapper;

import ivan.zelezinski.lab.domain.Bookcase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper
public interface BookcaseDtoMapper {

    BookcaseDto toDto(Bookcase bookcase);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Bookcase toEntity(BookcaseDto dto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Bookcase updateEntity(@MappingTarget Bookcase entity, BookcaseDto dto);

    Page<BookcaseDto> findAll(Page<Bookcase> bookcases);
}
