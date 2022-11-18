package cje.umcAssignment.repository;

import cje.umcAssignment.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository{
    @Override
    List<Post> findByWriter(String writer);

    @Override
    List<Post> findByCategory(String category);
}
