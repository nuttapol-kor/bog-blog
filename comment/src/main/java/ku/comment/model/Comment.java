package ku.comment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID blogId;
    private String username;
    private String commentText;
    private Instant createdAt;
    private int liked;
}
