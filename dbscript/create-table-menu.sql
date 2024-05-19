USE DOSIRAK;
DROP TABLE IF EXISTS suit_box CASCADE;
DROP TABLE IF EXISTS menu CASCADE;


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

-- MENU 테이블에 더미 데이터 삽입
INSERT INTO MENU (menu_code, menu_name, menu_category, menu_extracash, menu_status, menu_carbo, menu_sugar, menu_protein, menu_fat, menu_saturatedfat, menu_transfat, menu_cholesterol, menu_natrium, menu_calory) VALUES
-- rice 카테고리
(NULL, '밥1', 'rice', 0, 'Y', 30.5, 1.2, 2.5, 0.3, 0.1, 0, 0, 0, 120),
(NULL, '밥2', 'rice', 0, 'Y', 32.7, 1.5, 2.8, 0.4, 0.2, 0, 0, 0, 130),
(NULL, '밥3', 'rice', 0, 'Y', 31.8, 1.3, 2.7, 0.4, 0.2, 0, 0, 0, 125),
(NULL, '밥4', 'rice', 0, 'Y', 33.2, 1.4, 2.9, 0.5, 0.3, 0, 0, 0, 135),
(NULL, '밥5', 'rice', 0, 'Y', 29.5, 1.1, 2.3, 0.3, 0.1, 0, 0, 0, 115),

-- main 카테고리
(NULL, '메인1', 'main', 500, 'S', 20.1, 3.5, 15.2, 7.8, 3.2, 0, 35, 1200, 350),
(NULL, '메인2', 'main', 700, 'Y', 25.8, 2.9, 17.5, 8.2, 3.5, 0, 40, 1250, 380),
(NULL, '메인3', 'main', 600, 'Y', 22.3, 3.2, 16.8, 7.5, 3.0, 0, 37, 1180, 340),
(NULL, '메인4', 'main', 800, 'Y', 28.5, 3.9, 19.2, 9.5, 4.0, 0, 45, 1300, 400),
(NULL, '메인5', 'main', 550, 'N', 19.8, 3.0, 14.5, 7.0, 2.8, 0, 32, 1150, 320),

-- side 카테고리
(NULL, '사이드1', 'side', 300, 'Y', 10.5, 1.8, 5.5, 3.0, 1.2, 0, 20, 500, 150),
(NULL, '사이드2', 'side', 250, 'Y', 12.8, 2.2, 6.8, 3.5, 1.5, 0, 25, 550, 170),
(NULL, '사이드3', 'side', 350, 'N', 9.5, 1.5, 4.8, 2.5, 1.0, 0, 18, 480, 130),
(NULL, '사이드4', 'side', 400, 'Y', 13.2, 2.5, 7.2, 3.8, 1.8, 0, 28, 580, 180),
(NULL, '사이드5', 'side', 200, 'Y', 11.0, 1.6, 5.0, 2.8, 1.0, 0, 22, 520, 160),

-- kimchi 카테고리
(NULL, '김치1', 'kimchi', 0, 'Y', 5.2, 2.0, 0.5, 0.1, 0, 0, 0, 1100, 40),
(NULL, '김치2', 'kimchi', 0, 'Y', 4.8, 1.8, 0.4, 0.1, 0, 0, 0, 1050, 35),
(NULL, '김치3', 'kimchi', 0, 'S', 5.5, 2.2, 0.6, 0.2, 0, 0, 0, 1150, 45),
(NULL, '김치4', 'kimchi', 0, 'N', 4.5, 1.5, 0.3, 0.1, 0, 0, 0, 1000, 30),
(NULL, '김치5', 'kimchi', 0, 'S', 4.9, 1.7, 0.4, 0.1, 0, 0, 0, 1100, 38);

