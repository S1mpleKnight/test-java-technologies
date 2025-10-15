package ivan.zelezinski.lab.mapper.bookcase;

import ivan.zelezinski.lab.mapper.UuidBaseDto;
import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public class BookcaseDto extends UuidBaseDto {

    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = Constants.NAME_REGEXP, message = "Invalid name")
    private String name;

    @NotBlank(message = "Shop name can not be empty")
    @Pattern(regexp = Constants.NAME_REGEXP, message = "Invalid shop name")
    private String shopName;

    private UUID userId;

    private List<BookDto> books;

}
