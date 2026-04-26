-- ============================================================
-- 섹션 9. 인덱스2 - 심화
-- ============================================================

USE study_db2;

-- [강의: 옵티마이저와 인덱스 선택]
-- 옵티마이저(Optimizer): MySQL이 쿼리를 실행하기 전에 "가장 효율적인 방법"을 자동으로 선택하는 엔진
-- 인덱스가 여러 개 있으면 옵티마이저가 가장 적합한 인덱스를 자동으로 골라줍니다.
-- 데이터가 적거나 대부분의 행을 읽어야 하면 인덱스를 무시하고 Full Scan을 선택하기도 합니다.


-- [강의: 커버링 인덱스 (Covering Index)]
-- 쿼리에 필요한 모든 컬럼이 인덱스에 포함되어 있어서
-- 테이블 본체를 읽지 않고 인덱스만으로 결과를 반환하는 것
-- 성능이 매우 좋습니다!

-- 예시: emp_name 인덱스만으로 결과를 반환 (테이블 접근 불필요)
EXPLAIN SELECT emp_name FROM employees WHERE emp_name = '홍길동';
-- Extra에 "Using index"가 표시되면 커버링 인덱스가 적용된 것!


-- [강의: 복합 인덱스 (Composite Index)]
-- 여러 컬럼을 하나의 인덱스로 묶은 것
-- 컬럼 순서가 매우 중요합니다!

CREATE INDEX idx_dept_salary ON employees (dept_id, salary);

-- 복합 인덱스는 "왼쪽부터 순서대로" 사용됩니다 (Leftmost Prefix Rule)
-- idx_dept_salary = (dept_id, salary) 일 때:

-- ✅ 인덱스 사용 O
EXPLAIN SELECT * FROM employees WHERE dept_id = 1;                          -- 첫 번째 컬럼 사용
EXPLAIN SELECT * FROM employees WHERE dept_id = 1 AND salary >= 4500;       -- 첫 번째 + 두 번째 사용
EXPLAIN SELECT * FROM employees WHERE dept_id = 1 ORDER BY salary;          -- 정렬에도 활용

-- ❌ 인덱스 사용 X (첫 번째 컬럼을 건너뛰었기 때문)
EXPLAIN SELECT * FROM employees WHERE salary >= 4500;                        -- 두 번째만 사용 → 인덱스 X


-- [강의: 복합 인덱스 정리]
-- 복합 인덱스 (A, B, C) 에서:
-- | WHERE 조건         | 인덱스 사용 |
-- |---------------------|-----------|
-- | A = ?               | O         |
-- | A = ? AND B = ?     | O         |
-- | A = ? AND B = ? AND C = ? | O   |
-- | B = ?               | X (A 빠짐) |
-- | C = ?               | X (A, B 빠짐) |
-- | B = ? AND C = ?     | X (A 빠짐) |
-- 핵심: 항상 왼쪽(첫 번째) 컬럼부터 사용해야 인덱스가 작동합니다!


-- [강의: 인덱스 설계 가이드라인]
-- 1. WHERE 절에 자주 사용되는 컬럼에 인덱스를 걸어라
-- 2. 카디널리티(고유 값의 수)가 높은 컬럼이 인덱스에 유리하다
--    (예: 주민번호 > 성별, 이름 > 부서)
-- 3. 복합 인덱스에서는 카디널리티가 높은 컬럼을 앞에 배치하라
-- 4. SELECT에 사용되는 컬럼까지 인덱스에 포함시키면 커버링 인덱스 효과
-- 5. 정렬(ORDER BY)에 사용되는 컬럼도 인덱스에 포함시키면 유리


-- [강의: 인덱스의 단점과 주의사항]
-- 1. 쓰기 성능 저하: INSERT, UPDATE, DELETE 시 인덱스도 함께 갱신 → 느려짐
-- 2. 저장 공간 사용: 인덱스도 디스크 공간을 차지함
-- 3. 과도한 인덱스: 테이블당 3~5개 정도가 적당 (너무 많으면 역효과)
-- 4. 인덱스가 무시되는 경우:
--    - 데이터가 적을 때 (Full Scan이 더 빠름)
--    - 대부분의 행을 읽어야 할 때
--    - 함수를 적용한 컬럼 (WHERE YEAR(hire_date) = 2020 → 인덱스 X)
--      → WHERE hire_date >= '2020-01-01' AND hire_date < '2021-01-01' 로 변환!
