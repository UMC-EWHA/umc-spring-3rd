package com.umc.umcserver.controller;

import com.umc.umcserver.dto.*;
import com.umc.umcserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<CreatePostsResDto> CreateBoard(@RequestBody CreatePostsReqDto requestDto) {

        try {
            CreatePostsResDto responseDto;
            if (requestDto.getTitle() != null) {
                responseDto = boardService.createBoard(requestDto);
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // 게시물 전체 조회
    @GetMapping
    public List<FetchPostsResDto> FetchAllBoard() {
        return boardService.fetchAllBoard();

    }

    // id에 해당하는 게시글 수정
    @GetMapping("/{postId}")
    public ResponseEntity<FetchPostsResDto> findBoard(@PathVariable Long postId) {

        FetchPostsResDto responseDto = boardService.fetchBoard(postId);
        return ResponseEntity.ok(responseDto);
    }

    // id에 해당하는 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<UpdatePostsResDto> updateBoard(@PathVariable Long postId, @RequestBody UpdatePostsReqDto requestDto) {
        UpdatePostsResDto responseDto = boardService.updateBoard(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deleteBoard(@PathVariable Long postId){
        boardService.deleteBoard(postId);
        return ResponseEntity.ok(postId);
    }
}


