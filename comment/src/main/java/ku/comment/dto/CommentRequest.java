package ku.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class CommentRequest {

    private UUID blogId;

    @NotBlank
    private String username;

    @NotBlank
    private String commentText;
}

