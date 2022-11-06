package cje.SymDaDiary.service;

import cje.SymDaDiary.domain.*;
import cje.SymDaDiary.repository.CommentRepository;
import cje.SymDaDiary.repository.DiaryRepository;
import cje.SymDaDiary.repository.QuestionRepository;
import cje.SymDaDiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService{
    // 의존성 주입
    private final DiaryRepository diaryRepository;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository, QuestionRepository questionRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.questionRepository = questionRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /*
     * 일기 작성
     * */
    @Override @Transactional
    public DiaryResponseDto keepDiary(DiaryCreateRequestDto diaryCreateRequestDto) {
        // Request DTO -> Entity
        Optional<User> userById = userRepository.findById(diaryCreateRequestDto.getUserId());
        User user = userById.orElseThrow();

        Optional<Question> questionById = questionRepository.findById(diaryCreateRequestDto.getQuestionId());
        Question question = questionById.orElseThrow();

        // 알맞은 코멘트 선택
        Optional<Comment> commentByEmotionAndWeather = commentRepository.findByEmotionAndWeather(diaryCreateRequestDto.getEmotion(), diaryCreateRequestDto.getWeather());
        Comment comment = commentByEmotionAndWeather.orElseThrow();

        Diary diary = diaryCreateRequestDto.toEntity(user, question);

         diary.giveCommentByEmotionAndWeather(comment);

        Diary savedDiary = diaryRepository.save(diary);

        DiaryResponseDto diaryResponseDto = savedDiary.diaryToDiaryResponseDto(savedDiary);


        return diaryResponseDto;
        // 다이어리 id 대신 DiaryResponseDto을 리턴
    }

    /*
     * id로 일기 삭제
     * */
    @Override @Transactional
    public void deleteDiary(Long diary_id) {
        diaryRepository.deleteById(diary_id);
    }

    /*
    * date로 일기 삭제
    * */
    @Override @Transactional
    public void deleteDiaryByDate(String date) {
        diaryRepository.deleteByDate(date);
    }

    /*
     * 개별 일기 조회 - id로
     * */
    @Override @Transactional
    public DiaryResponseDto findDiary(Long diary_id) {
        Optional<Diary> find_diary = diaryRepository.findById(diary_id);
        Diary diary = find_diary.orElseThrow();
        DiaryResponseDto diaryResponseDto = diary.diaryToDiaryResponseDto(diary);
        return diaryResponseDto;
    }

    /*
     * 개별 일기 조회 - date로
     * */
    @Override @Transactional
    public DiaryResponseDto findDiaryByDate(String date) {
        Optional<Diary> find_diaryByDate = diaryRepository.findByDate(date);
        Diary diary = find_diaryByDate.orElseThrow();
        DiaryResponseDto diaryResponseDto = diary.diaryToDiaryResponseDto(diary);
        return diaryResponseDto;
    }

    /*
     * 일기 수정
     * */

    @Override
    public DiaryResponseDto updateDiary(Long diaryId, DiaryCreateRequestDto diaryCreateRequestDto) {
        Diary diary = diaryRepository.findById(diaryId)
                        .orElseThrow(()->new IllegalArgumentException("해당 일기가 존재하지 않습니다."));

        diary.update(diaryCreateRequestDto);
        Comment commentByEmotionAndWeather = commentRepository.findByEmotionAndWeather(diary.getEmotion(), diary.getWeather())
                .orElseThrow(()-> new IllegalArgumentException());

        diary.giveCommentByEmotionAndWeather(commentByEmotionAndWeather);

        DiaryResponseDto diaryResponseDto = diary.diaryToDiaryResponseDto(diary);
        return diaryResponseDto;
    }

    /*
     * 월별 일기 조회
     * */
    @Override @Transactional
    public List<DiaryResponseDto> findMonthlyDiary(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);

        // Entity -> responseDto로 바꾸기
        List<DiaryResponseDto> monthlyDiary = new ArrayList<>();
        for (Diary diary: diary_byMonth){
            monthlyDiary.add(diary.diaryToDiaryResponseDto(diary));
        }
        return monthlyDiary;
    }

    /*
     * 월별 일기 감정 조회
     * */
    @Override @Transactional
    public List<MonthlyEmotionDiaryResponseDto> findMonthlyEmotion(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);

        // Entity -> responseDto로 바꾸기
        List<MonthlyEmotionDiaryResponseDto> monthlyDiary = new ArrayList<>();
        for (Diary diary: diary_byMonth) {
            monthlyDiary.add(diary.diaryToMonthlyEmotionDiaryResponseDto(diary));
        }

        return monthlyDiary;
    }

    /*
     * 식물 상태 조회 (월별 일기 개수 조회)
     * */
    @Override @Transactional
    public int cntMonthlyPlant(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);
        int plant = diary_byMonth.size();
        return plant;
    }


}
