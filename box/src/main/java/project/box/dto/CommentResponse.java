package project.box.dto;

import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
public class CommentResponse {
    private UUID id;
    private UUID blogId;
    private String username;
    private String commentText;
    private Instant createdAt;
    private int liked;
}
