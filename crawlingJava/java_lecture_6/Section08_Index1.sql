-- ============================================================
-- 섹션 8. 인덱스1 - 기본
-- ============================================================

USE study_db2;

-- [강의: 인덱스가 필요한 이유]
-- 인덱스 없이 검색하면 모든 행을 처음부터 끝까지 읽어야 합니다 (Full Table Scan → O(n))
-- 인덱스가 있으면 "목차"를 보고 바로 원하는 페이지로 이동할 수 있습니다 (Index Scan → O(log n))
-- 비유: 책에서 "자바"라는 단어를 찾을 때,
--   인덱스 없음 = 1쪽부터 끝까지 넘기며 찾기
--   인덱스 있음 = 맨 뒤 "찾아보기"에서 "자바 → p.127" 확인하고 바로 이동


-- [강의: 트리 자료 구조 (B-Tree / B+Tree)]
-- MySQL의 인덱스는 내부적으로 B+Tree 구조를 사용합니다.
-- B+Tree: 이진 트리를 확장한 것으로, 각 노드에 여러 키를 저장
-- 루트 → 브랜치 → 리프 순서로 탐색하며, 리프 노드에 실제 데이터 위치가 저장됩니다.
-- 높이가 3~4단계면 수백만 건에서도 3~4번 비교로 찾을 수 있습니다!


-- [강의: 인덱스 생성, 조회, 삭제]

-- 인덱스 생성
CREATE INDEX idx_emp_salary ON employees (salary);
CREATE INDEX idx_emp_name ON employees (emp_name);

-- 인덱스 목록 조회
SHOW INDEX FROM employees;

-- 인덱스 삭제
-- DROP INDEX idx_emp_salary ON employees;


-- [강의: 인덱스와 동등 비교 (=)]
-- 인덱스가 있는 컬럼을 = 비교하면 O(log n)으로 매우 빠릅니다.
EXPLAIN SELECT * FROM employees WHERE emp_name = '홍길동';
-- EXPLAIN을 붙이면 쿼리 실행 계획을 볼 수 있습니다 (인덱스 사용 여부 확인)


-- [강의: 인덱스와 범위 검색]
-- >, <, >=, <=, BETWEEN 등 범위 검색에도 인덱스가 사용됩니다.
EXPLAIN SELECT * FROM employees WHERE salary BETWEEN 4000 AND 5000;
EXPLAIN SELECT * FROM employees WHERE salary >= 4500;


-- [강의: 인덱스와 LIKE 범위 검색]
-- LIKE 검색에서 인덱스 사용 여부:
--   'Kim%'    → 인덱스 사용 O (앞부분이 확정)
--   '%Kim'    → 인덱스 사용 X (앞부분이 불확실)
--   '%Kim%'   → 인덱스 사용 X
EXPLAIN SELECT * FROM employees WHERE emp_name LIKE '홍%';   -- 인덱스 O
EXPLAIN SELECT * FROM employees WHERE emp_name LIKE '%길동';  -- 인덱스 X (Full Scan)


-- [강의: 인덱스와 정렬]
-- 인덱스가 이미 정렬되어 있으므로 ORDER BY 시 별도 정렬이 필요 없습니다.
EXPLAIN SELECT * FROM employees ORDER BY salary;       -- idx_emp_salary 활용
EXPLAIN SELECT * FROM employees ORDER BY salary DESC;  -- 역순도 인덱스 활용 가능
