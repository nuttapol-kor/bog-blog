package project.box.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CommentRequest {
    private UUID blogId;
    private String username;
    private String commentText;
}
