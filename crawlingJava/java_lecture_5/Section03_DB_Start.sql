-- ============================================================
-- 섹션 3. 데이터베이스 시작
-- ============================================================

-- [강의: 데이터베이스 시작하기]
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS study_db;
USE study_db;

-- [강의: SQL이란?]
-- SQL (Structured Query Language): 데이터베이스를 다루기 위한 표준 언어
-- SQL의 종류:
--   DDL (Data Definition Language): 테이블 구조 정의 (CREATE, ALTER, DROP)
--   DML (Data Manipulation Language): 데이터 조작 (INSERT, UPDATE, DELETE, SELECT)
--   DCL (Data Control Language): 권한 관리 (GRANT, REVOKE)

-- [강의: 데이터 타입]
-- 숫자형:
--   INT         : 정수 (-21억 ~ 21억)
--   BIGINT      : 큰 정수
--   DECIMAL(M,D): 정밀한 소수 (예: DECIMAL(10,2) → 99999999.99)
--   DOUBLE      : 부동소수점 (근사값, 과학 계산용)
--
-- 문자형:
--   VARCHAR(N)  : 가변 길이 문자열 (최대 N자, 실제 길이만큼만 저장)
--   CHAR(N)     : 고정 길이 문자열 (항상 N자 공간 사용)
--   TEXT        : 긴 문자열 (게시글 본문 등)
--
-- 날짜/시간:
--   DATE        : 날짜 (YYYY-MM-DD)
--   DATETIME    : 날짜 + 시간 (YYYY-MM-DD HH:MM:SS)
--   TIMESTAMP   : 날짜 + 시간 (시간대 자동 변환)

-- [강의: 제약 조건]
-- NOT NULL     : NULL 값 허용하지 않음
-- UNIQUE       : 중복 값 허용하지 않음
-- PRIMARY KEY  : NOT NULL + UNIQUE (테이블의 대표 식별자)
-- FOREIGN KEY  : 다른 테이블의 PK를 참조
-- DEFAULT      : 기본값 설정
-- CHECK        : 조건에 맞는 값만 허용 (예: age >= 0)
-- AUTO_INCREMENT: 자동으로 1씩 증가 (MySQL 전용)

-- 예시: 제약 조건이 적용된 테이블
CREATE TABLE IF NOT EXISTS example_constraints (
    id INT AUTO_INCREMENT PRIMARY KEY,           -- PK + 자동 증가
    username VARCHAR(50) NOT NULL UNIQUE,         -- 필수, 중복 불가
    email VARCHAR(100) NOT NULL,                  -- 필수
    age INT DEFAULT 0 CHECK (age >= 0),           -- 기본값 0, 음수 불가
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 기본값: 현재 시간
);
