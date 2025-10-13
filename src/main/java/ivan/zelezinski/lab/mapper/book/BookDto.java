package ivan.zelezinski.lab.mapper.book;

import ivan.zelezinski.lab.mapper.UuidBaseDto;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDto;
import ivan.zelezinski.lab.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public class BookDto extends UuidBaseDto {

    @NotBlank
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = Constants.DATE_REGEXP)
    private LocalDate releaseDate;

    private BookcaseDto bookcase;
}
