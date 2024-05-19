USE DOSIRAK;
DROP TABLE IF EXISTS ADMIN CASCADE;

CREATE TABLE IF NOT EXISTS ADMIN (
    ID VARCHAR(20) PRIMARY KEY COMMENT '관리자아이디',
    NAME VARCHAR(5) NOT NULL COMMENT '이름',
    BIRTH DATE COMMENT '생년월일',
    GENDER CHAR COMMENT '성별',
    CONTACT VARCHAR(5) COMMENT '내선번호',
    EMAIL VARCHAR(50) NOT NULL COMMENT '이메일',
    DEPARTMENT VARCHAR(10) COMMENT '부서',
    POSITION VARCHAR(10) COMMENT '직급',
    AUTHORITY CHAR NOT NULL COMMENT '권한등급' DEFAULT 3,
    PWD VARCHAR(255) UNIQUE NOT NULL COMMENT '비밀번호',
    JOINDATE DATE NOT NULL COMMENT '입사날짜',
    WITHDRAWAL BOOLEAN NOT NULL COMMENT '퇴직여부' DEFAULT FALSE,
    ROLE VARCHAR(50) NOT NULL COMMENT '사용자구분' DEFAULT 'ADMIN'
) ENGINE=INNODB COMMENT '관리자';

INSERT INTO ADMIN (ID, NAME, BIRTH, GENDER, CONTACT, EMAIL, DEPARTMENT, POSITION, AUTHORITY, PWD, JOINDATE, WITHDRAWAL, ROLE) VALUES
('choijihyun7', '최지현', '1975-02-10', '여', '101', 'choijihyun7@gmail.com', '마케팅', '팀장', '2', '$2a$10$Un1IY9KPrmNH.Dc3c7dQ.e1DQTH8IZErLi4L4MCv/2SOUq0FYA4se', '2021-04-12', FALSE, 'ADMIN'),
('hanyeeun5', '한예은', '1998-05-25', '여', '104', 'hanyeeun5@gmail.com', '마케팅', '팀원', '3', '$2a$10$kG4kMTVOijM1yW3YaxO1sOpm/6tQ.khie7XWv.1yjLSce/y2W1dU.', '2023-10-23', FALSE, 'ADMIN'),
('hhwo146', '홍현우', '1970-06-30', '남', '400', 'hhwo146@naver.com', '인사재무', '임원', '1', '$2a$10$rhWYjJFJwazvWRqDBrp.Lus1t1jSCkyJthDgfBNfENX3RHyzXAite', '2020-06-07', FALSE, 'ADMIN'),
('hwangyunseo9', '황윤서', '1994-08-15', '여', '203', 'hwangyunseo9@daum.net', '운영', '팀원', '3', '$2a$10$BIwiyW0k6ozFwghjqRPZcetLxrHEWKlJL3A6ziHQOVWjDHJdSiJPi', '2022-04-23', FALSE, 'ADMIN'),
('imjunho67', '임준호', '1993-08-08', '남', '403', 'imjunho67@naver.com', '인사재무', '팀원', '3', '$2a$10$QjlGH.huZo0zxlkNeKix6OlALvHJ25Tyzo6GzvS5Sm3f7IyVYF32.', '2021-10-11', FALSE, 'ADMIN'),
('imseongmin82', '임성민', '1992-08-25', '남', '404', 'imseongmin82@daum.net', '인사재무', '팀원', '3', '$2a$10$dUrEbTmrjpKkdnbPluRA6eRhrjv7yHsl0WTZGcgjfUTLHY3Rc6F0u', '2021-04-23', FALSE, 'ADMIN'),
('jeongseongwoo14', '정성우', '1973-06-20', '남', '401', 'jeongseongwoo14@naver.com', '인사재무', '팀장', '2', '$2a$10$L.WufiCzVWsGjl.6Kx./FeOpldZuLPW46XGgPeo.FC1/SgkPAHASG', '2020-05-25', FALSE, 'ADMIN'),
('jeongseoyeon11', '정서연', '1985-06-15', '여', '302', 'jeongseoyeon11@gmail.com', '고객서비스', '부서장', '2', '$2a$10$b1Ew/FbLdkcKuVuv4dnqKOfgUK0u8iuRbOGYvpHLOW0m3VO7ce3S6', '2017-06-10', FALSE, 'ADMIN'),
('jmsevwx4', '정민서', '1976-09-25', '여', '500', 'jmsevwx4@naver.com', '시스템', '임원', '1', '$2a$10$CkgsgJ81QACem01RJv6oqekubwQbAnNX8G3JliXyqMkZFjYy7I5Qu', '2009-03-21', FALSE, 'ADMIN'),
('kanghaeun99', '강하은', '1983-02-15', '여', '502', 'kanghaeun99@naver.com', '시스템', '부서장', '2', '$2a$10$fROuVZ8Nh9LtGuGjvi3jHerpbYLUarhnkFPI9JhEBIW2CQ0XGHova', '2010-07-01', FALSE, 'ADMIN'),
('kimdoyoon12', '김도윤', '1991-11-20', '남', '504', 'kimdoyoon12@daum.net', '시스템', '팀원', '3', '$2a$10$37OjgDAkLCTLK8bKFiZZY.7yo65T00UmxsQDUqJnpHd24zaO8zacK', '2023-03-18', FALSE, 'ADMIN'),
('kimminjun34', '김민준', '1985-08-30', '남', '202', 'kimminjun34@naver.com', '운영', '부서장', '2', '$2a$10$axa0VVJlMxNR126tAfb.f.JthnNrMbM5u1faohjjqqKwfa36CDW4S', '2006-02-15', FALSE, 'ADMIN'),
('kjiy198', '김지연', '1975-05-01', '여', '100', 'kjiy198@naver.com', '마케팅', '임원', '1', '$2a$10$oHNzIO03/qsNEKaJw13zJuQqfMKO1csX4pOjW1oyc6oNukE82inmy', '2014-09-04', FALSE, 'ADMIN'),
('leejiwon88', '이지원', '1991-06-08', '여', '103', 'leejiwon88@daum.net', '마케팅', '팀원', '3', '$2a$10$MeoVpoiLQxASvm8pKa9Ok./ouuzRumsuzM5hJeabYJtOJnApdhIw6', '2011-01-12', FALSE, 'ADMIN'),
('leeseojin22', '이서진', '1980-10-30', '남', '501', 'leeseojin22@gmail.com', '시스템', '팀장', '2', '$2a$10$ZMZ04MUZh5GYVTJukA5XsuuqTHD6NokIDyEfnCHWXiK06sjxwWN96', '2010-12-10', FALSE, 'ADMIN'),
('ly2824h8', '이서연', '1978-11-20', '남', '300', 'ly2824h8@naver.com', '고객서비스', '임원', '1', '$2a$10$wvTAml/cIGpxMmGLamiOvOspxfCqmySIWvyQ6LYLZZGdX9rpcgBvS', '2009-11-08', FALSE, 'ADMIN'),
('moonjihun3', '문지훈', '1974-10-12', '남', '402', 'moonjihun3@gmail.com', '인사재무', '부서장', '2', '$2a$10$POqLfUoIPYYPh4oEWzVk6uQqr7Sx2cyr3cE3sROyiTumDTDWUagzy', '2011-09-24', FALSE, 'ADMIN'),
('ojiwon77', '오지원', '1997-07-03', '남', '204', 'ojiwon77@naver.com', '운영', '팀원', '3', '$2a$10$fgovX18vircQzzzEJwQVD.WPQu4SnY8anYiezfcXbIKwp96/kM7z6', '2010-01-25', FALSE, 'ADMIN'),
('parkjiyeon1', '박지연', '1983-04-22', '여', '102', 'parkjiyeon1@naver.com', '마케팅', '부서장', '2', '$2a$10$.juD4WsGTOBeL3pNqJkowuuDjmves8uzGwuZqV0C9VkM3X/BwcrdS', '2008-10-09', FALSE, 'ADMIN'),
('parkjunho92', '박준호', '1982-08-10', '남', '201', 'parkjunho92@naver.com', '운영', '팀장', '2', '$2a$10$Ri6frvYamxBsFdsOPZFAwOpJJ.KhaC7t8J8AwjR8wu5wBZO8liTE2', '2009-07-15', FALSE, 'ADMIN'),
('pmji37', '박민지', '1972-08-15', '여', '200', 'pmji37@naver.com', '운영', '임원', '1', '$2a$10$2CkSuDH/7lZRskg8.diVD.YTaYqkRFp8CQ6SRVwWdnbsvGAc683BK', '2011-03-21', FALSE, 'ADMIN'),
('seojiwoo23', '서지우', '1992-10-30', '남', '303', 'seojiwoo23@daum.net', '고객서비스', '팀원', '3', '$2a$10$OIylpLbh4xqbYAofAMV0Ru871OTOdAVCsf2NwfqA20EaNtnOmYf6m', '2010-07-19', FALSE, 'ADMIN'),
('sinminseo8', '신민서', '1996-04-23', '여', '304', 'sinminseo8@gmail.com', '고객서비스', '팀원', '3', '$2a$10$FDODUbEzinVcOJOVuHdrhu3VgfpXxTHz/DYG/Ag3zaOOOeMEx1Gd.', '2009-11-11', FALSE, 'ADMIN'),
('sonjimin44', '손지민', '1978-12-05', '여', '301', 'sonjimin44@naver.com', '고객서비스', '팀장', '2', '$2a$10$HJrx6isBbbNNROuEuKJmHe8yuZu3z4n9RgpO9IZ8FvIJ1XJXyPjyO', '2011-02-03', FALSE, 'ADMIN'),
('yoonseoyeon6', '윤서연', '1992-05-05', '여', '503', 'yoonseoyeon6@naver.com', '시스템', '팀원', '3', '$2a$10$bEAvfO9NXRelA3TVa8Un7O8BsC9lnB9138sRET0UodL8QxM8b.3yW', '2010-01-17', FALSE, 'ADMIN');

select * from ADMIN;
