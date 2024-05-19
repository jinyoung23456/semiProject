USE DOSIRAK;
DROP TABLE IF EXISTS CUSTOMER_IMG CASCADE;

CREATE TABLE IF NOT EXISTS CUSTOMER_IMG (
                                            IMG_CODE INT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '이미지코드',
    REF_ASK_CODE INT COMMENT '문의코드',
    ORIGINAL_NAME varchar(50) COMMENT '파일이름',
    SAVED_NAME VARCHAR(225) COMMENT '저장된이름',
    SAVE_PATH VARCHAR(225) COMMENT '저장경로',
    FILE_TYPE VARCHAR(50) COMMENT '파일형식',
    THUMB_NAIL_PATH varchar(225) COMMENT '썸네일저장경로',
    ATTACHMENT_STATUS CHAR COMMENT '저장상태',
    foreign key (REF_ASK_CODE) references ASK (ASK_CODE)
    ) ENGINE=INNODB COMMENT '이미지파일';

select * from customer_img;