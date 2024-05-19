USE dosirak;

DROP TABLE IF EXISTS tbl_product CASCADE;
CREATE TABLE IF NOT EXISTS tbl_product(
    PRODUCT_CODE    INT(255) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    PRODUCT_NAME    VARCHAR(20) UNIQUE                  NOT NULL,
    product_price   INTEGER(20)                         NOT NULL,
    product_status  VARCHAR(20)                         NOT NULL,
    product_summary VARCHAR(20)                         NOT NULL,
    product_category_code int(10) NOT NULL ,
    FOREIGN KEY (product_category_code) REFERENCES tbl_product_category(product_main_category_code)
) ENGINE = INNODB;

INSERT INTO tbl_product VALUE ('1','헬시 도시락','12000','판매중','맛난거','1');
INSERT INTO tbl_product VALUE ('2','샐러드','13000','판매중단','안맛난거','2');
INSERT INTO tbl_product VALUE ('3','닭 가슴살 도시락','1000','판매중','맛난거','9');
INSERT INTO tbl_product VALUE ('4','암 도시락','12000','판매중','맛난거','4');
INSERT INTO tbl_product VALUE ('5','혈당 도시락','12000','판매중','맛난거','7');

DROP TABLE IF EXISTS tbl_product_category CASCADE;
CREATE TABLE IF NOT EXISTS tbl_product_category(
    product_main_category_code     INT(255) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_main_category_name     VARCHAR(20)                  NOT NULL,
    product_sub_category_code INT(255)                         ,
    FOREIGN KEY (product_sub_category_code) REFERENCES tbl_product_category (product_main_category_code)
) ENGINE = INNODB;
INSERT INTO tbl_product_category VALUES ('1','건강 도시락',null);
INSERT INTO tbl_product_category VALUES ('2','정성 도시락',null);
INSERT INTO tbl_product_category VALUES ('3','간편식',null);
INSERT INTO tbl_product_category VALUES ('4','암환자 회복식단','1');
INSERT INTO tbl_product_category VALUES ('5','신장 관리식단','1');
INSERT INTO tbl_product_category VALUES ('6','혈압 관리식단','1');
INSERT INTO tbl_product_category VALUES ('7','혈당 관리식단','1');
INSERT INTO tbl_product_category VALUES ('8','샐러드','3');
INSERT INTO tbl_product_category VALUES ('9','닭 가슴살','3');


DROP TABLE IF EXISTS tbl_detail CASCADE;
CREATE TABLE IF NOT EXISTS tbl_detail(
    detailitem_count INT(255)           NOT NULL,
    detailitem_code  VARCHAR(20) UNIQUE NOT NULL,
    suitbox_code     INT(20)            NOT NULL,
    detail_code      int(20) primary key,
    order_code       INT(12)            not null,
    FOREIGN KEY (order_code) REFERENCES tbl_order (order_code)
) ENGINE = INNODB;

DROP TABLE IF EXISTS tbl_order CASCADE;
CREATE TABLE IF NOT EXISTS tbl_order(
    order_code      INT(12) primary key NOT NULL,
    order_status    VARCHAR(5) UNIQUE   NOT NULL,
    order_recipient VARCHAR(5)          NOT NULL,
    order_contact   int(20) NOT NULL ,
    oder_address VARCHAR(100) NOT NULL ,
    user_id VARCHAR(20) not null ,
    FOREIGN KEY (user_id) REFERENCES USER (ID)
) ENGINE = INNODB;
drop table product_img;
CREATE TABLE IF NOT EXISTS PRODUCT_IMG (
                                           IMG_CODE INT AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 코드',
                                           PRODUCT_CODE INT COMMENT '상품 코드',
                                           SAVED_NAME VARCHAR(255) NOT NULL COMMENT '저장이름 코드',
                                           SAVE_PATH VARCHAR(255) NOT NULL COMMENT '저장 경로',
                                           FOREIGN KEY (PRODUCT_CODE) REFERENCES tbl_product(PRODUCT_CODE)
)AUTO_INCREMENT = 70007001 COMMENT '이미지 파일';




USE DOSIRAK;
DROP TABLE IF EXISTS ASK CASCADE;

CREATE TABLE IF NOT EXISTS ASK (
                                   ASK_CODE INT AUTO_INCREMENT KEY COMMENT '문의코드',
                                   ASK_TITLE VARCHAR(225) NOT NULL COMMENT '문의제목',
                                   ASK_CONTENT TEXT(500) NOT NULL COMMENT '문의내용',
                                   ASK_DATE DATE NOT NULL COMMENT '문의날짜',
                                   USER_ID VARCHAR(20) NOT NULL COMMENT '회원아이디',
                                   ASK_EDITDATE DATE COMMENT '수정일시',
                                   ASK_DELETE BOOLEAN COMMENT '삭제여부',
                                   ASK_CATEGORY_CODE INT(5) COMMENT '문의분류코드',
                                   foreign key (USER_ID) references USER (ID),
                                   foreign key (ASK_CATEGORY_CODE) references ask_category (ASK_CATEGORY_CODE)
) ENGINE=INNODB COMMENT '1대1문의';

INSERT INTO ASK (ASK_CODE, ASK_TITLE, ASK_CONTENT, ASK_DATE, USER_ID,ASK_EDITDATE, ASK_DELETE, ASK_CATEGORY_CODE) VALUES
                                                                                                         (1,'문의합니다.','문의합니다.','2024-01-01','starry2','2024-01-02',0,1);



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


INSERT INTO `ORDER` (ORDER_CODE, ORDER_STATUS, ID)
VALUES
    (30003001, 'O', 'cool12'),
    (30003002, 'C', 'don789'),
    (30003003, 'X', 'gaonkim44'),
    (30003004, 'O', 'gayu99'),
    (30003005, 'C', 'haunoh98'),
    (30003006, 'O', 'hdonguk1'),
    (30003007, 'X', 'hkwang88'),
    (30003008, 'O', 'hohwan45'),
    (30003009, 'C', 'jhyukoh10'),
    (30003010, 'O', 'jihlim77'),
    (30003011, 'C', 'jihyun1'),
    (30003012, 'X', 'jinwo21'),
    (30003013, 'O', 'jiwon98'),
    (30003014, 'C', 'jiwoo2'),
    (30003015, 'X', 'jiwoon21'),
    (30003016, 'O', 'jiyo999'),
    (30003017, 'C', 'jmin56'),
    (30003018, 'X', 'juaan123'),
    (30003019, 'O', 'junho25'),
    (30003020, 'C', 'jwbaek99');

INSERT INTO `ORDER` (ORDER_CODE, ORDER_STATUS, ID, ORDER_RECIPIENT, ORDER_CONTACT, ORDER_ADDRESS1, ORDER_ADDRESS2, ORDER_ADDRESS3)
VALUES
    (30003021, 'O', 'cool12', '최승현', '01023456789', '48058', '부산광역시 해운대구 해운대로 77', '405호'),
    (30003022, 'C', 'don789', '신동우', '01012345678', '42156', '울산광역시 중구 성남로 876', '102호'),
    (30003023, 'X', 'gaonkim44', '김가온', '01098765432', '12345', '서울특별시 강남구 강남대로 777', '403호'),
    (30003024, 'O', 'gayu99', '윤가연', '01087654321', '30123', '세종특별자치시 조치원읍 용두로 444', '303호'),
    (30003025, 'C', 'haunoh98', '오하은', '01098765432', '41234', '경기도 파주시 금릉로 666', '202호'),
    (30003026, 'O', 'hdonguk1', '한동욱', '01098765432', '44708', '울산광역시 남구 삼산로 22', '301호'),
    (30003027, 'X', 'hkwang88', '곽영훈', '01098765432', '41232', '부산광역시 사하구 다대로 987', '201호'),
    (30003028, 'O', 'hohwan45', '안준호', '01098765432', '07507', '서울특별시 강서구 양천로 9', '101호'),
    (30003029, 'C', 'jhyukoh10', '오준혁', '01098765432', '30123', '세종특별자치시 나성로 555', '202호'),
    (30003030, 'O', 'jihlim77', '임지혜', '01087654321', '12345', '경기도 의정부시 평화로 777', '501호'),
    (30003031, 'C', 'jihyun1', '김지현', '01087654321', '41123', '경기도 고양시 덕양구 화정로 777', '201호'),
    (30003032, 'X', 'jinwo21', '손진우', '01034567890', '30077', '세종특별자치시 세종로 333', '202호'),
    (30003033, 'O', 'jiwon98', '한지원', '01065432187', '07508', '서울특별시 강서구 양천로 9', '401호'),
    (30003034, 'C', 'jiwoo2', '최지우', '01087654321', '60123', '부산광역시 연제구 중앙대로 111', '502호'),
    (30003035, 'X', 'jiwoon21', '곽지원', '01098765432', '30123', '대전광역시 중구 대종로 333', '102호'),
    (30003036, 'O', 'jiyo999', '백지윤', '01087654321', '61187', '부산광역시 동래구 충렬대로 111', '401호'),
    (30003037, 'C', 'jmin56', '정민석', '01054321678', '12345', '인천광역시 연수구 송도대로 123번길 8', '501호'),
    (30003038, 'X', 'juaan123', '안주아', '01098764567', '41234', '경기도 안산시 단원구 고잔로 888', '101호'),
    (30003039, 'O', 'junho25', '이준호', '01098765432', '16654', '경기도 수원시 영통구 매탄로 67번길 21', '201호'),
    (30003040, 'C', 'jwbaek99', '백준원', '01087654321', '41267', '대구광역시 수성구 수성로 321', '303호');

INSERT INTO DELIVERY (ORDER_CODE, DELIVERY_CODE, DELIVERY_STATUS)
VALUES
    (30003001, 50005001, 'P'),
    (30003002, 50005002, 'I'),
    (30003003, 50005003, 'C'),
    (30003004, 50005004, 'D'),
    (30003005, 50005005, 'P'),
    (30003006, 50005006, 'I'),
    (30003007, 50005007, 'C'),
    (30003008, 50005008, 'D'),
    (30003009, 50005009, 'P'),
    (30003010, 50005010, 'I'),
    (30003011, 50005011, 'C'),
    (30003012, 50005012, 'D'),
    (30003013, 50005013, 'P'),
    (30003014, 50005014, 'I'),
    (30003015, 50005015, 'C'),
    (30003016, 50005016, 'D'),
    (30003017, 50005017, 'P'),
    (30003018, 50005018, 'I'),
    (30003019, 50005019, 'C'),
    (30003020, 50005020, 'D'),
    (30003021, 50005021, 'P'),
    (30003022, 50005022, 'I'),
    (30003023, 50005023, 'C'),
    (30003024, 50005024, 'D'),
    (30003025, 50005025, 'P'),
    (30003026, 50005026, 'I'),
    (30003027, 50005027, 'C'),
    (30003028, 50005028, 'D'),
    (30003029, 50005029, 'P'),
    (30003030, 50005030, 'I'),
    (30003031, 50005031, 'C'),
    (30003032, 50005032, 'D'),
    (30003033, 50005033, 'P'),
    (30003034, 50005034, 'I'),
    (30003035, 50005035, 'C'),
    (30003036, 50005036, 'D'),
    (30003037, 50005037, 'P'),
    (30003038, 50005038, 'I'),
    (30003039, 50005039, 'C'),
    (30003040, 50005040, 'D');