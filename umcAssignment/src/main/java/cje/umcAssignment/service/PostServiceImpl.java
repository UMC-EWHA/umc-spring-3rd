package cje.umcAssignment.service;

import cje.umcAssignment.model.Post;
import cje.umcAssignment.model.PostRequestDto;
import cje.umcAssignment.model.PostResponseDto;
import cje.umcAssignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Autowired  // 의존성 주입
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto) {
        // requestDto -> entity
        Post post = postRequestDto.toEntity();
        // entity save
        Post savedPost = postRepository.save(post);
        // entity -> responseDto
        PostResponseDto postResponseDto = savedPost.toPostResponseDto(savedPost);

        return postResponseDto;
    }

    @Override @Transactional
    public PostResponseDto findById(Long id) {
        // id로 Post 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 Post가 없습니다."));
        // entity -> responseDto
        PostResponseDto postResponseDto = post.toPostResponseDto(post);

        return postResponseDto;
    }

    @Override @Transactional
    public List<PostResponseDto> findAll() {
        // 모든 Post 찾기
        List<Post> posts = postRepository.findAll();

        // entity -> responseDto
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtos.add(post.toPostResponseDto(post));
        }

        return postResponseDtos;
    }

    @Override @Transactional
    public List<PostResponseDto> findByWriter(String writer) {
        // writer로 Post 찾기
        List<Post> posts = postRepository.findByWriter(writer);

        // entity -> responseDto
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtos.add(post.toPostResponseDto(post));
        }

        return postResponseDtos;
    }

    @Override @Transactional
    public List<PostResponseDto> findByCategory(String category) {
        // category로 Post 찾기
        List<Post> posts = postRepository.findByCategory(category);

        // entity -> responseDto
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtos.add(post.toPostResponseDto(post));
        }

        return postResponseDtos;
    }

    @Override @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        // id로 post 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 Post가 없습니다."));
        // 수정하기
        post.setCategory(postRequestDto.getCategory());
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setWriter(postRequestDto.getWriter());

        // entity -> responseDto
        PostResponseDto postResponseDto = post.toPostResponseDto(post);

        return postResponseDto;
    }
}
