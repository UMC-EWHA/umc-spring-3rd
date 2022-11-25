package com.umc.umcserver.domain.board.controller;

import com.umc.umcserver.domain.board.dto.*;
import com.umc.umcserver.domain.board.service.BoardService;
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
    public ResponseEntity<CreatePostsResDto> createBoard(@RequestBody CreatePostsReqDto requestDto) {

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
    public List<FetchPostsResDto> fetchAllBoard(@RequestBody FetchPostsReqDto requestDto) {
        return boardService.fetchAllBoard(requestDto);

    }

    // id에 해당하는 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<FetchPostsResDto> findBoard(@PathVariable Long postId) {

        FetchPostsResDto responseDto = boardService.fetchBoard(postId);
        return ResponseEntity.ok(responseDto);
    }

    // query string 이용하여 해당 title 갖는 게시글 조회하기
    @GetMapping("/query")
    public ResponseEntity<List<FetchPostsResDto>> findByTitleBoard(@RequestParam(required = false) String title) {
        try {
            List<FetchPostsResDto> responseDto = null;
            if (title == null) {
                System.out.println("받은 쿼리 없음");
                //responseDto = boardService.fetchAllBoard();
            } else {
                responseDto = boardService.fetchByTitleBoard(title);
            }
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // id에 해당하는 게시글 데이터 전체 수정
    @PutMapping("/{postId}")
    public ResponseEntity<UpdatePostsResDto> updateBoard(@PathVariable Long postId, @RequestBody UpdatePostsReqDto requestDto) {
        try {
            UpdatePostsResDto responseDto;
            if (requestDto.getTitle() == null && requestDto.getWriter() == null && requestDto.getWriter() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                responseDto = boardService.updateBoard(postId, requestDto);
                return ResponseEntity.ok(responseDto);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 일부 정보 업데이트
    @PatchMapping("/{postId}")
    public ResponseEntity<UpdatePostsResDto> patchUpdateBoard(@PathVariable Long postId, @RequestBody UpdatePostsReqDto requestDto) {
        try {
            UpdatePostsResDto responseDto;
            if (requestDto.getTitle() == null && requestDto.getWriter() == null && requestDto.getWriter() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                responseDto = boardService.PatchBoard(postId, requestDto);
                return ResponseEntity.ok(responseDto);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deleteBoard(@PathVariable Long postId) {
        boardService.deleteBoard(postId);
        return ResponseEntity.ok(postId);
    }
}


