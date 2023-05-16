package project.box.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class StatusRequest {
    private UUID commentId;
    private String username;
}
