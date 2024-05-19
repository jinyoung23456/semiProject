USE DOSIRAK;
DROP TABLE IF EXISTS survey_result CASCADE;
DROP TABLE IF EXISTS survey_range CASCADE;
DROP TABLE IF EXISTS survey_answer CASCADE;
DROP TABLE IF EXISTS survey_question CASCADE;
DROP TABLE IF EXISTS survey_version CASCADE;
CREATE TABLE IF NOT EXISTS survey_version
(
    version_id      INT PRIMARY KEY AUTO_INCREMENT COMMENT '버전 식별코드',
    version_name    VARCHAR(255) NOT NULL COMMENT '버전 별칭',
    version_explain VARCHAR(40)  NOT NULL COMMENT '버전 설명',
    update_date     DATE         NOT NULL COMMENT '추가된 날짜',
    status          BOOLEAN      NOT NULL COMMENT '사용 상태'
);


-- survey_question 테이블 생성

CREATE TABLE IF NOT EXISTS survey_question
(
    question_id       INT AUTO_INCREMENT PRIMARY KEY COMMENT '질문 코드',
    version_id        INT         NOT NULL COMMENT '설문 버전',
    question_order    INT         NOT NULL COMMENT '질문 순서',
    question_text     VARCHAR(50) NOT NULL COMMENT '질문 내용',
    question_category CHAR(1)     NOT NULL CHECK (question_category IN ('C', 'P', 'F', 'W')) COMMENT '질문 분류(C, P, F, W)',
    FOREIGN KEY (version_id) REFERENCES survey_version (version_id)
);
-- survey_answer 테이블 생성
CREATE TABLE IF NOT EXISTS survey_answer
(
    answer_id    INT AUTO_INCREMENT PRIMARY KEY COMMENT '답변 코드',
    question_id  INT         NOT NULL COMMENT '질문 코드',
    answer_text  VARCHAR(30) NOT NULL COMMENT '답변 내용',
    answer_value INT         NOT NULL COMMENT '답변 값',
    FOREIGN KEY (question_id) REFERENCES survey_question (question_id)
);
-- survey_range 테이블 생성
CREATE TABLE IF NOT EXISTS survey_range
(
    version_id INT NOT NULL,
    category   CHAR(1) CHECK (category IN ('C', 'P', 'F', 'W')),
    range1     INT,
    range2     INT,
    range3     INT,
    range4     INT,
    PRIMARY KEY (version_id, category),
    FOREIGN KEY (version_id) REFERENCES survey_version (version_id),
    CHECK (range1 < range2 AND range2 < range3 AND range3 < range4)
);
-- survey_result 테이블 생성(더미데이터 X)
CREATE TABLE IF NOT EXISTS SURVEY_RESULT
(
    user_id               VARCHAR(20) NOT NULL,
    survey_height         DOUBLE      NOT NULL,
    survey_weight         DOUBLE      NOT NULL,
    survey_bmi            DOUBLE      NOT NULL,
    survey_diet           VARCHAR(10) NOT NULL,
    survey_exercise_score INT         NOT NULL,
    survey_carbo_score    INT         NOT NULL,
    survey_protein_score  INT         NOT NULL,
    survey_fat_score      INT         NOT NULL,
    survey_diabetes       BOOLEAN,
    survey_cancer         BOOLEAN,
    survey_kidney         BOOLEAN,
    survey_blood          BOOLEAN,
    survey_date           DATE,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES `USER` (id)
);

-- SURVEY_VERSION 더미 생성
INSERT INTO SURVEY_VERSION (version_name, version_explain, update_date, status)
VALUES ('첫 번째 설문', '처음으로 등록한 설문', '2024-04-23', 1);

-- 버전 1에 대한 더미 데이터 삽입
INSERT INTO survey_question (version_id, question_order, question_text, question_category)
VALUES (1, 1, '하루에 과일을 얼마나 드시나요?', 'C'),
       (1, 2, '하루에 콩류를 얼마나 드시나요?', 'P'),
       (1, 3, '하루 간식을 얼마나 드시나요?', 'C'),
       (1, 4, '하루에 기름진 음식을 얼마나 드시나요?', 'F'),
       (1, 5, '하루에 드시는 동물성 지방 양은?', 'F'),
       (1, 6, '하루에 드시는 단백질 풍부 음식 양은?', 'P'),
       (1, 7, '하루에 드시는 주식 양은?', 'C'),
       (1, 8, '하루에 드시는 육류 양은?', 'P'),
       (1, 9, '하루에 드시는 기름진 유제품 빈도는?', 'F'),
       (1, 10, '하루에 드시는 밥 끼수는?', 'C'),
       (1, 11, '하루에 드시는 우유나 유제품 양은?', 'P'),
       (1, 12, '하루에 드시는 다른 지방 섭취 음식은?', 'F'),
       (1, 13, '하루에 드시는 과일 개수는?', 'C'),
       (1, 14, '하루에 드시는 식물성 지방 양은?', 'F'),
       (1, 15, '하루에 드시는 난류 양은?', 'P'),
       (1, 16, '하루에 활동적인 생활 정도는?', 'W'),
       (1, 17, '일주일에 정규운동 횟수는?', 'W'),
       (1, 18, '하루에 유산소 운동 시간은?', 'W'),
       (1, 19, '주말 운동 빈도는?', 'W'),
       (1, 20, '주로 하는 운동 종류는?', 'W');

-- 답변 생성
INSERT INTO survey_answer (question_id, answer_text, answer_value)
VALUES (1, '1개 미만', 1),
       (1, '1~2개', 2),
       (1, '3개 이상', 3),

       (2, '자주 먹음', 3),
       (2, '가끔 먹음', 2),
       (2, '거의 안 먹음', 1),

       (3, '매일', 3),
       (3, '몇 번씩', 2),
       (3, '거의 안 먹음', 1),

       (4, '1끼', 1),
       (4, '2끼', 2),
       (4, '3끼 이상', 3),

       (5, '자주 먹음', 3),
       (5, '가끔 먹음', 2),
       (5, '거의 안 먹음', 1),

       (6, '1끼', 1),
       (6, '2끼', 2),
       (6, '3끼 이상', 3),

       (7, '적게 먹음', 1),
       (7, '보통', 2),
       (7, '많이 먹음', 3),

       (8, '매일', 3),
       (8, '몇 번씩', 2),
       (8, '거의 안 먹음', 1),

       (9, '많이 먹음', 3),
       (9, '보통', 2),
       (9, '적게 먹음', 1),

       (10, '1끼', 1),
       (10, '2끼', 2),
       (10, '3끼 이상', 3),

       (11, '자주 먹음', 3),
       (11, '가끔 먹음', 2),
       (11, '거의 안 먹음', 1),

       (12, '자주 먹음', 3),
       (12, '가끔 먹음', 2),
       (12, '거의 안 먹음', 1),

       (13, '1개 미만', 1),
       (13, '1~2개', 2),
       (13, '3개 이상', 3),

       (14, '매일', 3),
       (14, '몇 번씩', 2),
       (14, '거의 안 먹음', 1),

       (15, '많이 먹음', 3),
       (15, '보통', 2),
       (15, '적게 먹음', 1),

       (16, '거의 하지 않음', 1),
       (16, '약간', 2),
       (16, '꾸준히', 3),

       (17, '전혀 안 함', 1),
       (17, '1~2번', 2),
       (17, '3번 이상', 3),

       (18, '30분 미만', 1),
       (18, '30분~1시간', 2),
       (18, '1시간 이상', 3),

       (19, '거의 안 함', 1),
       (19, '약간', 2),
       (19, '많이', 3),

       (20, '유산소 운동', 1),
       (20, '근력 운동', 2),
       (20, '다양한 종류', 3);

INSERT INTO SURVEY_RANGE (version_id, category, range1, range2, range3, range4)
VALUES (1, 'C', 3, 6, 9, 12),
       (1, 'P', 3, 7, 10, 12),
       (1, 'F', 2, 5, 8, 11),
       (1, 'W', 2, 6, 8, 10);