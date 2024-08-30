-- USERS 테이블 생성, 요한이가 만들어둔 데이터 이니셜라이져가 테이블만 만들어주고 데이터 반영이 안되서 따로 만듬
-- 스프링 실행시 자동으로 실행됩지는 않으므로 h2 콘솔에서 복사해서 직접 실행해야 테이블 만들어지고 데이터 들어감
CREATE TABLE USERS (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    img VARCHAR(255),
    role VARCHAR(50),
    profile TEXT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- 데이터 삽입
INSERT INTO USERS (username, nickname, img, role, profile, email, password) VALUES
('yohan', 'water', NULL, 'USER', '저는 워터입니다.', 'kakao1@naver.com', '1234'),
('hanyo', 'whater', NULL, 'USER', '저는 와터입니다.', 'kakao2@naver.com', '2345'),
('kakao', 'winter', NULL, 'USER', '저는 카카오입니다.', 'kakao3@naver.com', '3456'); 이거를 데이터이니셜라이져 폴더에 넣으려고 하는데 적당한 파일이름 추천좀