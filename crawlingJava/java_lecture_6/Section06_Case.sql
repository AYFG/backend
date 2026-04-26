-- ============================================================
-- 섹션 6. CASE 문
-- ============================================================

USE study_db2;

-- [강의: CASE 문 기본]
-- SQL에서 if-else와 같은 조건 분기를 수행합니다.

-- 단순 CASE 문 (값 비교)
SELECT emp_name, dept_id,
    CASE dept_id
        WHEN 1 THEN '개발팀'
        WHEN 2 THEN '마케팅팀'
        WHEN 3 THEN '인사팀'
        ELSE '미배정'
    END AS '부서명'
FROM employees;

-- 검색 CASE 문 (조건 비교) - 더 유연한 방식
SELECT emp_name, salary,
    CASE
        WHEN salary >= 5000 THEN 'S등급'
        WHEN salary >= 4500 THEN 'A등급'
        WHEN salary >= 4000 THEN 'B등급'
        ELSE 'C등급'
    END AS '급여 등급'
FROM employees;


-- [강의: CASE 문 - 그룹핑]
-- CASE로 새로운 분류를 만들고 GROUP BY로 집계
SELECT
    CASE
        WHEN salary >= 5000 THEN '고연봉'
        WHEN salary >= 4000 THEN '중연봉'
        ELSE '저연봉'
    END AS '급여 구간',
    COUNT(*) AS '인원수',
    AVG(salary) AS '평균 급여'
FROM employees
GROUP BY
    CASE
        WHEN salary >= 5000 THEN '고연봉'
        WHEN salary >= 4000 THEN '중연봉'
        ELSE '저연봉'
    END;


-- [강의: CASE 문 - 조건부 집계 (Pivot)]
-- 행 데이터를 열로 변환하는 피벗(Pivot) 기법
-- 부서별 인원수를 한 줄로 보기
SELECT
    COUNT(CASE WHEN dept_id = 1 THEN 1 END) AS '개발팀',
    COUNT(CASE WHEN dept_id = 2 THEN 1 END) AS '마케팅팀',
    COUNT(CASE WHEN dept_id = 3 THEN 1 END) AS '인사팀',
    COUNT(CASE WHEN dept_id IS NULL THEN 1 END) AS '미배정'
FROM employees;

-- 부서별 급여 합계를 한 줄로 보기
SELECT
    SUM(CASE WHEN dept_id = 1 THEN salary ELSE 0 END) AS '개발팀 급여',
    SUM(CASE WHEN dept_id = 2 THEN salary ELSE 0 END) AS '마케팅팀 급여',
    SUM(CASE WHEN dept_id = 3 THEN salary ELSE 0 END) AS '인사팀 급여'
FROM employees;
