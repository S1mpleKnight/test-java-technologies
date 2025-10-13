package ivan.zelezinski.lab.mapper;

import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class UuidBaseDto {

    private UUID uuid;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
