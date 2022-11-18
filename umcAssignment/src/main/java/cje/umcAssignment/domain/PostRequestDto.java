package cje.umcAssignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PostRequestDto {
    private String title;

    private String writer;

    private String content;

    private String category;

    // requestDto -> Entity
    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .category(category).build();
    }
}