package ku.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class StatusRequest {

    private UUID commentId;
    private String username;

}
