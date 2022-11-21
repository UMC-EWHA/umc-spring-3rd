package com.umc.week6.model;
import lombok.*;
//@Getter
//@Setter
//@AllArgsConstructor
public class Post {
    private int postnum;
    private String title;
    private String content;
    private String userid;

    public Post() {
    }
 
    public Post(int postnum, String title, String content, String userid) {
        this.postnum = postnum;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }
 
    public int getPostnum() {
        return postnum;
    }
 
    public void setPostnum(int postnum) {
        this.postnum = postnum;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    public String getUserid() {
        return userid;
    }
 
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}
