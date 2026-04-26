-- ============================================================
-- 섹션 12. 저장 프로시저, 함수, 트리거
-- ============================================================

USE study_db2;

-- [강의: 프로시저, 함수, 트리거 소개]
-- 저장 프로시저(Stored Procedure): DB에 저장되는 "프로그램". 여러 SQL을 묶어서 한 번에 실행.
-- 함수(Function): 값을 반환하는 저장 프로시저. SELECT에서 사용 가능.
-- 트리거(Trigger): 특정 이벤트(INSERT, UPDATE, DELETE) 발생 시 자동으로 실행되는 코드.


-- [강의: 저장 프로시저 (Stored Procedure)]
DELIMITER //

CREATE PROCEDURE IF NOT EXISTS sp_get_employees_by_dept(IN p_dept_id INT)
BEGIN
    SELECT e.emp_name, e.salary, d.dept_name
    FROM employees e
        JOIN departments d ON e.dept_id = d.dept_id
    WHERE e.dept_id = p_dept_id;
END //

DELIMITER ;

-- 프로시저 호출
CALL sp_get_employees_by_dept(1);  -- 개발팀 직원 조회
CALL sp_get_employees_by_dept(2);  -- 마케팅팀 직원 조회

-- 프로시저 삭제
-- DROP PROCEDURE sp_get_employees_by_dept;


-- [강의: 함수 (Function)]
DELIMITER //

CREATE FUNCTION IF NOT EXISTS fn_salary_grade(p_salary INT)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE grade VARCHAR(20);
    IF p_salary >= 5000 THEN SET grade = 'S등급';
    ELSEIF p_salary >= 4500 THEN SET grade = 'A등급';
    ELSEIF p_salary >= 4000 THEN SET grade = 'B등급';
    ELSE SET grade = 'C등급';
    END IF;
    RETURN grade;
END //

DELIMITER ;

-- 함수 사용 (SELECT에서 호출 가능)
SELECT emp_name, salary, fn_salary_grade(salary) AS '등급' FROM employees;


-- [강의: 트리거 (Trigger)]
-- 특정 테이블에 INSERT/UPDATE/DELETE가 발생하면 자동으로 실행됩니다.

-- 감사 로그 테이블
CREATE TABLE IF NOT EXISTS employee_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    action_type VARCHAR(20),
    emp_name VARCHAR(50),
    old_salary INT,
    new_salary INT,
    changed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //

CREATE TRIGGER IF NOT EXISTS trg_salary_update
    AFTER UPDATE ON employees
    FOR EACH ROW
BEGIN
    IF OLD.salary != NEW.salary THEN
        INSERT INTO employee_log (action_type, emp_name, old_salary, new_salary)
        VALUES ('UPDATE', NEW.emp_name, OLD.salary, NEW.salary);
    END IF;
END //

DELIMITER ;

-- 트리거 테스트: 급여 변경
UPDATE employees SET salary = 5500 WHERE emp_name = '홍길동';

-- 자동으로 로그가 기록되었는지 확인
SELECT * FROM employee_log;


-- [강의: 데이터베이스 로직의 함정과 현대적 대안]
-- ⚠️ 현대 개발에서는 프로시저/트리거를 남용하지 않는 것이 추세입니다.
--
-- 단점:
-- 1. 디버깅이 어렵다 (에러 추적이 힘듦)
-- 2. 버전 관리가 어렵다 (Git으로 관리하기 힘듦)
-- 3. 테스트가 어렵다 (단위 테스트 작성이 어려움)
-- 4. 이식성이 낮다 (MySQL ↔ PostgreSQL 간 문법이 다름)
--
-- 현대적 대안:
-- → 비즈니스 로직은 애플리케이션(Java/Spring 등)에서 처리하고,
--    DB는 데이터 저장과 조회에 집중하는 것이 권장됩니다.
-- → 단, 간단한 감사 로그(트리거)나 대량 데이터 처리(프로시저)는 여전히 유용합니다.
