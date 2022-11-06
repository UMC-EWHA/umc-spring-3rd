package cje.SymDaDiary.domain;

import cje.SymDaDiary.constants.Emotion;
import cje.SymDaDiary.constants.Weather;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diary_id;  // 일기 pk

    @Column(length = 1000)
    private String content; // 일기 내용

    @Enumerated(EnumType.STRING)
    private Weather weather;    // 날씨

    @Column(name = "created_at")
    private LocalDate createdAt;   // 생성 시간
    @PrePersist // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행해라
    public void createdAt(){
        this.createdAt = LocalDate.now();
        setMonth(createdAt);
        setDate(createdAt);
    }

    private String month;   // 연월
    public void setMonth(LocalDate createdAt) {
        String year = Integer.toString(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        // %(명령 시작) 0(채워질 문자) 2(총 자리수) d(십진정수)
        this.month = year+ month;
    }

    private String date;    // 연월일 20220724
    public void setDate(LocalDate createdAt){
        String year = Integer.toString(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        String day = String.format("%02d", createdAt.getDayOfMonth());

        this.date = year + month + day;
    }

    @Enumerated(EnumType.STRING)
    private Emotion emotion;    // 감정

    // 다대일 단방향 관계
    @ManyToOne  // 유저(일) - 일기(다)
    @JoinColumn(name = "user_id")
    private User user;  // 유저 pk (FK)

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;  // 질문 pk (FK)

    @ManyToOne  // 코멘트(일) - 일기(다)
    @JoinColumn(name = "comment_id")
    private Comment comment;    // 코멘트 pk (FK)

    // entity를 무분별하게 수정하지 못하도록 메서드에 이름 붙이기
    public void giveCommentByEmotionAndWeather(Comment comment) {    // 일기의 weather&emotion으로 코멘트 정하기
        this.comment = comment;
    }

    @Builder
    public Diary(String content, Weather weather, LocalDate createdAt, String month, String date, Emotion emotion, User user, Question question, Comment comment) {
        this.content = content;
        this.weather = weather;
        this.createdAt = createdAt;
        this.month = month;
        this.date = date;
        this.emotion = emotion;
        this.user = user;
        this.question = question;
        this.comment = comment;
    }

    public void update(DiaryCreateRequestDto diaryCreateRequestDto){
        this.content = diaryCreateRequestDto.getContent();
        this.weather = diaryCreateRequestDto.getWeather();
        this.emotion = diaryCreateRequestDto.getEmotion();
    }

    // Diary -> DiaryResponseDto
    public DiaryResponseDto diaryToDiaryResponseDto(Diary diary){
        return DiaryResponseDto.builder()
                .diary_id(diary_id)
                .content(content)
                .weather(weather)
                .createdAt(createdAt)
                .month(month)
                .date(date)
                .emotion(emotion)
                .question(question)
                .comment(comment).build();
    }

    // Diary -> MonthlyEmotionDiaryResponseDto
    public MonthlyEmotionDiaryResponseDto diaryToMonthlyEmotionDiaryResponseDto(Diary diary){
        return MonthlyEmotionDiaryResponseDto.builder()
                .diary_id(diary_id)
                .date(date)
                .emotion(emotion).build();
    }
}