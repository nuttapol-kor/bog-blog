package ku.comment.controller;

import ku.comment.dto.CommentRequest;
import ku.comment.dto.CommentResponse;
import ku.comment.dto.StatusRequest;
import ku.comment.model.Comment;
import ku.comment.model.Status;
import ku.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public List<Comment> getAll() {
        return service.getAll();
    }

    @GetMapping("/status")
    public List<Status> getStatusAll() {
        return service.getStatusAll();
    }

    @GetMapping("/{id}")
    public List<CommentResponse> getAllCommentsForBlog(@PathVariable UUID id) {
        return service.getAllCommentsForBlog(id);
    }

    @PostMapping("/like")
    public void like(@Valid @RequestBody StatusRequest status) {
        service.like(status);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CommentRequest comment,
                                 BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<String>("Invalid request format", HttpStatus.UNPROCESSABLE_ENTITY);

        service.addComment(comment);
        return new ResponseEntity<CommentRequest>(comment, HttpStatus.OK);
    }

}
