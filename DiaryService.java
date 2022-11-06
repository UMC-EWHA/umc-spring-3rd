package cje.SymDaDiary.service;

import cje.SymDaDiary.domain.DiaryCreateRequestDto;
import cje.SymDaDiary.domain.DiaryResponseDto;
import cje.SymDaDiary.domain.MonthlyEmotionDiaryResponseDto;

import java.util.List;


public interface DiaryService {
    /*
    * 일기 작성
    * */
    public DiaryResponseDto keepDiary(DiaryCreateRequestDto diaryCreateRequestDto);

    /*
    * id로 일기 삭제
    * */
    public void deleteDiary(Long diary_id);

    /*
    * date로 일기 삭제
    * */
    public void deleteDiaryByDate(String date);

    /*
    * 일기 수정
    * */
    public DiaryResponseDto updateDiary(Long diaryId, DiaryCreateRequestDto diaryCreateRequestDto);

    /*
    * 개별 일기 조회 - id로
    * */
    public DiaryResponseDto findDiary(Long diary_id);

    /*
     * 개별 일기 조회 - date로
     * */
    public DiaryResponseDto findDiaryByDate(String date);

    /*
    * 월별 일기 조회
   * */
    public List<DiaryResponseDto> findMonthlyDiary(String month);

    /*
    * 월별 일기 감정 조회
    * */
    public List<MonthlyEmotionDiaryResponseDto> findMonthlyEmotion(String month);

    /*
    * 식물 상태 조회 (월별 일기 개수 조회)
    * */
    public int cntMonthlyPlant(String month);

}
