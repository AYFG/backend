-- ============================================================
-- 섹션 7. 뷰 (View)
-- ============================================================

USE study_db2;

-- [강의: 뷰(View) 소개]
-- 뷰: 자주 사용하는 SELECT 쿼리를 저장해두고 테이블처럼 사용하는 가상 테이블
-- 실제 데이터를 저장하지 않고, 호출할 때마다 원본 SELECT를 실행합니다.
-- 비유: 자주 쓰는 전화번호를 "즐겨찾기"에 등록해두는 것과 같습니다.


-- [강의: 뷰 생성 (CREATE VIEW)]
CREATE OR REPLACE VIEW v_emp_dept AS
SELECT
    e.emp_id,
    e.emp_name,
    d.dept_name,
    e.salary,
    e.hire_date
FROM employees e
    LEFT JOIN departments d ON e.dept_id = d.dept_id;

-- 뷰를 테이블처럼 사용
SELECT * FROM v_emp_dept;
SELECT * FROM v_emp_dept WHERE salary >= 4500;


-- [강의: 뷰 수정 (ALTER VIEW)]
-- CREATE OR REPLACE VIEW로 덮어쓰기가 더 일반적
CREATE OR REPLACE VIEW v_emp_dept AS
SELECT
    e.emp_id,
    e.emp_name,
    d.dept_name,
    e.salary,
    e.hire_date,
    CASE
        WHEN e.salary >= 5000 THEN '고연봉'
        ELSE '일반'
    END AS salary_grade  -- 등급 컬럼 추가
FROM employees e
    LEFT JOIN departments d ON e.dept_id = d.dept_id;

SELECT * FROM v_emp_dept;


-- [강의: 뷰 삭제 (DROP VIEW)]
-- DROP VIEW v_emp_dept;
-- DROP VIEW IF EXISTS v_emp_dept;  -- 존재할 때만 삭제


-- [강의: 뷰의 장점과 단점]
-- 장점:
--   1. 복잡한 쿼리를 단순화 (한 번 만들어두면 간단하게 호출)
--   2. 보안 강화 (민감한 컬럼을 숨기고 필요한 컬럼만 노출)
--   3. 논리적 데이터 독립성 (테이블 구조가 바뀌어도 뷰만 수정하면 됨)
--
-- 단점:
--   1. 성능 이슈 (복잡한 뷰는 매번 쿼리가 실행되므로 느릴 수 있음)
--   2. 수정 제한 (조인이 포함된 뷰는 INSERT/UPDATE가 어려울 수 있음)
