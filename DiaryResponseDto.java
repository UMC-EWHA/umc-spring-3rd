package cje.SymDaDiary.domain;

import cje.SymDaDiary.constants.Emotion;
import cje.SymDaDiary.constants.Weather;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DiaryResponseDto {
    // id
    private Long diary_id;

    // content
    private String content;

    // weather
    private Weather weather;

    // createdAt
    private LocalDate createdAt;

    // month
    private String month;

    //date
    private String date;

    // emotion
    private Emotion emotion;

    // question
    private Question question;

    // comment
    private Comment comment;

    // -> user 만 빼고 리턴

    @Builder
    public DiaryResponseDto(Long diary_id, String content, Weather weather, LocalDate createdAt, String month, String date, Emotion emotion, Question question, Comment comment) {
        this.diary_id = diary_id;
        this.content = content;
        this.weather = weather;
        this.createdAt = createdAt;
        this.month = month;
        this.date = date;
        this.emotion = emotion;
        this.question = question;
        this.comment = comment;
    }

}
