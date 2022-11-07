package com.umc.umcserver.service;

import com.umc.umcserver.dto.*;
import com.umc.umcserver.repository.Board;
import com.umc.umcserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시물 생성
    @Transactional
    public CreatePostsResDto createBoard(CreatePostsReqDto requestDto) {
        Board board = boardRepository.save(requestDto.toEntity()); //게시물 저장
        return new CreatePostsResDto(board.getId());
    }

    // 모든 데이터 조회
    @Transactional
    public List<FetchPostsResDto> fetchAllBoard() {
        List<Board> list = boardRepository.findAll();
        return list.stream().map(FetchPostsResDto::new).collect(Collectors.toList());
    }

    // 특정 id에 대한 데이터 조회
    @Transactional
    public FetchPostsResDto fetchBoard(Long postId) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        return new FetchPostsResDto(board);
    }

    // 데이터 업데이트
    @Transactional
    public UpdatePostsResDto updateBoard(Long postId, UpdatePostsReqDto requestDto) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
        return new UpdatePostsResDto(board);
    }

    // 게시글 제거
    @Transactional
    public void deleteBoard(Long postId){
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        boardRepository.delete(board);
    }



}
