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
public class Status {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID commentId;
    private String username;
}

