-- ============================================================
-- 섹션 6. SQL - 데이터 가공
-- ============================================================

USE study_db;

-- [강의: 산술 연산]
-- SELECT에서 직접 계산 가능
SELECT name, age, age + 5 AS '5년 후 나이' FROM members;
SELECT name, age, age * 2 AS '나이x2' FROM members;

-- 할인 가격 계산 예시
-- SELECT product_name, price, price * 0.9 AS '할인가' FROM products;


-- [강의: 문자열 함수]
-- CONCAT: 문자열 합치기
SELECT CONCAT(name, ' (', grade, ')') AS '회원정보' FROM members;

-- SUBSTRING: 부분 문자열 추출 (1부터 시작)
SELECT name, SUBSTRING(email, 1, 3) AS '이메일앞3자' FROM members;

-- LENGTH: 바이트 길이 / CHAR_LENGTH: 문자 길이
SELECT name, LENGTH(name) AS '바이트', CHAR_LENGTH(name) AS '글자수' FROM members;
-- 한글은 UTF-8에서 3바이트이므로 LENGTH와 CHAR_LENGTH가 다릅니다!

-- UPPER / LOWER: 대소문자 변환
SELECT email, UPPER(email) AS '대문자', LOWER(email) AS '소문자' FROM members;

-- TRIM: 앞뒤 공백 제거
SELECT TRIM('  hello  ') AS '공백제거';

-- REPLACE: 문자열 치환
SELECT name, REPLACE(email, '@test.com', '@example.com') AS '변경된이메일' FROM members;


-- [강의: NULL 함수]
-- IFNULL(값, 대체값): NULL이면 대체값 사용
SELECT name, IFNULL(phone, '미등록') AS '전화번호' FROM members;

-- COALESCE(값1, 값2, ...): 첫 번째로 NULL이 아닌 값을 반환
SELECT name, COALESCE(phone, email, '정보없음') AS '연락처' FROM members;


-- [강의: 다양한 함수 소개]
-- 숫자 함수
SELECT ROUND(3.567, 1) AS '반올림';    -- 3.6
SELECT CEIL(3.14) AS '올림';           -- 4
SELECT FLOOR(3.99) AS '내림';          -- 3
SELECT ABS(-10) AS '절대값';           -- 10
SELECT MOD(10, 3) AS '나머지';         -- 1

-- 날짜/시간 함수
SELECT NOW() AS '현재 날짜시간';
SELECT CURDATE() AS '현재 날짜';
SELECT CURTIME() AS '현재 시간';
SELECT YEAR(NOW()) AS '년', MONTH(NOW()) AS '월', DAY(NOW()) AS '일';
SELECT DATE_FORMAT(NOW(), '%Y년 %m월 %d일') AS '포맷팅된 날짜';

-- 조건 함수 (IF, CASE)
SELECT name, age,
    IF(age >= 30, '30대 이상', '20대') AS '나이대'
FROM members;

SELECT name, grade,
    CASE grade
        WHEN 'VIP' THEN '최우수 회원'
        WHEN 'GOLD' THEN '우수 회원'
        ELSE '일반 회원'
    END AS '회원 등급 설명'
FROM members;
