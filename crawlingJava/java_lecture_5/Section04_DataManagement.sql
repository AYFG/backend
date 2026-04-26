-- ============================================================
-- 섹션 4. SQL - 데이터 관리 (DDL + DML)
-- ============================================================

USE study_db;

-- =====================
-- DDL (Data Definition Language) - 테이블 구조 정의
-- =====================

-- [강의: DDL - 테이블 생성]
CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    age INT CHECK (age >= 0),
    grade VARCHAR(20) DEFAULT 'NORMAL',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- [강의: DDL - 테이블 변경 (ALTER)]
-- 컬럼 추가
ALTER TABLE members ADD COLUMN phone VARCHAR(20);

-- 컬럼 타입 변경
ALTER TABLE members MODIFY COLUMN grade VARCHAR(30);

-- 컬럼 이름 변경
-- ALTER TABLE members RENAME COLUMN phone TO phone_number;

-- 컬럼 삭제
-- ALTER TABLE members DROP COLUMN phone;

-- [강의: DDL - 테이블 제거 (DROP)]
-- DROP TABLE members;  -- 테이블 자체를 삭제 (데이터 + 구조 모두 삭제, 복구 불가!)
-- TRUNCATE TABLE members;  -- 데이터만 전부 삭제 (구조는 유지, DELETE보다 빠름)


-- =====================
-- DML (Data Manipulation Language) - 데이터 조작
-- =====================

-- [강의: DML - 등록 (INSERT)]
INSERT INTO members (name, email, age, grade) VALUES ('홍길동', 'hong@test.com', 25, 'VIP');
INSERT INTO members (name, email, age, grade) VALUES ('김철수', 'kim@test.com', 30, 'NORMAL');
INSERT INTO members (name, email, age, grade) VALUES ('이영희', 'lee@test.com', 22, 'VIP');
INSERT INTO members (name, email, age, grade) VALUES ('박민수', 'park@test.com', 28, 'NORMAL');
INSERT INTO members (name, email, age, grade) VALUES ('최수진', 'choi@test.com', 35, 'GOLD');

-- 여러 건 한 번에 등록
INSERT INTO members (name, email, age, grade) VALUES
    ('정다은', 'jung@test.com', 27, 'NORMAL'),
    ('한지민', 'han@test.com', 31, 'GOLD');

-- 등록된 데이터 확인
SELECT * FROM members;

-- [강의: DML - 수정 (UPDATE)]
-- 주의: WHERE 없이 UPDATE하면 모든 행이 수정됩니다! (매우 위험!)
UPDATE members SET grade = 'GOLD' WHERE name = '김철수';
UPDATE members SET age = 26, grade = 'VIP' WHERE name = '박민수';

-- 수정 결과 확인
SELECT * FROM members;

-- [강의: DML - 삭제 (DELETE)]
-- 주의: WHERE 없이 DELETE하면 모든 데이터가 삭제됩니다! (매우 위험!)
DELETE FROM members WHERE name = '정다은';

-- 삭제 결과 확인
SELECT * FROM members;

-- [강의: 제약 조건 활용]
-- 1. NOT NULL 위반 테스트 (에러 발생)
-- INSERT INTO members (name, email) VALUES (NULL, 'test@test.com');

-- 2. UNIQUE 위반 테스트 (에러 발생)
-- INSERT INTO members (name, email) VALUES ('테스트', 'hong@test.com'); -- 이미 존재하는 이메일

-- 3. CHECK 위반 테스트 (에러 발생)
-- INSERT INTO members (name, email, age) VALUES ('테스트', 'test2@test.com', -1); -- 음수 나이
