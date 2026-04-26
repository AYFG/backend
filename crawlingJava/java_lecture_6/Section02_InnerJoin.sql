-- ============================================================
-- 섹션 2. 조인1 - 내부 조인 (INNER JOIN)
-- ============================================================

-- [실습 데이터 준비]
CREATE DATABASE IF NOT EXISTS study_db2;
USE study_db2;

CREATE TABLE IF NOT EXISTS departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(50) NOT NULL,
    dept_id INT,
    salary INT NOT NULL,
    hire_date DATE NOT NULL
);

INSERT INTO departments (dept_name) VALUES ('개발팀'), ('마케팅팀'), ('인사팀'), ('디자인팀');

INSERT INTO employees (emp_name, dept_id, salary, hire_date) VALUES
    ('홍길동', 1, 5000, '2020-03-15'),
    ('김철수', 1, 4500, '2021-07-01'),
    ('이영희', 2, 4800, '2019-11-20'),
    ('박민수', 2, 4200, '2022-01-10'),
    ('최수진', 3, 5200, '2018-06-01'),
    ('정다은', NULL, 3800, '2023-02-15');  -- 부서 미배정


-- [강의: 조인이 필요한 이유]
-- employees 테이블에는 dept_id(숫자)만 있고, 부서 이름은 departments 테이블에 있습니다.
-- 직원 이름과 부서 이름을 함께 보려면 두 테이블을 "연결(조인)"해야 합니다.

-- 조인 없이 조회하면?
SELECT * FROM employees;     -- dept_id는 보이지만 부서 "이름"은 모릅니다.
SELECT * FROM departments;


-- [강의: 내부 조인 (INNER JOIN)]
-- 두 테이블에서 조건이 일치하는 행만 결합하여 보여줍니다.
-- 일치하지 않는 행은 결과에 포함되지 않습니다.

-- 기본 문법
SELECT e.emp_name, d.dept_name, e.salary
FROM employees e
    INNER JOIN departments d ON e.dept_id = d.dept_id;
-- ⚠️ 정다은(dept_id=NULL)은 departments와 매칭되는 행이 없으므로 결과에서 제외됩니다!

-- 별칭(Alias) 설명:
-- employees e  → employees 테이블을 "e"라는 약칭으로 부르겠다
-- departments d → departments 테이블을 "d"라는 약칭으로 부르겠다
-- ON 절: 어떤 컬럼을 기준으로 연결할지 명시

-- 조건을 추가한 내부 조인
SELECT e.emp_name, d.dept_name, e.salary
FROM employees e
    INNER JOIN departments d ON e.dept_id = d.dept_id
WHERE e.salary >= 4500;

-- JOIN은 INNER JOIN의 축약형 (동일하게 동작)
SELECT e.emp_name, d.dept_name
FROM employees e
    JOIN departments d ON e.dept_id = d.dept_id;
