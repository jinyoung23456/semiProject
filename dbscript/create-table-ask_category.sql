USE DOSIRAK;
DROP TABLE IF EXISTS ASK_CATEGORY CASCADE;

CREATE TABLE IF NOT EXISTS ASK_CATEGORY (
ASK_CATEGORY_CODE INT(20) PRIMARY KEY COMMENT '문의분류코드',
ASK_CATEGORY_NAME VARCHAR(50) NOT NULL COMMENT '문의분류이름'
) ENGINE=INNODB COMMENT '문의분류';

insert into ask_category (ask_category_code, ask_category_name) values
(1, '회원'),
(2, '주문/결제'),
(3, '배송/반품'),
(4, '교환/취소/환불'),
(5, '서비스/기타');

