package project.box.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class BlogRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
