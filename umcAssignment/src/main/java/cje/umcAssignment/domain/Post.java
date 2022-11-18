package cje.umcAssignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter @AllArgsConstructor
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String title;

    private String writer;

    private String content;

    private String category;

    @Builder
    public Post(String title, String writer, String content, String category) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.category = category;
    }

    // Post -> responseDto
    public PostResponseDto toPostResponseDto(Post post){
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .category(post.getCategory()).build();
    }
}
