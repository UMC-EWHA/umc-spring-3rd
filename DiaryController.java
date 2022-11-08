package cje.SymDaDiary.controller;

import cje.SymDaDiary.domain.DiaryCreateRequestDto;
import cje.SymDaDiary.domain.DiaryResponseDto;
import cje.SymDaDiary.domain.MonthlyEmotionDiaryResponseDto;
import cje.SymDaDiary.domain.Question;
import cje.SymDaDiary.service.DiaryService;
import cje.SymDaDiary.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiaryController {

    // 의존성 주입 (생성자가 하나인 경우 생략 가능)
    private final DiaryService diaryService;
    private final QuestionService questionService;

    @Autowired
    public DiaryController(DiaryService diaryService, QuestionService questionService) {
        this.diaryService = diaryService;
        this.questionService = questionService;
    }

    /*
    * 일기 작성 - POST
    * */
    @ResponseBody   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
    @PostMapping("/diary/new")
    public DiaryResponseDto saveDiary(@RequestBody DiaryCreateRequestDto diaryCreateRequestDto){
        DiaryResponseDto diaryResponseDto = diaryService.keepDiary(diaryCreateRequestDto);
        return diaryResponseDto;
    }

    /*
    * id로 일기 삭제 - DELETE
    * */
    @ResponseBody
    @DeleteMapping("/diary/{diaryId}")
    public void deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId);
    }

    /*
    * date로 일기 삭제 - DELETE
    * */
    @ResponseBody   //@ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/diary/date/{date}/delete")
    public void deleteDiaryByDate(@PathVariable String date){
        diaryService.deleteDiaryByDate(date);
    }

    /*
     * 다이어리 수정하기 - PUT
     * */
    @ResponseBody
    @PutMapping("/diary/{diaryId}")
    public DiaryResponseDto updateDiary(@PathVariable Long diaryId, @RequestBody DiaryCreateRequestDto diaryCreateRequestDto){
        // 업데이트
        DiaryResponseDto diaryResponseDto = diaryService.updateDiary(diaryId, diaryCreateRequestDto);
        return diaryResponseDto;
    }

    /*
    * id로 일기 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/{diaryId}")
    public DiaryResponseDto getDiary(@PathVariable Long diaryId){
        DiaryResponseDto diary = diaryService.findDiary(diaryId);
        return diary;
    }

    /*
    * date(ex) 20220724) 로 일기 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/date/{date}")
    public DiaryResponseDto getDiaryByDate(@PathVariable String date){
        DiaryResponseDto diaryByDate = diaryService.findDiaryByDate(date);
        return diaryByDate;
    }

    /*
    * 월별 일기 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}")
    public List<DiaryResponseDto> getMonthlyDiary(@PathVariable String month){
        List<DiaryResponseDto> monthlyDiary = diaryService.findMonthlyDiary(month);
        return monthlyDiary;
    }

    /*
    * 월별 감정 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}/emotion")
    public List<MonthlyEmotionDiaryResponseDto> getMonthlyEmotion(@PathVariable String month){
        List<MonthlyEmotionDiaryResponseDto> monthlyEmotion = diaryService.findMonthlyEmotion(month);
        return monthlyEmotion;
    }

    /*
    * 식물 상태 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}/plant")
    public int getMonthlyPlant(@PathVariable String month){
        int plant = diaryService.cntMonthlyPlant(month);
        return plant;
    }

    /*
    * 오늘의 질문 조회 - GET
    * */
    @ResponseBody
    @GetMapping("/diary/new/question/{questionId}")
    public Question getTodayQuestion(@PathVariable Long questionId){
        Question question = questionService.findQuestion(questionId);
        return question;
    }

}
