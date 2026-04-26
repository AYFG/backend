-- ============================================================
-- 섹션 5. SQL - 조회와 정렬
-- ============================================================

USE study_db;

-- [조회 실습 데이터 준비]
-- 이전 섹션의 members 테이블 데이터를 사용합니다.
-- 데이터가 없다면 Section04를 먼저 실행해 주세요.


-- [강의: SELECT - 조회]
-- 모든 컬럼 조회
SELECT * FROM members;

-- 특정 컬럼만 조회
SELECT name, email, grade FROM members;

-- 별칭(Alias) 사용
SELECT name AS '이름', age AS '나이', grade AS '등급' FROM members;


-- [강의: WHERE - 기본 검색]
-- 비교 연산자: =, !=, <, >, <=, >=
SELECT * FROM members WHERE age >= 28;
SELECT * FROM members WHERE grade = 'VIP';
SELECT * FROM members WHERE grade != 'NORMAL';

-- 논리 연산자: AND, OR, NOT
SELECT * FROM members WHERE age >= 25 AND grade = 'VIP';
SELECT * FROM members WHERE grade = 'VIP' OR grade = 'GOLD';
SELECT * FROM members WHERE NOT grade = 'NORMAL';


-- [강의: WHERE - 편리한 조건 검색]
-- BETWEEN: 범위 검색 (이상 ~ 이하)
SELECT * FROM members WHERE age BETWEEN 25 AND 30;

-- IN: 여러 값 중 하나와 일치
SELECT * FROM members WHERE grade IN ('VIP', 'GOLD');

-- LIKE: 패턴 검색
-- %: 0개 이상의 임의의 문자
-- _: 정확히 1개의 임의의 문자
SELECT * FROM members WHERE name LIKE '김%';   -- "김"으로 시작하는 이름
SELECT * FROM members WHERE email LIKE '%test%'; -- "test"가 포함된 이메일
SELECT * FROM members WHERE name LIKE '__수';   -- 3글자이고 "수"로 끝나는 이름 (예: 김철수)


-- [강의: ORDER BY - 정렬]
-- ASC: 오름차순 (기본값, 생략 가능)
-- DESC: 내림차순
SELECT * FROM members ORDER BY age ASC;       -- 나이 오름차순
SELECT * FROM members ORDER BY age DESC;      -- 나이 내림차순
SELECT * FROM members ORDER BY grade ASC, age DESC; -- 등급 오름차순 → 같으면 나이 내림차순


-- [강의: LIMIT - 개수 제한]
SELECT * FROM members ORDER BY age DESC LIMIT 3;    -- 상위 3명만
SELECT * FROM members ORDER BY age ASC LIMIT 2, 3;  -- 3번째부터 3개 (OFFSET 2)
-- 페이징에 자주 사용됨: LIMIT (페이지-1)*개수, 개수


-- [강의: DISTINCT - 중복 제거]
SELECT DISTINCT grade FROM members;  -- 중복 없는 등급 목록


-- [강의: NULL - 알 수 없는 값]
-- NULL은 "값이 없음"을 의미하며, 일반 비교 연산자로 비교할 수 없습니다.
-- NULL 비교에는 반드시 IS NULL / IS NOT NULL을 사용해야 합니다!
SELECT * FROM members WHERE phone IS NULL;      -- phone이 NULL인 행
SELECT * FROM members WHERE phone IS NOT NULL;  -- phone이 NULL이 아닌 행

-- 주의: 아래는 동작하지 않습니다!
-- SELECT * FROM members WHERE phone = NULL;  -- ❌ 잘못된 방법
-- NULL은 "알 수 없는 값"이므로 NULL = NULL 조차도 결과가 NULL(거짓)입니다.
