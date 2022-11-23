package cje.umcAssignment.model;

import lombok.Getter;
import lombok.Setter;

// paging을 위해
@Getter @Setter
public class SearchDto {
    private int page;   // 현재 페이지 번호
    private int recordSize; // 페이지당 출력할 데이터 개수
    private int pageSize;   // 화면 하단에 출력할 페이지 사이즈
    private String keyword; // 검색 키워드
    private String SearchType;  // 검색 유형

    // 생성자
    public SearchDto(){
       this.page = 1;
       this.recordSize = 10;
       this.pageSize = 10;
    }

    public int getOffset() {
        return (page-1) * recordSize;
    }
}
