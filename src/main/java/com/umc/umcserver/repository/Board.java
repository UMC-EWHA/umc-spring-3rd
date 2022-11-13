package com.umc.umcserver.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column(nullable = false)
    private String writer;

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
