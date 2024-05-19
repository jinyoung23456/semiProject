USE DOSIRAK;
DROP TABLE IF EXISTS SUIT_BOX CASCADE;
DROP TABLE IF EXISTS MENU CASCADE;

CREATE TABLE IF NOT EXISTS MENU
(
    menu_code         INT PRIMARY KEY AUTO_INCREMENT,
    menu_name         VARCHAR(20)                          NOT NULL,
    menu_category     ENUM ('rice','main','side','kimchi') NOT NULL,
    menu_extracash    INT                                  NOT NULL,
    menu_status       ENUM ('Y', 'S', 'N')                 NOT NULL,
    menu_carbo        FLOAT                                NOT NULL,
    menu_sugar        FLOAT                                NOT NULL,
    menu_protein      FLOAT                                NOT NULL,
    menu_fat          FLOAT                                NOT NULL,
    menu_saturatedfat FLOAT                                NOT NULL,
    menu_transfat     FLOAT                                NOT NULL,
    menu_cholesterol  FLOAT                                NOT NULL,
    menu_natrium      FLOAT                                NOT NULL,
    menu_calory       INTEGER                              NOT NULL
);

CREATE TABLE IF NOT EXISTS SUIT_BOX
(
    suitbox_code INT(10) AUTO_INCREMENT PRIMARY KEY,
    rice_code    INT NOT NULL,
    main_code    INT NOT NULL,
    side_code    INT NOT NULL,
    kimchi_code  INT NOT NULL,
    FOREIGN KEY (rice_code) REFERENCES MENU (menu_code),
    FOREIGN KEY (main_code) REFERENCES MENU (menu_code),
    FOREIGN KEY (side_code) REFERENCES MENU (menu_code),
    FOREIGN KEY (kimchi_code) REFERENCES MENU (menu_code)
);


-- MENU 테이블에 더미 데이터 삽입
-- rice 카테고리 더미 데이터
INSERT INTO MENU (menu_name, menu_category, menu_extracash, menu_status, menu_carbo, menu_sugar, menu_protein, menu_fat,
                  menu_saturatedfat, menu_transfat, menu_cholesterol, menu_natrium, menu_calory)
VALUES ('현미밥', 'rice', 0, 'Y', 40.0, 0.5, 3.0, 0.5, 0.1, 0.0, 0.0, 0.0, 200),
       ('보리밥', 'rice', 0, 'S', 45.0, 1.0, 3.5, 0.8, 0.2, 0.0, 0.0, 0.0, 220),
       ('검정콩밥', 'rice', 200, 'Y', 42.0, 0.8, 4.0, 0.6, 0.1, 0.0, 0.0, 0.0, 210),
       ('오곡밥', 'rice', 200, 'Y', 38.0, 0.7, 3.8, 0.4, 0.0, 0.0, 0.0, 0.0, 190),
       ('카무트밥', 'rice', 300, 'N', 40.0, 1.0, 3.5, 0.7, 0.1, 0.0, 0.0, 0.0, 210),
       ('기장밥', 'rice', 200, 'S', 43.0, 0.6, 4.2, 0.5, 0.1, 0.0, 0.0, 0.0, 200),
       ('귀리밥', 'rice', 200, 'S', 39.0, 0.4, 4.0, 0.5, 0.1, 0.0, 0.0, 0.0, 190),
       ('흑미밥', 'rice', 0, 'Y', 41.0, 0.6, 4.1, 0.6, 0.1, 0.0, 0.0, 0.0, 200),
       ('곤약밥', 'rice', 200, 'Y', 35.0, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 30),
-- main 카테고리
       ('갈비찜', 'main', 500, 'Y', 20.0, 5.0, 25.0, 15.0, 5.0, 0.0, 100.0, 1000.0, 400),
       ('함박스테이크', 'main', 0, 'N', 30.0, 2.0, 20.0, 15.0, 6.0, 0.0, 90.0, 800.0, 350),
       ('닭갈비', 'main', 200, 'S', 25.0, 10.0, 30.0, 10.0, 3.0, 0.0, 80.0, 1200.0, 380),
       ('고추장 삼겹살', 'main', 500, 'Y', 15.0, 3.0, 20.0, 25.0, 10.0, 0.0, 110.0, 1500.0, 450),
       ('생선까스', 'main', 200, 'S', 35.0, 2.0, 15.0, 20.0, 5.0, 0.0, 70.0, 900.0, 380),
       ('우삼겹숙주볶음', 'main', 200, 'Y', 20.0, 5.0, 25.0, 20.0, 8.0, 0.0, 120.0, 1300.0, 420),
       ('두부감자조림', 'main', 0, 'Y', 25.0, 5.0, 10.0, 15.0, 3.0, 0.0, 60.0, 800.0, 300),
       ('살치살 새우 찹스테이크', 'main', 700, 'Y', 25.0, 2.0, 30.0, 20.0, 7.0, 0.0, 100.0, 1100.0, 430),
       ('연어 스테이크', 'main', 1000, 'N', 20.0, 3.0, 30.0, 15.0, 4.0, 0.0, 80.0, 900.0, 380),
-- side 카테고리
       ('소고기 장조림', 'side', 300, 'N', 15.0, 5.0, 15.0, 10.0, 4.0, 0.0, 80.0, 900.0, 300),
       ('어묵 잔멸치 볶음', 'side', 0, 'S', 20.0, 3.0, 10.0, 5.0, 2.0, 0.0, 60.0, 800.0, 250),
       ('소시지 야채볶음', 'side', 200, 'S', 25.0, 2.0, 10.0, 15.0, 6.0, 0.0, 70.0, 1000.0, 320),
       ('동그랑땡', 'side', 0, 'Y', 30.0, 1.0, 15.0, 10.0, 3.0, 0.0, 50.0, 700.0, 280),
       ('호박전', 'side', 0, 'Y', 20.0, 3.0, 5.0, 10.0, 2.0, 0.0, 40.0, 600.0, 200),
       ('계란말이', 'side', 0, 'N', 10.0, 2.0, 10.0, 10.0, 2.0, 0.0, 90.0, 700.0, 250),
       ('깻잎 장아찌', 'side', 0, 'Y', 5.0, 1.0, 2.0, 5.0, 1.0, 0.0, 20.0, 300.0, 100),
       ('어묵 볶음', 'side', 0, 'Y', 25.0, 4.0, 8.0, 5.0, 2.0, 0.0, 40.0, 500.0, 220),
       ('감자채볶음', 'side', 0, 'S', 30.0, 2.0, 5.0, 10.0, 3.0, 0.0, 30.0, 400.0, 180),
       ('미나리무침', 'side', 0, 'Y', 10.0, 3.0, 3.0, 5.0, 1.0, 0.0, 20.0, 200.0, 80),
-- kimchi 카테고리
       ('배추김치', 'kimchi', 0, 'Y', 5.0, 2.0, 1.0, 0.5, 0.2, 0.0, 0.0, 1000.0, 30),
       ('깍두기', 'kimchi', 0, 'Y', 4.0, 1.5, 0.8, 0.3, 0.1, 0.0, 0.0, 900.0, 25),
       ('오이소박이', 'kimchi', 0, 'Y', 6.0, 3.0, 1.5, 0.7, 0.3, 0.0, 0.0, 1100.0, 35),
       ('갓김치', 'kimchi', 0, 'S', 4.0, 1.0, 1.0, 0.4, 0.2, 0.0, 0.0, 950.0, 30),
       ('나박김치', 'kimchi', 0, 'S', 5.0, 2.0, 1.2, 0.6, 0.3, 0.0, 0.0, 1000.0, 30),
       ('열무김치', 'kimchi', 0, 'Y', 4.0, 1.5, 1.0, 0.5, 0.2, 0.0, 0.0, 950.0, 30),
       ('무생채', 'kimchi', 0, 'N', 3.0, 1.0, 0.8, 0.3, 0.1, 0.0, 0.0, 850.0, 20),
       ('총각김치', 'kimchi', 0, 'Y', 5.0, 2.5, 1.0, 0.5, 0.2, 0.0, 0.0, 1000.0, 30);

-- SUIT_BOX 테이블에 더미 데이터 삽입
INSERT INTO SUIT_BOX (rice_code, main_code, side_code, kimchi_code)
VALUES (1, 15, 19, 34),
       (4, 12, 22, 35),
       (5, 13, 23, 32),
       (2, 12, 21, 31),
       (3, 11, 25, 36),
       (4, 17, 23, 33),
       (1, 13, 27, 34);