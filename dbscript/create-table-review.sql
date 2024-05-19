USE DOSIRAK;
DROP TABLE IF EXISTS ASK CASCADE;

CREATE TABLE IF NOT EXISTS review (
review_code INT AUTO_INCREMENT COMMENT '리뷰코드',
review_title VARCHAR(255) NOT NULL COMMENT '리뷰제목',
review_content VARCHAR(255) NOT NULL COMMENT '리뷰내용',
user_id VARCHAR(20) COMMENT '회원아이디',
review_editdate DATE  COMMENT '수정일시',
review_delete VARCHAR(2) DEFAULT 'N' NOT NULL  COMMENT '삭제여부',
detail_code INT NOT NULL COMMENT '주문상세코드',
review_date DATE NOT NULL COMMENT '리뷰날짜',
PRIMARY KEY (review_code) ,
FOREIGN KEY (user_id) REFERENCES USER(ID),
FOREIGN KEY (detail_code) REFERENCES detail(detail_code)
) ENGINE=INNODB;

INSERT INTO ASK (ASK_CODE, ASK_TITLE, ASK_CONTENT, ASK_DATE, USER_ID, ASK_DELETE, ASK_CATEGORY_CODE) VALUES
(1,'문의합니다.','문의합니다.','2024-01-01','starry2','2024-01-02',0,1),
(2,'문의합니다2','문의합니다2','2024-01-02','starry2','2024-01-02',0,2),
(3,'문의합니다아아아','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Exercitationem maiores, perferendis aliquid excepturi consequatur dolorem ipsum ab, aut soluta rerum sequi illo nesciunt alias harum, distinctio molestias adipisci. Maiores, ea.','2024-01-02','starry2','2024-01-02',0,3),
(4,'왜 답변 안해주시나요..','내가 지금 울고있은데.','2024-01-02','starry2','2024-01-02',0,3),
(5,'2023년 4월 19일의 문의입니다.','2023년 4월 19일의 문의 내용입니다.','2023-04-19','starry2','2023-04-19',0,1),
(6,'2023년 10월 20일의 문의입니다.','2023년 10월 20일의 문의 내용입니다.','2023-10-20','starry2','2023-10-20',0,1),
(7,'2024년 1월 20일의 문의입니다.','2024년 1월 20일의 문의 내용입니다.','2024-01-20','starry2','2024-01-20',0,2),
(8,'2024년 3월 25일의 문의입니다.','2024년 3월 25일의 문의 내용입니다.','2024-03-25','starry2','2024-03-25',0,2),
(9,'2024년 4월 16일의 문의입니다.','2024년 4월 16일의 문의 내용입니다.','2024-04-16','starry2','2024-04-16',0,2),
(10,'테스트','테스트',NULL,'임시사용자','2024-04-19',0,1),
(11,'첨부파일 테스트','첨부파일 테스트',NULL,'임시사용자','2024-04-22',0,1);
