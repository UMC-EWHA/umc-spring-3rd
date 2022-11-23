package cje.umcAssignment.repository;

import cje.umcAssignment.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    /*
    * Post(게시글) 저장
    * */
    Post save(Post post);

    /*
    * id로 Post 보기
    * */
    Optional<Post> findById(Long id);

    /*
    * 모든 Post 보기
    * */
    List<Post> findAll();

    /*
    * writer로 Post 보기
    * */
    List<Post> findByWriter(String writer);

    /*
    * category로 Post 보기
    * */
    List<Post> findByCategory(String category);

    /*
    * id로 Post 삭제
    * */
    void deleteById(Long id);
}
