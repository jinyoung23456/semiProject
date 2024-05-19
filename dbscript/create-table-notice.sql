USE DOSIRAK;
DROP TABLE IF EXISTS NOTICE CASCADE;

CREATE TABLE IF NOT EXISTS NOTICE (
NOTICE_CODE INT AUTO_INCREMENT PRIMARY KEY COMMENT '공지코드',
NOTICE_TITLE VARCHAR(225) NOT NULL COMMENT '공지제목',
NOTICE_CONTENT TEXT(500) NOT NULL COMMENT '공지내용',
NOTICE_DATE DATE COMMENT '공지날짜',
ADMIN_ID VARCHAR(20) COMMENT '관리자아이디',
foreign key (ADMIN_ID) references ADMIN (ID)
) ENGINE=INNODB COMMENT '공지사항';

INSERT INTO NOTICE (NOTICE_CODE, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, ADMIN_ID) VALUES
(1,'공지사항 페이지가 오픈 되었습니다.','도시樂의 홈페이지가 오픈되었습니다.\r\n앞으로 많은 이용 부탁드립니다.','2024-04-23','choijihyun7'),
(2,'배송 휴무 일정 안내','2023년 4월 배송 휴무 일정 안내드립니다.\r\n고객님의 양해 부탁드리며, 아래 내용 확인하셔서 이용에 불편 없으시기를 바랍니다.','2024-04-23','choijihyun7'),
(3,'이벤트 당첨자 안내','안녕하세요. 고객님. 도시樂 입니다.\r\n지난번 진행하였던 구매 이벤트 당첨 안내 드립니다.\r\n이벤트에 참여해주신 많은 고객 분들에게 감사드립니다.\r\n\r\n앞으로도 많은 참여 부탁드립니다.','2024-04-23','choijihyun7'),
(4,'서비스 점검 안내','안녕하세요. 고객님. 도시樂 입니다.\r\n저희 홈페이지를 이용해주시는 고객님께 항상 감사드립니다.\r\n아래의 내용으로 시스템 점검 계획을 안내 드리니, 이용에 참고 부탁드립니다.\r\n\r\n2024월 1월 1일 오전 0시~오전 5시\r\n\r\n','2024-04-23','choijihyun7'),
(5,'가입 이벤트 당첨자 안내','안녕하세요. 고객님. 도시樂 입니다.\r\n지난번 진행하였던 가입 이벤트 당첨 안내 드립니다.\r\n이벤트에 참여해주신 많은 고객 분들에게 감사드립니다.\r\n\r\n앞으로도 많은 참여 부탁드립니다.','2024-04-23','choijihyun7'),
(6,'가입 이벤트 당첨자 안내 (추가)','추가 당첨자를 안내 드립니다.','2024-04-23','choijihyun7'),
(7,'홈페이지 리뉴얼 예정 안내','안녕하세요. 도시樂입니다.\r\n홈페이지를 리뉴얼 할 예정입니다.\r\n\r\n일정 참고 부탁드립니다.','2024-04-23','choijihyun7'),
(8,'카카오페이 결제 안내','안녕하세요. 도시樂 입니다.\r\n카카오페이 결제를 도입할 예정입니다.\r\n\r\n많은 관심 부탁드리겠습니다.','2024-04-23','choijihyun7'),
(9,'결제 일시 중단 안내','안녕하세요.\r\n홈페이지 점검을 위해 결제가 일시적으로 중단될 예정입니다.\r\n일정 참고 부탁드리겠습니다.','2024-04-23','choijihyun7'),
(10,'배송지 변경 안내','안녕하세요. 배송지 변경에 관한 많은 문의가 들어오고 있습니다.\r\n이와 같은 사유는 고객센터 이용 부탁드리겠습니다.\r\n\r\n감사합니다.','2024-04-23','choijihyun7'),
(11,'도시樂 명절 휴무 안내','안녕하세요. 도시樂 입니다.\r\n명절 기간동안 모든 서비스가 중단되오니 참고 부탁드립니다.','2024-04-23','choijihyun7');

