package com.example.demo.src.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPostRes {
    private int postIdx;
    private String title;
    private String writer;
    private String content;
}
