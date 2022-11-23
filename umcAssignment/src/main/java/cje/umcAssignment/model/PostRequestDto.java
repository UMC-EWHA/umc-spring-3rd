package cje.umcAssignment.model;

import lombok.AllArgsConstructor;
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