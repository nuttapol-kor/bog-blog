package ku.comment.repository;

import ku.comment.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Status, UUID> {
    List<Status> findByCommentId(UUID commentId);
}
