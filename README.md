# backend


<h2> 주요 스택 </h2>

- JAVA 17
- (개발)H2 Database → (운영)MySQL
- 스프링 부트 & 스프링 MVC
- JPA
- Spring Data JPA
- QueryDSL
- Swagger
- Junit5
- SLF4J & LogBack (로깅)

<h2> Git 전략 </h2>

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
    - **예시**: `feat` (새로운 기능 추가), `merge`(머지 완료), `fix` (버그 수정), `docs` (문서 수정), `style` (코드 포맷팅 등, 코드 변경이 없는 경우), `refactor` (코드 리팩토링), `test` (테스트 코드 추가), `chore` (기타 변경사항)
    - **옵션(Scope, 선택 사항)**: 변경된 파일이나 기능의 범위를 설명합니다. 선택사항이며, 괄호 안에 작성합니다.
        - **예시**: `feat(auth):`, `fix(ui):`
    - **제목(Subject)**: 50자 이내의 간결한 설명을 작성합니다. 커밋의 내용을 요약하며, 첫 글자는 대문자로 시작하고 마침표를 붙이지 않습니다.
        - **예시**: `Add user authentication feature`

<h2>팀 Backend 노션 링크</h2>
https://www.notion.so/goormkdx/BackEnd-d5a43ee6e98144e2a00030a6f8f3c828

