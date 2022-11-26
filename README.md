# CRUD API 
> UMC-EWHA 3rd Server-Spring Mission
<br>

## Mission List
|Week|Topic|Code|
|:--:|:--:|:----:|
|Week6|Backend Language||
|Week7|Restful, Framework||
|Week8|Client Intro||
<br>

## Project Structure
```
.
├── HELP.md
├── README.md
├── build
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── umc
│   │               └── crud
│   │                   ├── CrudApplication.class
│   │                   ├── config
│   │                   │   ├── BaseException.class
│   │                   │   ├── BaseResponse.class
│   │                   │   └── BaseResponseStatus.class
│   │                   └── src
│   │                       ├── TestController.class
│   │                       └── board
│   │                           ├── BoardController.class
│   │                           ├── BoardDao.class
│   │                           ├── BoardService.class
│   │                           └── model
│   │                               ├── BoardDto.class
│   │                               ├── DeleteBoardReq.class
│   │                               ├── DeleteBoardRes.class
│   │                               ├── GetBoardReq.class
│   │                               ├── GetBoardRes.class
│   │                               ├── PostBoardReq.class
│   │                               ├── PostBoardRes.class
│   │                               ├── PutBoardReq.class
│   │                               └── PutBoardRes.class
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       └── main
│   │       └── headers
│   │           └── java
│   │               └── main
│   ├── resources
│   │   └── main
│   │       ├── application.yml
│   │       ├── static
│   │       └── templates
│   └── tmp
│       └── compileJava
│           └── previous-compilation-data.bin
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── src
    ├── main
    │   ├── java
    │   │   └── umc
    │   │       └── crud
    │   │           ├── CrudApplication.java
    │   │           ├── config
    │   │           │   ├── BaseException.java
    │   │           │   ├── BaseResponse.java
    │   │           │   ├── BaseResponseStatus.java
    │   │           │   └── secret
    │   │           │       └── Secret.java
    │   │           ├── src
    │   │           │   ├── board
    │   │           │   │   ├── BoardController.java
    │   │           │   │   ├── BoardDao.java
    │   │           │   │   ├── BoardProvider.java
    │   │           │   │   ├── BoardService.java
    │   │           │   │   └── model
    │   │           │   │       ├── Board.java
    │   │           │   │       ├── GetBoardReq.java
    │   │           │   │       ├── GetBoardRes.java
    │   │           │   │       ├── PostBoardReq.java
    │   │           │   │       ├── PostBoardRes.java
    │   │           │   │       ├── PutBoardReq.java
    │   │           │   │       └── PutBoardRes.java
    │   │           │   ├── test
    │   │           │   │   └── TestController.java
    │   │           │   └── user
    │   │           │       ├── UserController.java
    │   │           │       ├── UserDao.java
    │   │           │       ├── UserProvider.java
    │   │           │       ├── UserService.java
    │   │           │       └── model
    │   │           │           ├── GetUserRes.java
    │   │           │           ├── PatchUserReq.java
    │   │           │           ├── PostLoginReq.java
    │   │           │           ├── PostLoginRes.java
    │   │           │           ├── PostUserReq.java
    │   │           │           ├── PostUserRes.java
    │   │           │           └── User.java
    │   │           └── utils
    │   │               ├── AES128.java
    │   │               ├── JwtService.java
    │   │               └── ValidationRegex.java
    │   └── resources
    │       ├── application.yml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── umc
                └── crud
                    └── CrudApplicationTests.java
```
