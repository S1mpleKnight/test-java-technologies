package ivan.zelezinski.lab.mapper.user;

import ivan.zelezinski.lab.mapper.UuidBaseDto;
import ivan.zelezinski.lab.mapper.bookcase.BookcaseDto;
import ivan.zelezinski.lab.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public class UserDto extends UuidBaseDto {

    @NotBlank(message = "First name can not be empty")
    @Pattern(regexp = Constants.NAME_REGEXP, message = "Invalid first name")
    private String firstName;

    @NotBlank(message = "Second name can not be empty")
    @Pattern(regexp = Constants.NAME_REGEXP, message = "Invalid second name")
    private String secondName;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid password")
    private String password;

    private List<BookcaseDto> bookcases;
}
