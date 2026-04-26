-- ============================================================
-- 섹션 5. UNION
-- ============================================================

USE study_db2;

-- [강의: UNION]
-- 두 개 이상의 SELECT 결과를 하나로 합칩니다.
-- 규칙: 각 SELECT의 컬럼 수와 데이터 타입이 동일해야 합니다.
-- UNION은 자동으로 중복을 제거합니다.

-- 개발팀 직원과 마케팅팀 직원을 합쳐서 조회
SELECT emp_name, salary, '개발팀' AS team FROM employees WHERE dept_id = 1
UNION
SELECT emp_name, salary, '마케팅팀' AS team FROM employees WHERE dept_id = 2;


-- [강의: UNION ALL]
-- UNION과 동일하지만 중복을 제거하지 않습니다.
-- 중복 제거 작업이 없으므로 UNION보다 빠릅니다.
-- 중복이 없다는 것이 확실하면 UNION ALL을 사용하는 것이 성능상 유리합니다.

SELECT emp_name FROM employees WHERE dept_id = 1
UNION ALL
SELECT emp_name FROM employees WHERE dept_id = 2;


-- [강의: UNION 정렬]
-- UNION 결과를 정렬하려면 마지막에 ORDER BY를 사용합니다.
-- 개별 SELECT에는 ORDER BY를 넣을 수 없습니다!

SELECT emp_name, salary, '개발팀' AS team FROM employees WHERE dept_id = 1
UNION
SELECT emp_name, salary, '마케팅팀' AS team FROM employees WHERE dept_id = 2
ORDER BY salary DESC;  -- 전체 결과를 급여 내림차순으로 정렬


-- [UNION 정리]
-- | 구분        | 중복 제거 | 성능    |
-- |------------|---------|--------|
-- | UNION      | O       | 느림    |
-- | UNION ALL  | X       | 빠름    |
-- 실무 팁: 중복이 없다는 것이 확실하면 항상 UNION ALL을 사용하세요!
