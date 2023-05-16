package ku.comment.service;

import ku.comment.dto.CommentRequest;
import ku.comment.dto.StatusRequest;
import ku.comment.dto.CommentResponse;
import ku.comment.model.Comment;
import ku.comment.model.Status;
import ku.comment.repository.CommentRepository;
import ku.comment.repository.LikeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public List<Status> getStatusAll() {
        return likeRepository.findAll();
    }

    public List<CommentResponse> getAllCommentsForBlog(UUID blogId) {
        List<Comment> comments = repository.findByBlogId(blogId);

        List<CommentResponse> dtos = comments
                .stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public void addComment(CommentRequest commentRequest) {
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setCreatedAt(Instant.now());
        comment.setLiked(0);
        repository.save(comment);
    }

    public void like(StatusRequest statusRequest) {
        Status status = modelMapper.map(statusRequest, Status.class);
        List<Status> statuses = likeRepository.findByCommentId(status.getCommentId());
        List<Comment> comments = repository.findAll();
        Comment comment = comments.get(0);
        for (Comment i: comments) {
            comment = i;
            if (comment.getId().equals(status.getCommentId())) {
                break;
            }
        }
        String name = "";
        for (Status i: statuses) {
            if (name.equals(status.getUsername())) {
                break;
            }
            name = i.getUsername();
        }
        if (name.equals(status.getUsername())) {
            for (Status i: likeRepository.findByCommentId(status.getCommentId())) {
                if (i.getUsername().equals(status.getUsername())) {
                    likeRepository.delete(i);
                    break;
                }
            }
            comment.setLiked(comment.getLiked()-1);
            repository.save(comment);
        }
        else {
            likeRepository.save(status);
            comment.setLiked(comment.getLiked()+1);
            repository.save(comment);
        }
        repository.save(comment);
    }
}
