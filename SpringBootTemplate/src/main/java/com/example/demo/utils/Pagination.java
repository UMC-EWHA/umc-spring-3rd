package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int page; // 페이지 번호
    private int limit = 3; //페이지당 출력할 데이터 개수

    public int getOffSet() {
        return (page - 1) * limit;
    }
}
