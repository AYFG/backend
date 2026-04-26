-- ============================================================
-- 섹션 4. 서브쿼리 (Subquery)
-- ============================================================

USE study_db2;

-- [강의: 서브쿼리 소개]
-- 서브쿼리: SQL 안에 또 다른 SQL을 넣는 것 (쿼리 속의 쿼리)
-- 바깥 쿼리를 "메인 쿼리", 안쪽 쿼리를 "서브쿼리"라고 합니다.


-- [강의: 스칼라 서브쿼리]
-- 하나의 값(단일 행, 단일 컬럼)을 반환하는 서브쿼리
-- WHERE 절에서 사용
SELECT * FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
-- "평균 급여보다 높은 직원"을 조회


-- [강의: 다중 행 서브쿼리]
-- 여러 행을 반환하는 서브쿼리 → IN, ANY, ALL과 함께 사용

-- IN: 서브쿼리 결과 목록 중 하나와 일치
SELECT * FROM employees
WHERE dept_id IN (SELECT dept_id FROM departments WHERE dept_name IN ('개발팀', '마케팅팀'));

-- ALL: 서브쿼리 결과 모두와 비교
SELECT * FROM employees
WHERE salary > ALL (SELECT salary FROM employees WHERE dept_id = 2);
-- 마케팅팀(dept_id=2)의 모든 급여보다 높은 직원

-- ANY: 서브쿼리 결과 중 하나라도 만족
SELECT * FROM employees
WHERE salary > ANY (SELECT salary FROM employees WHERE dept_id = 2);
-- 마케팅팀의 급여 중 하나라도 넘는 직원


-- [강의: 다중 컬럼 서브쿼리]
-- 여러 컬럼을 반환하는 서브쿼리
-- 부서별 최고 급여를 받는 직원 조회
SELECT * FROM employees
WHERE (dept_id, salary) IN (
    SELECT dept_id, MAX(salary) FROM employees
    WHERE dept_id IS NOT NULL
    GROUP BY dept_id
);


-- [강의: 상관 서브쿼리 (Correlated Subquery)]
-- 메인 쿼리의 값을 서브쿼리에서 참조하는 형태
-- 행마다 서브쿼리가 실행됨 → 성능에 주의!

-- 자기 부서의 평균 급여보다 높은 급여를 받는 직원
SELECT e.emp_name, e.salary, e.dept_id
FROM employees e
WHERE e.salary > (
    SELECT AVG(e2.salary)
    FROM employees e2
    WHERE e2.dept_id = e.dept_id  -- 메인 쿼리의 e.dept_id를 참조!
);

-- EXISTS: 서브쿼리의 결과가 "존재하는지"만 확인 (true/false)
-- 직원이 1명이라도 있는 부서만 조회
SELECT d.dept_name
FROM departments d
WHERE EXISTS (
    SELECT 1 FROM employees e WHERE e.dept_id = d.dept_id
);


-- [강의: SELECT 서브쿼리 (인라인 뷰)]
-- SELECT 절에서 서브쿼리 사용 → 각 행마다 계산된 값을 추가
SELECT
    e.emp_name,
    e.salary,
    (SELECT AVG(salary) FROM employees) AS '전체 평균',
    e.salary - (SELECT AVG(salary) FROM employees) AS '평균과의 차이'
FROM employees e;


-- [강의: 테이블 서브쿼리 (FROM 절 서브쿼리)]
-- FROM 절에 서브쿼리를 넣어 임시 테이블처럼 사용
SELECT dept_name, avg_salary
FROM (
    SELECT d.dept_name, AVG(e.salary) AS avg_salary
    FROM employees e
        JOIN departments d ON e.dept_id = d.dept_id
    GROUP BY d.dept_name
) AS dept_avg  -- FROM 절 서브쿼리에는 반드시 별칭(Alias)이 필요!
WHERE avg_salary >= 4500;


-- [강의: 서브쿼리 vs JOIN 정리]
-- | 상황                    | 추천           |
-- |------------------------|---------------|
-- | 다른 테이블의 값과 비교   | 서브쿼리       |
-- | 두 테이블 데이터를 합쳐서 보기 | JOIN      |
-- | 집계 결과와 비교          | 서브쿼리       |
-- | 성능이 중요한 대량 데이터  | JOIN (보통 더 빠름) |
