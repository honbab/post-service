package startspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import startspring.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
