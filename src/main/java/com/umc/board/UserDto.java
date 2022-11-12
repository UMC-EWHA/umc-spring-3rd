package com.umc.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public class DeleteUserReq { //delete api에 요청되는 전달 dto
        private int userIdx;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class DeleteUserRes { //delete api를 통해 리턴될 전달 dto
        private String name;
        private String nickName;
        private String phone;
        private String email;
        private String password;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public class BaseException extends Exception { //사용자 정의 API
        private BaseResponseStatus status;
    }


    @Getter
    @AllArgsConstructor
    @JsonPropertyOrder({"isSuccess", "code", "message", "result"})
    public class BaseResponse<T> {
        @JsonProperty("isSuccess")
        private final Boolean isSuccess;
        private final String message;
        private final int code;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private T result;

        // 요청에 성공한 경우
        public BaseResponse(T result) {
            this.isSuccess = SUCCESS.isSuccess();
            this.message = SUCCESS.getMessage();
            this.code = SUCCESS.getCode();
            this.result = result;
        }

        // 요청에 실패한 경우
        public BaseResponse(BaseResponseStatus status) {
            this.isSuccess = status.isSuccess();
            this.message = status.getMessage();
            this.code = status.getCode();
        }
    }
}
