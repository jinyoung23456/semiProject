DROP TABLE IF EXISTS MENU;
DROP TABLE IF EXISTS SUIT_BOX;
DROP TABLE IF EXISTS DETAIL;
DROP TABLE IF EXISTS DELIVERY;
DROP TABLE IF EXISTS PRODUCT_IMG;
DROP TABLE IF EXISTS REFUND;
DROP TABLE IF EXISTS PAY;
DROP TABLE IF EXISTS `ORDER`;
DROP TABLE IF EXISTS CART;

CREATE TABLE IF NOT EXISTS `ORDER` (
    ORDER_CODE INT PRIMARY KEY AUTO_INCREMENT COMMENT '주문 코드',
    ORDER_STATUS VARCHAR(1) DEFAULT 'O' CHECK(ORDER_STATUS IN ('O', 'X', 'C')) NOT NULL COMMENT '주문 상태',
    ID VARCHAR(20) NOT NULL COMMENT '사용자 ID',
    ORDER_RECIPIENT VARCHAR(5) COMMENT '수령인',
    ORDER_CONTACT VARCHAR(11) COMMENT '주문자 연락처',
    ORDER_ADDRESS1 VARCHAR(100) COMMENT '주문자 주소1',
    ORDER_ADDRESS2 VARCHAR(100) COMMENT '주문자 주소2',
    ORDER_ADDRESS3 VARCHAR(100) COMMENT '주문자 주소3',
    FOREIGN KEY (ID) REFERENCES USER(ID)
)AUTO_INCREMENT = 30003001 COMMENT '주문';
-- 완료 O: Completed
-- 취소 X: Canceled
-- 확정 C: Confirmed Purchase

CREATE TABLE IF NOT EXISTS CART (
    CARTITEM_COUNT INT NOT NULL COMMENT '장바구니에 담긴 상품의 수량',
    ID VARCHAR(20) NOT NULL COMMENT '사용자 ID',
    PRODUCT_CODE INT COMMENT '상품 코드',
        SUITBOX_CODE INT COMMENT '맞춤 상품 코드',
    FOREIGN KEY (ID) REFERENCES USER(ID),
    FOREIGN KEY (PRODUCT_CODE) REFERENCES tbl_product(PRODUCT_CODE),
        FOREIGN KEY (SUITBOX_CODE) REFERENCES SUIT_BOX(SUITBOX_CODE)
) COMMENT '장바구니';

CREATE TABLE IF NOT EXISTS PAY (
    ORDER_CODE INT NOT NULL COMMENT '주문 코드',
    PAY_PRICE INT NOT NULL COMMENT '결제 가격',
    PAY_DATE DATE NOT NULL COMMENT '결제 일자',
    PAY_STATUS VARCHAR(1) CHECK(PAY_STATUS IN ('O', 'F', 'X')) COMMENT '결제 상태',
    PAY_METHOD VARCHAR(7) NOT NULL COMMENT '결제 수단',
    FOREIGN KEY (ORDER_CODE) REFERENCES `ORDER`(ORDER_CODE)
) COMMENT '결제';
-- 완료 O: Completed
-- 실패 F: Failed
-- 취소 X: Canceled

CREATE TABLE IF NOT EXISTS REFUND (
    REFUND_CODE INT PRIMARY KEY AUTO_INCREMENT COMMENT '환불 코드',
    ORDER_CODE INT NOT NULL COMMENT '주문 코드',
    REFUND_PRICE INT NOT NULL COMMENT '환불 가격',
    REFUND_DATE DATE NOT NULL COMMENT '환불 일자',
    REFUND_REASON VARCHAR(200) COMMENT '환불 이유',
    REFUND_STATUS VARCHAR(1) DEFAULT 'R' CHECK( REFUND_STATUS IN ('R', 'A', 'D')) COMMENT '환불 상태',
    FOREIGN KEY (ORDER_CODE) REFERENCES `ORDER`(ORDER_CODE)
)AUTO_INCREMENT = 40004001 COMMENT '환불';
-- 환불요청 R: Refund Request
-- 환불처리중 P: Processing
-- 환불승인 A: Approved
-- 환불거부 D: Denied

CREATE TABLE IF NOT EXISTS DELIVERY (
    ORDER_CODE INT NOT NULL COMMENT '주문 코드',
    DELIVERY_CODE INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '배송 코드',
    DELIVERY_STATUS VARCHAR(7) DEFAULT 'P' CHECK ( DELIVERY_STATUS IN ('P', 'I', 'C', 'D')) NOT NULL COMMENT '배송 상태',
    FOREIGN KEY (ORDER_CODE) REFERENCES `ORDER`(ORDER_CODE)
)AUTO_INCREMENT = 50005001 COMMENT '배송';
-- 배송준비중 p prepare
-- 배송중 I ing
-- 배송완료 c complete
-- 배송지연 D delay

CREATE TABLE IF NOT EXISTS DETAIL (
    DETAIL_CODE INT AUTO_INCREMENT PRIMARY KEY COMMENT '상세 주문 코드',
    DETAIL_STATUS VARCHAR(1) DEFAULT 'O' CHECK( DETAIL_STATUS IN ('O', 'X')) COMMENT '주문 상세 상태',
    DETAILITEM_COUNT INT NOT NULL COMMENT '상세 주문 수량',
    PRODUCT_CODE INT COMMENT '상세 상품 코드',
    SUITBOX_CODE INT COMMENT '상세 맞춤 상품 코드',
    ORDER_CODE INT NOT NULL COMMENT '주문 코드',
    FOREIGN KEY (PRODUCT_CODE) REFERENCES tbl_product(PRODUCT_CODE),
    FOREIGN KEY (SUITBOX_CODE) REFERENCES SUIT_BOX(SUITBOX_CODE),
    FOREIGN KEY (ORDER_CODE) REFERENCES `ORDER`(ORDER_CODE)
)AUTO_INCREMENT = 60006001 COMMENT '주문상세';
-- 완료 O: Complete
-- 취소 X: Canceled

CREATE TABLE IF NOT EXISTS PRODUCT_IMG (
    IMG_CODE INT AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 코드',
    PRODUCT_CODE INT COMMENT '상품 코드',
    SAVED_NAME VARCHAR(255) NOT NULL COMMENT '저장이름 코드',
    SAVE_PATH VARCHAR(255) NOT NULL COMMENT '저장 경로',
    FOREIGN KEY (PRODUCT_CODE) REFERENCES tbl_product(PRODUCT_CODE)
)AUTO_INCREMENT = 70007001 COMMENT '이미지 파일';
-- 구현중

CREATE TABLE IF NOT EXISTS MENU(
    menu_code INT PRIMARY KEY AUTO_INCREMENT,
    menu_name VARCHAR(20) NOT NULL,
    menu_category ENUM('rice','main','side','kimchi') NOT NULL,
    menu_extracash INT NOT NULL,
    menu_status ENUM('Y', 'S', 'N') NOT NULL,
    menu_carbo FLOAT NOT NULL,
    menu_sugar FLOAT NOT NULL,
    menu_protein FLOAT NOT NULL,
    menu_fat FLOAT NOT NULL,
    menu_saturatedfat FLOAT NOT NULL,
    menu_transfat FLOAT NOT NULL,
    menu_cholesterol FLOAT NOT NULL,
    menu_natrium FLOAT NOT NULL,
    menu_calory INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS SUIT_BOX(
    suitbox_code INT(10)  AUTO_INCREMENT PRIMARY KEY,
    rice_code INT NOT NULL,
    main_code INT NOT NULL,
    side_code INT NOT NULL,
    kimchi_code INT NOT NULL,
    FOREIGN KEY(rice_code) REFERENCES MENU(menu_code),
    FOREIGN KEY(main_code) REFERENCES MENU(menu_code),
    FOREIGN KEY(side_code) REFERENCES MENU(menu_code),
    FOREIGN KEY(kimchi_code) REFERENCES MENU(menu_code)
);


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

INSERT INTO PAY (ORDER_CODE, PAY_PRICE, PAY_DATE, PAY_STATUS, PAY_METHOD)
VALUES
    (30003001, 12000, '2024-04-15', 'O', '카카오페이'),
    (30003002, 13000, '2024-04-16', 'O', '신용카드'),
    (30003003, 14000, '2024-04-17', 'F', '무통장 입금'),
    (30003004, 15000, '2024-04-18', 'O', '카카오페이'),
    (30003005, 11000, '2024-04-19', 'O', '신용카드'),
    (30003006, 10000, '2024-04-20', 'O', '무통장 입금'),
    (30003007, 13000, '2024-04-21', 'F', '카카오페이'),
    (30003008, 15000, '2024-04-22', 'O', '무통장 입금'),
    (30003009, 12000, '2024-04-23', 'O', '카카오페이'),
    (30003010, 11000, '2024-04-24', 'O', '신용카드'),
    (30003011, 14000, '2024-04-25', 'O', '무통장 입금'),
    (30003012, 13000, '2024-04-26', 'O', '카카오페이'),
    (30003013, 15000, '2024-04-27', 'O', '신용카드'),
    (30003014, 11000, '2024-04-28', 'O', '무통장 입금'),
    (30003015, 14000, '2024-04-29', 'O', '카카오페이'),
    (30003016, 12000, '2024-04-30', 'O', '신용카드'),
    (30003017, 10000, '2024-05-01', 'O', '무통장 입금'),
    (30003018, 11000, '2024-05-02', 'O', '카카오페이'),
    (30003019, 15000, '2024-05-03', 'O', '신용카드'),
    (30003020, 14000, '2024-05-04', 'O', '무통장 입금'),
    (30003021, 12000, '2024-05-05', 'O', '카카오페이'),
    (30003022, 15000, '2024-05-06', 'O', '신용카드'),
    (30003023, 12000, '2024-05-07', 'O', '무통장 입금'),
    (30003024, 13000, '2024-05-08', 'O', '카카오페이'),
    (30003025, 14000, '2024-05-09', 'O', '신용카드'),
    (30003026, 12000, '2024-05-10', 'O', '무통장 입금'),
    (30003027, 15000, '2024-05-11', 'O', '카카오페이'),
    (30003028, 13000, '2024-05-12', 'O', '신용카드'),
    (30003029, 14000, '2024-05-13', 'O', '무통장 입금'),
    (30003030, 11000, '2024-05-14', 'O', '카카오페이'),
    (30003031, 12000, '2024-05-15', 'O', '신용카드'),
    (30003032, 13000, '2024-05-16', 'O', '무통장 입금'),
    (30003033, 15000, '2024-05-17', 'O', '카카오페이'),
    (30003034, 14000, '2024-05-18', 'O', '신용카드'),
    (30003035, 12000, '2024-05-19', 'O', '무통장 입금'),
    (30003036, 11000, '2024-05-20', 'O', '카카오페이'),
    (30003037, 13000, '2024-05-21', 'O', '신용카드'),
    (30003038, 14000, '2024-05-22', 'O', '무통장 입금'),
    (30003039, 12000, '2024-05-23', 'O', '카카오페이'),
    (30003040, 15000, '2024-05-24', 'O', '신용카드');

INSERT INTO REFUND (REFUND_CODE, ORDER_CODE, REFUND_PRICE, REFUND_DATE, REFUND_REASON, REFUND_STATUS)
VALUES
    (40004001, 30003001, 12000, '2024-04-17', '상품 불만족', 'A'),
    (40004002, 30003002, 13000, '2024-04-18', '배송 지연', 'R'),
    (40004003, 30003003, 14000, '2024-04-19', '상품 결함', 'A'),
    (40004004, 30003004, 15000, '2024-04-20', '변심', 'R'),
    (40004005, 30003005, 11000, '2024-04-21', '상품 불만족', 'A'),
    (40004006, 30003006, 10000, '2024-04-22', '배송 지연', 'R'),
    (40004007, 30003007, 13000, '2024-04-23', '상품 결함', 'A'),
    (40004008, 30003008, 15000, '2024-04-24', '변심', 'R'),
    (40004009, 30003009, 12000, '2024-04-25', '상품 불만족', 'A'),
    (40004010, 30003010, 11000, '2024-04-26', '배송 지연', 'R');

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

-- DETAIL 테이블에 더미 데이터 삽입

-- 한 주문에 일반 상품과 맞춤 상품을 모두 구매하도록 40개의 데이터 삽입
INSERT INTO `DETAIL` (DETAILITEM_COUNT, DETAIL_STATUS, PRODUCT_CODE, SUITBOX_CODE, ORDER_CODE) VALUES
(3, 'O', 1, NULL, 30003001),
(2, 'O', NULL, 1, 30003001),
(2, 'O', 2, NULL, 30003002),
(1, 'O', NULL, 2, 30003002),
(4, 'X', 3, NULL, 30003003),
(3, 'O', NULL, 3, 30003003),
(1, 'O', 4, NULL, 30003004),
(2, 'O', NULL, 4, 30003004),
(5, 'O', 5, NULL, 30003005),
(3, 'O', NULL, 5, 30003005),
(2, 'X', 6, NULL, 30003006),
(1, 'O', NULL, 6, 30003006),
(3, 'O', 7, NULL, 30003007),
(2, 'O', NULL, 7, 30003007),
(2, 'X', 8, NULL, 30003008),
(1, 'O', NULL, 8, 30003008),
(2, 'O', 9, NULL, 30003009),
(3, 'O', NULL, 9, 30003009),
(2, 'O', 10, NULL, 30003010),
(4, 'O', NULL, 10, 30003010),
(2, 'O', 11, NULL, 30003011),
(2, 'O', NULL, 11, 30003011),
(1, 'O', 12, NULL, 30003012),
(2, 'O', NULL, 12, 30003012),
(3, 'O', 13, NULL, 30003013),
(2, 'O', NULL, 13, 30003013),
(2, 'O', 14, NULL, 30003014),
(1, 'O', NULL, 14, 30003014),
(3, 'O', 15, NULL, 30003015),
(3, 'O', NULL, 15, 30003015),
(2, 'O', 16, NULL, 30003016),
(4, 'O', NULL, 16, 30003016),
(3, 'O', 17, NULL, 30003017),
(1, 'O', NULL, 17, 30003017),
(2, 'O', 18, NULL, 30003018),
(3, 'O', NULL, 18, 30003018),
(3, 'O', 19, NULL, 30003019),
(2, 'O', NULL, 19, 30003019),
(2, 'O', 20, NULL, 30003020),
(4, 'O', NULL, 20, 30003020);


INSERT INTO SUIT_BOX (rice_code, main_code, side_code, kimchi_code) VALUES
(1, 6, 11, 16),
(2, 7, 12, 17),
(3, 8, 13, 18),
(4, 9, 14, 19),
(5, 10, 15, 20),
(1, 7, 11, 17),
(2, 8, 12, 18),
(3, 9, 13, 19),
(4, 10, 14, 20),
(5, 6, 15, 16),
(1, 8, 12, 19),
(2, 9, 13, 20),
(3, 10, 14, 16),
(4, 6, 15, 17),
(5, 7, 11, 18),
(1, 9, 13, 16),
(2, 10, 14, 17),
(3, 6, 15, 18),
(4, 7, 11, 19),
(5, 8, 12, 20),
(1, 10, 14, 18),
(2, 6, 15, 19),
(3, 7, 11, 20),
(4, 8, 12, 16),
(5, 9, 13, 17),
(1, 7, 15, 19),
(2, 8, 11, 16),
(3, 9, 12, 17),
(4, 10, 13, 18),
(5, 6, 14, 20),
(1, 9, 11, 17),
(2, 10, 12, 18),
(3, 6, 13, 19),
(4, 7, 14, 20),
(5, 8, 15, 16),
(1, 10, 12, 20),
(2, 6, 11, 16),
(3, 7, 13, 17),
(4, 8, 14, 18),
(5, 9, 15, 19);
