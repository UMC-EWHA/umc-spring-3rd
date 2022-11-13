package com.umc.umcserver.src.post.service;

import com.umc.umcserver.src.post.dto.*;
import com.umc.umcserver.src.post.repository.Board;
import com.umc.umcserver.src.post.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
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

    // 게시글 제거
    @Transactional
    public void deleteBoard(Long postId) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        boardRepository.delete(board);
    }

    // 데이터 업데이트
    @Transactional
    public UpdatePostsResDto updateBoard(Long postId, UpdatePostsReqDto requestDto) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
        return new UpdatePostsResDto(board);
    }

    // 일부 정보 업데이트
    @Transactional
    public UpdatePostsResDto PatchBoard(Long postId, UpdatePostsReqDto requestDto){
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다"));
        List<UpdatePostsReqDto> lists = new ArrayList<>();
        lists.add(requestDto);

        for (UpdatePostsReqDto list : lists) { // 업데이트 사항인것만 반영해주기
            if (list.getTitle() != null)
                board.updateTitle(list.getTitle());
            if (list.getWriter() != null)
                board.updateWriter(list.getWriter());
            if (list.getContent() != null)
                board.updateContent(list.getContent());
        }

        return new UpdatePostsResDto(board);
    }


}
