package umc.crud.src.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.crud.config.BaseException;
import umc.crud.config.BaseResponseStatus;
import umc.crud.src.board.model.*;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    /**
     * 모든 글 조회
     * @return
     */
    public List<GetBoardRes> getAllBoards() {

        return boardDao.getAllBoards();
    }

    /**
     * 특정 글 조회
     * @param writer
     * @return
     */
    public List<GetBoardRes> getBoardByWriter(String writer) {

        return boardDao.getBoardByWriter(writer);
    }

    /**
     * 글 생성
     * @param postBoardReq
     * @return
     * @throws BaseException
     */
    public PostBoardRes writeBoard(PostBoardReq postBoardReq) throws BaseException{

        try {
            int boardId = boardDao.writeBoard(postBoardReq);
            return new PostBoardRes(boardId);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 글 내용 수정
     * @param putBoardReq
     * @throws BaseException
     */
    public void modifyContent(PutBoardReq putBoardReq) throws BaseException {
        try {
            int result = boardDao.modifyContent(putBoardReq);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.MODIFY_FAIL_CONTENT);
            }
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 글 삭제
     * @param boardId
     */
    public void deleteBoard(int boardId) {
        boardDao.deleteBoard(boardId);
    }

}