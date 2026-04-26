-- ============================================================
-- 섹션 7. SQL - 집계와 그룹핑
-- ============================================================

USE study_db;

-- [집계와 그룹핑 실습 데이터 준비]
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(50) NOT NULL,
    product VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    order_date DATE NOT NULL
);

-- 실습 데이터 삽입
INSERT INTO orders (member_name, product, price, quantity, order_date) VALUES
    ('홍길동', '노트북', 1500000, 1, '2024-01-15'),
    ('홍길동', '마우스', 30000, 2, '2024-01-15'),
    ('김철수', '키보드', 80000, 1, '2024-02-10'),
    ('이영희', '모니터', 350000, 1, '2024-02-20'),
    ('이영희', '마우스', 30000, 1, '2024-03-05'),
    ('박민수', '노트북', 1800000, 1, '2024-03-10'),
    ('박민수', '키보드', 120000, 1, '2024-03-10'),
    ('최수진', '모니터', 400000, 2, '2024-04-01'),
    ('홍길동', '키보드', 100000, 1, '2024-04-15'),
    ('김철수', '노트북', 1600000, 1, '2024-05-01');


-- [강의: 집계 함수]
-- COUNT: 행의 개수
SELECT COUNT(*) AS '전체 주문 수' FROM orders;
SELECT COUNT(DISTINCT member_name) AS '주문한 회원 수' FROM orders;

-- SUM: 합계
SELECT SUM(price * quantity) AS '총 매출' FROM orders;

-- AVG: 평균
SELECT AVG(price) AS '평균 단가' FROM orders;

-- MAX / MIN: 최대, 최소
SELECT MAX(price) AS '최고 단가', MIN(price) AS '최저 단가' FROM orders;


-- [강의: GROUP BY - 그룹으로 묶기]
-- 회원별 주문 수와 총 결제 금액
SELECT
    member_name AS '회원',
    COUNT(*) AS '주문 횟수',
    SUM(price * quantity) AS '총 결제 금액'
FROM orders
GROUP BY member_name;

-- 상품별 판매량
SELECT
    product AS '상품',
    COUNT(*) AS '판매 횟수',
    SUM(quantity) AS '총 판매 수량',
    AVG(price) AS '평균 단가'
FROM orders
GROUP BY product;

-- 월별 매출 집계
SELECT
    DATE_FORMAT(order_date, '%Y-%m') AS '월',
    COUNT(*) AS '주문 건수',
    SUM(price * quantity) AS '매출'
FROM orders
GROUP BY DATE_FORMAT(order_date, '%Y-%m')
ORDER BY '월';


-- [강의: GROUP BY - 주의사항]
-- ⚠️ SELECT에 GROUP BY에 없는 일반 컬럼을 넣으면 안 됩니다!
-- SELECT member_name, product, COUNT(*) FROM orders GROUP BY member_name;
-- → product는 그룹당 여러 값이 있을 수 있으므로 어떤 값을 보여줄지 알 수 없음!


-- [강의: HAVING - 그룹 필터링]
-- WHERE는 그룹화 전 개별 행을 필터링
-- HAVING은 그룹화 후 그룹을 필터링
--
-- 주문 횟수가 2번 이상인 회원만 조회
SELECT
    member_name AS '회원',
    COUNT(*) AS '주문 횟수',
    SUM(price * quantity) AS '총 결제 금액'
FROM orders
GROUP BY member_name
HAVING COUNT(*) >= 2;

-- 평균 단가가 10만원 이상인 상품만 조회
SELECT
    product AS '상품',
    AVG(price) AS '평균 단가'
FROM orders
GROUP BY product
HAVING AVG(price) >= 100000;


-- [강의: SQL 실행 순서]
-- SQL은 작성 순서와 실행 순서가 다릅니다!
--
-- [작성 순서]                    [실행 순서]
-- 1. SELECT                     5. SELECT
-- 2. FROM                       1. FROM ← 먼저 테이블을 정한다
-- 3. WHERE                      2. WHERE ← 개별 행을 필터링한다
-- 4. GROUP BY                   3. GROUP BY ← 그룹으로 묶는다
-- 5. HAVING                     4. HAVING ← 그룹을 필터링한다
-- 6. ORDER BY                   6. ORDER BY ← 정렬한다
-- 7. LIMIT                      7. LIMIT ← 개수를 제한한다
--
-- 이 순서를 이해하면 WHERE와 HAVING의 차이가 명확해집니다!
-- WHERE: FROM 직후 실행 → 그룹화 전 필터링
-- HAVING: GROUP BY 직후 실행 → 그룹화 후 필터링
