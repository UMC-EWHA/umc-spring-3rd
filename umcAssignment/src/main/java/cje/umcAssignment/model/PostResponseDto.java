package cje.umcAssignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PostResponseDto {
    private String title;

    private String writer;

    private String content;

    private String category;

    @Builder
    public PostResponseDto(String title, String writer, String content, String category) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.category = category;
    }
}
