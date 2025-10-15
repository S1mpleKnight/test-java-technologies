package ivan.zelezinski.lab.mapper.filter;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@ToString
public class BookFilterDto {

    private List<UUID> bookIds;

    private List<UUID> bookcaseIds;

    private List<UUID> userIds;

    private String name;

    private LocalDateTime fromReleaseDateTime;

    private LocalDateTime toReleaseDateTime;

    private LocalDateTime fromCreateDateTime;

    private LocalDateTime toCreateDateTime;

    private LocalDateTime fromUpdateDateTime;

    private LocalDateTime toUpdateDateTime;
}
