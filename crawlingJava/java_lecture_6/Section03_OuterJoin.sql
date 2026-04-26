-- ============================================================
-- 섹션 3. 조인2 - 외부 조인과 기타 조인
-- ============================================================

USE study_db2;

-- [강의: 외부 조인 (OUTER JOIN)]
-- 내부 조인은 양쪽 다 매칭되는 것만 보여줍니다.
-- 외부 조인은 한쪽에 매칭이 없더라도 결과에 포함시킵니다.

-- LEFT OUTER JOIN (= LEFT JOIN)
-- 왼쪽 테이블(employees)의 모든 행을 보여줍니다.
-- 오른쪽(departments)에 매칭되지 않으면 NULL로 표시됩니다.
SELECT e.emp_name, d.dept_name, e.salary
FROM employees e
    LEFT JOIN departments d ON e.dept_id = d.dept_id;
-- 정다은(dept_id=NULL)도 결과에 포함됨! dept_name은 NULL로 표시

-- RIGHT OUTER JOIN (= RIGHT JOIN)
-- 오른쪽 테이블(departments)의 모든 행을 보여줍니다.
-- 왼쪽(employees)에 매칭되지 않으면 NULL로 표시됩니다.
SELECT e.emp_name, d.dept_name
FROM employees e
    RIGHT JOIN departments d ON e.dept_id = d.dept_id;
-- 디자인팀(dept_id=4)은 직원이 없지만 결과에 포함됨! emp_name은 NULL로 표시


-- [강의: 조인의 특징]
-- INNER JOIN: 교집합 (양쪽 다 있는 것만)
-- LEFT JOIN:  왼쪽 전부 + 교집합
-- RIGHT JOIN: 오른쪽 전부 + 교집합
-- FULL OUTER JOIN: 양쪽 전부 (MySQL은 미지원, UNION으로 대체)

-- FULL OUTER JOIN 대체 (MySQL)
SELECT e.emp_name, d.dept_name FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id
UNION
SELECT e.emp_name, d.dept_name FROM employees e RIGHT JOIN departments d ON e.dept_id = d.dept_id;


-- [강의: 셀프 조인 (Self Join)]
-- 같은 테이블을 자기 자신과 조인하는 것
-- 대표적인 사용 사례: 조직도에서 "상사" 찾기

CREATE TABLE IF NOT EXISTS org_chart (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    manager_id INT  -- 자기 테이블의 emp_id를 참조
);

INSERT INTO org_chart VALUES (1, '대표이사', NULL);
INSERT INTO org_chart VALUES (2, '개발팀장', 1);
INSERT INTO org_chart VALUES (3, '마케팅팀장', 1);
INSERT INTO org_chart VALUES (4, '개발자A', 2);
INSERT INTO org_chart VALUES (5, '개발자B', 2);

-- 셀프 조인으로 직원과 상사를 함께 조회
SELECT
    e.emp_name AS '직원',
    m.emp_name AS '상사'
FROM org_chart e
    LEFT JOIN org_chart m ON e.manager_id = m.emp_id;
-- 같은 테이블을 e(직원)와 m(상사)로 두 번 사용!


-- [강의: CROSS JOIN (교차 조인)]
-- 두 테이블의 모든 조합을 만들어냅니다 (카르테시안 곱).
-- A 테이블 3행 × B 테이블 4행 = 12행
-- 실무에서는 잘 안 쓰지만, 테스트 데이터 생성 등에 활용됩니다.
SELECT e.emp_name, d.dept_name
FROM employees e
    CROSS JOIN departments d;


-- [조인 종합 정리]
-- | 조인 종류      | 결과                          | 키워드          |
-- |---------------|-------------------------------|----------------|
-- | INNER JOIN    | 양쪽 다 매칭되는 것만           | JOIN            |
-- | LEFT JOIN     | 왼쪽 전부 + 매칭되는 오른쪽     | LEFT JOIN       |
-- | RIGHT JOIN    | 오른쪽 전부 + 매칭되는 왼쪽     | RIGHT JOIN      |
-- | SELF JOIN     | 자기 자신과 조인               | JOIN (같은 테이블) |
-- | CROSS JOIN    | 모든 조합 (곱)                | CROSS JOIN      |
