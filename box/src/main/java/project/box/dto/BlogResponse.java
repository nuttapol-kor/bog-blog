package project.box.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BlogResponse {
    private UUID id;
    private UUID blogId;
    private String title;
    private String content;
}
