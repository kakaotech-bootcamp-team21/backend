# Backend(📖자소서 첨삭 프로젝트📖)

<h2> 1. 서비스 UI 예시</h2>
<img width="500" alt="스크린샷 2024-09-12 오후 8 10 11" src="https://github.com/user-attachments/assets/d80d0598-bebe-4523-8438-7b9a15097322">
<img width="500" alt="KakaoTalk_Photo_2024-09-12-19-42-35 002" src="https://github.com/user-attachments/assets/772f9f04-d560-4c24-8a38-781fd25c5423">
<img width="500" alt="스크린샷 2024-09-12 오후 7 44 12" src="https://github.com/user-attachments/assets/2ca44abb-38d1-4cce-a1c6-312dcaaaabc1">
<img width="500" alt="스크린샷 2024-09-12 오후 8 07 44" src="https://github.com/user-attachments/assets/14636fec-d676-4933-93db-835b3a7dc01d">
<img width="500" alt="스크린샷 2024-09-12 오후 8 12 29" src="https://github.com/user-attachments/assets/b7806d91-aa3c-4f0b-ab80-a357a1cd2292">

<h2> 2. 주요 스택 </h2>

- JAVA 17
- (개발)H2 Database → (운영)MySQL
- 스프링 부트 & 스프링 MVC
- JPA
- Spring Data JPA
- QueryDSL
- Swagger
- Junit5
- SLF4J & LogBack (로깅)

<h2> 3. Git 전략 </h2>

<h3>브랜치 전략</h3>

- 개발  
  - feature/[기능] 브랜치 : 개인이 개발을 위해 사용하는 브랜치
  - dev 브랜치 : 개인이 개발한 브랜치를 PR 할 때 사용하는 브랜치 (보호된 브랜치. 즉, PR을 통해서만 머지 가능.)
    - dev 브랜치에 PR이 들어오면 애플리케이션의 테스트 케이스를 수행하고 성공 시에만 squash merge를 할 수 있다.
  - main 브랜치 : 개발이 어느 정도 되었을 시 배포를 위한 브랜치로, 커밋을 수행하면 자동으로 EC2 Instance로 앱이 배포되게 된다. (제약 브랜치X. 조심)
    - ec2 url : http://ec2-43-200-211-225.ap-northeast-2.compute.amazonaws.com:8080
- 운영
  - release : 개발용 배포를 통해 잘 되는 것으로 확인 된다면 운영 서버에 반영 할 때 이용. 
    - ec2 url: http://ec2-43-200-211-225.ap-northeast-2.compute.amazonaws.com:8088 (예정)

<h3> 커밋 메시지 전략 </h3>

- `타입(옵션): 제목`
    - **예시**: `feat` (새로운 기능 추가),`fix` (버그 수정), `docs` (문서 수정), `style` (코드 포맷팅 등, 코드 변경이 없는 경우), `refactor` (코드 리팩토링), `test` (테스트 코드 추가), `chore` (기타 변경사항)
    - **옵션(Scope, 선택 사항)**: 변경된 파일이나 기능의 범위를 설명합니다. 선택사항이며, 괄호 안에 작성합니다.
        - **예시**: `feat(auth):`, `fix(ui):`
    - **제목(Subject)**: 50자 이내의 간결한 설명을 작성합니다. 커밋의 내용을 요약하며, 첫 글자는 대문자로 시작하고 마침표를 붙이지 않습니다.
        - **예시**: `Add user authentication feature`

<h2> 4. 요구사항 분석</h2>

- 기능적 요구 사항
    - 사용자 관리 기능
        - 회원 가입/탈퇴 기능
        - 로그인 기능
            - 인증 기능
                - 일반 계정 인증
                - 소셜 인증
            - 권한 관리
                - 사용자
                - 전문가
                - 관리자
        - 프로필 관리
            - 닉네임 설정
            - 간단한 자기소개 설정
            - 프로필 사진 설정
    - 첨삭 기능
        - 일반 첨삭(전문가에게 게시글 형식으로 첨삭 받기)
            - 사용자 관점
                1. 자소서 업로드(파일 업로드 or 텍스트 형식)
                2. 전문가들에게 요청 정보가 전송됨.
            - 전문가 관점
                1. 자신의 산업 및 직군에 속한 일반 첨삭 리스트 정보를 수신함을 통해 열람
                2. 자소서 **문항 단위** 혹은 **전체**를 선택하여 답변
        - 채팅 / 영상통화 첨삭
            - 사용자 관점
                1. 자신이 속한 산업 및 직군 카테고리 선택
                2. 채팅/영상통화가 가능한 전문가 리스트를 show
                3. 원하는 전문가 클릭
                4. 캘린더를 통한 일정 및 시간 조율(전문가에게 요청 내용 검토의 시간이 필요함)
                5. 자소서 업로드(파일 및 텍스트 형식)
                6. 전문가에게 **알림 메시지**와 **요청 정보**가 전송됨.
            - 전문가 관점
                1. 자신의 수신함에 있는 첨삭 **요청 정보**를 읽고 수락/거절
                2. 수락 시 일정 정보가 생성되고 **일정 메시지(10분 전)**를 발송.
        - AI 첨삭
            - 자소서 첨삭 기능
                - 맞춤법 교정, 글의 문맥 및 스타일, 안 좋은 습관을 개선
            - 자소서를 통한 면접 가능 질문/ 답변 생성
    - 알림 기능
        - ex) 첨삭약속 10분 전에 알림이 가게 하는 기능 구현 예정.
- 비기능적 요구 사항(개발 후 상황에 따라 구현)
    - 인프라
    - 성능
    - 보안



<h2> 5. DB 테이블 설계</h2>
<img src="https://github.com/user-attachments/assets/c64daa62-d513-44e4-bdda-f64b7a7fa99c">

<h2> 6. JPA 엔티티 설계 </h2>
<img src="https://github.com/user-attachments/assets/0c327f66-bae9-44b5-897a-d008d64af964">

<h2>7. 소프트웨어 아키텍처</h2>

<img src="https://github.com/user-attachments/assets/76a19415-886f-4313-a5cd-3e19e66b54b1">

→ **계층형 구조 사용**
- controller : 웹 계층(api endpoint)
- service: 비즈니스 로직, 트랜잭션 처리
- repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용
- domain: 엔티티가 모여 있는 계층, 모든 계층에서 사용

### **패키지 구조**

- `kakaotech_bootcamp.team_21.coverletter_spring_project`
    - domain : 엔티티 저장
        - enums : 열거형 엔티티 저장
    - service : 비즈니스 로직 및 트랜젝션 처리
    - dto : 요청 or 응답시 활용 할 클래스
    - exception : 사용자 정의 예외 저장
    - repository : 데이터 접근 계층
    - config : 설정 정보들 저장
    - dataInitializer : 초기 데이터 구성 용도
    - controller
        - api : REST API endpoint.

<h2>8. REST API 설계</h2>

| Description | REST API | HTTP Method |
| --- | --- | --- |
| Retrieve all Users | /users | GET |
| Create a User | /users | POST |
| Retrieve One User | /users/{id} | GET |
| Delete a User | /users/{id} | DELETE |
| Retrieve all posts for a User | /users/{id}/posts | GET |
| Create a posts for a User | /users/{id}/posts | POST |
| Retrieve details of a User | /users/{id}/posts/{post_id} | GET |

<h2>팀 공유용 Backend 노션 링크</h2>
https://www.notion.so/goormkdx/BackEnd-d5a43ee6e98144e2a00030a6f8f3c828

