package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/boards")
public class BoardController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final BoardProvider boardProvider;
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final JwtService jwtService;

    public BoardController(BoardProvider boardProvider, BoardService boardService, JwtService jwtService) {
        this.boardProvider = boardProvider;
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    /*
    *게시글 작성 API
    * [POST] / boards
    */
    @ResponseBody
    @PostMapping("/post")
    public BaseResponse<PostBoardRes> uploadBoard(@RequestBody PostBoardReq postBoardReq) {

        if (postBoardReq.getTitle() == null) {
            return new BaseResponse<>(POST_BOARDS_EMPTY_TITLE);
        }
        if (postBoardReq.getContent() == null) {
            return new BaseResponse<>(POST_BOARDS_EMPTY_CONTENT);
        }
        PostBoardRes postBoardRes = boardService.uploadBoard(postBoardReq);

        return new BaseResponse<>(postBoardRes);
    }

    /*
     *게시글 리스트 조회 API
     * [GET] / boards
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetPostRes>> getPosts(@RequestParam(required = false) String writer,
                                                   @RequestParam(required = false) int page){ // query string으로 page 전달받음
        try{
            // 전달받은 page값 입력
            Pagination pagination = new Pagination();
            pagination.setPage(page); 

            if(writer == null){
                List<GetPostRes> getPostsRes = boardProvider.getPosts(pagination); 
                return new BaseResponse<>(getPostsRes);
            }
            List<GetPostRes> getPostRes = boardProvider.getPostByWriter(writer);
            return new BaseResponse<>(getPostRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /*
     *게시글 내용 수정 API
     * [PATCH] / boards
     */
   @ResponseBody
   @PatchMapping("/{postIdx}")
    public BaseResponse<String> modifyPostContent(@PathVariable("postIdx") int postIdx, @RequestBody Post post) {
        try {
            PatchPostReq patchPostReq = new PatchPostReq(postIdx, post.getContent());
            boardService.modifyPostContent(patchPostReq);

            String result = "게시글이 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
