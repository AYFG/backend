-- ============================================================
-- 섹션 11. 트랜잭션 (Transaction)
-- ============================================================

USE study_db2;

-- [강의: 트랜잭션이 필요한 이유]
-- 예시: 계좌 이체 (A → B에게 100만원 송금)
--   1단계: A 계좌에서 100만원 차감 (UPDATE)
--   2단계: B 계좌에 100만원 추가 (UPDATE)
-- 만약 1단계 실행 후 서버가 죽으면? → A의 돈만 사라지고 B는 받지 못함!
-- 이를 방지하기 위해 "둘 다 성공하거나, 둘 다 실패"하게 만드는 것이 트랜잭션입니다.

CREATE TABLE IF NOT EXISTS accounts (
    account_id INT PRIMARY KEY,
    owner VARCHAR(50) NOT NULL,
    balance INT NOT NULL CHECK (balance >= 0)
);

INSERT INTO accounts VALUES (1, '홍길동', 1000000);
INSERT INTO accounts VALUES (2, '김철수', 500000);


-- [강의: 커밋, 롤백 (COMMIT, ROLLBACK)]

-- 트랜잭션 시작
START TRANSACTION;

-- 이체 실행
UPDATE accounts SET balance = balance - 100000 WHERE account_id = 1;  -- 홍길동 -10만
UPDATE accounts SET balance = balance + 100000 WHERE account_id = 2;  -- 김철수 +10만

-- 결과 확인 (아직 확정 안 됨)
SELECT * FROM accounts;

-- 확정하기 (모든 변경사항을 영구 반영)
COMMIT;

-- 만약 문제가 생겨서 되돌리고 싶다면?
-- ROLLBACK;  -- START TRANSACTION 이후의 모든 변경을 취소!


-- [강의: 트랜잭션의 ACID 속성]
-- A (Atomicity, 원자성):
--   트랜잭션은 "전부 실행" 또는 "전부 취소" 중 하나. 부분 실행은 없습니다.
--   비유: 편의점 도시락은 반만 살 수 없다. 전부 사거나 전부 안 사거나.
--
-- C (Consistency, 일관성):
--   트랜잭션 전후로 데이터가 일관된 상태를 유지해야 합니다.
--   예: 이체 전후로 총 금액의 합은 동일해야 한다.
--
-- I (Isolation, 격리성):
--   동시에 실행되는 트랜잭션들이 서로 영향을 주지 않아야 합니다.
--   트랜잭션 A가 작업 중인 데이터를 트랜잭션 B가 보거나 수정하면 안 됩니다.
--
-- D (Durability, 지속성):
--   COMMIT된 데이터는 시스템이 다운되더라도 영구적으로 보존됩니다.
--   디스크에 기록되기 때문에 전원이 꺼져도 데이터가 유지됩니다.


-- [강의: 트랜잭션 격리 수준 (Isolation Level)]
-- 동시에 여러 트랜잭션이 실행될 때, 어느 수준까지 서로를 보호할지 결정합니다.
--
-- | 격리 수준             | Dirty Read | Non-Repeatable Read | Phantom Read | 성능  |
-- |----------------------|-----------|-------------------|-------------|------|
-- | READ UNCOMMITTED     | O         | O                 | O           | 가장 빠름 |
-- | READ COMMITTED       | X         | O                 | O           | 빠름    |
-- | REPEATABLE READ      | X         | X                 | O           | 보통    |
-- | SERIALIZABLE         | X         | X                 | X           | 가장 느림 |
--
-- MySQL 기본값: REPEATABLE READ
-- 실무에서는 대부분 READ COMMITTED 또는 REPEATABLE READ를 사용합니다.

-- Dirty Read: 아직 COMMIT 안 된 다른 트랜잭션의 데이터를 읽는 것
-- Non-Repeatable Read: 같은 쿼리를 두 번 실행했는데 결과가 달라지는 것
-- Phantom Read: 같은 조건으로 조회했는데 행이 추가/삭제되어 있는 것

-- 격리 수준 확인
SELECT @@transaction_isolation;

-- 격리 수준 변경 (세션 단위)
-- SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
