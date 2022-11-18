package cje.umcAssignment.service;

import cje.umcAssignment.domain.PostRequestDto;
import cje.umcAssignment.domain.PostResponseDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    /*
     * Post(게시글) 저장
     * */
    public PostResponseDto save(PostRequestDto postRequestDto);

    /*
     * id로 Post 보기
     * */
    public PostResponseDto findById(Long id);

    /*
     * 모든 Post 보기
     * */
    public List<PostResponseDto> findAll();

    /*
     * writer로 Post 보기
     * */
    public List<PostResponseDto> findByWriter(String writer);

    /*
     * category로 Post 보기
     * */
    public List<PostResponseDto> findByCategory(String category);

    /*
     * id로 Post 삭제
     * */
    public void deleteById(Long id);

    /*
    * Post 수정
    * */
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto);
}
