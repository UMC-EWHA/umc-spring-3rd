package cje.umcAssignment.config;

// BaseResponseStatus에서 가능한 모든 경우의 응답 코드를 정의
// BaseResponseStatus = {isSuccess, code, message)
// 응답 코드를 예외(Exception)에 담아서 Catch 또는 Throw (체크 예외 - 애플리케이션 로직에서 사용할 수 잇는 실질적 최상위 예외)
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
