-- ============================================================
-- 섹션 10. 데이터 무결성
-- ============================================================

USE study_db2;

-- [강의: 데이터 무결성이 중요한 이유]
-- 데이터 무결성(Data Integrity): 데이터가 정확하고 일관된 상태를 유지하는 것
-- 잘못된 데이터가 들어가면 서비스 전체에 문제가 발생합니다.
-- 제약 조건(Constraint)을 사용하여 DB 레벨에서 잘못된 데이터를 원천 차단합니다.


-- [강의: 기본 제약 조건]
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,       -- PK: 고유 식별자
    product_name VARCHAR(100) NOT NULL,              -- NOT NULL: 필수 입력
    price INT NOT NULL CHECK (price >= 0),           -- CHECK: 음수 가격 방지
    category VARCHAR(50) DEFAULT '기타',              -- DEFAULT: 기본값
    sku VARCHAR(50) UNIQUE                            -- UNIQUE: 중복 불가
);


-- [강의: 외래 키 제약 조건 (Foreign Key)]
-- 다른 테이블의 PK를 참조하여 "존재하지 않는 값"이 들어가는 것을 방지합니다.
CREATE TABLE IF NOT EXISTS order_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
    -- product_id는 반드시 products 테이블에 존재하는 값이어야 합니다!
);

-- 데이터 삽입
INSERT INTO products (product_name, price, sku) VALUES ('노트북', 1500000, 'NB-001');
INSERT INTO products (product_name, price, sku) VALUES ('마우스', 30000, 'MS-001');

-- 정상 삽입
INSERT INTO order_items (product_id, quantity) VALUES (1, 2);

-- 외래 키 위반 (존재하지 않는 product_id) → 에러!
-- INSERT INTO order_items (product_id, quantity) VALUES (999, 1);

-- 외래 키가 참조 중인 상품 삭제 시도 → 에러!
-- DELETE FROM products WHERE product_id = 1;

-- 외래 키 옵션:
-- ON DELETE CASCADE:  부모 삭제 시 자식도 함께 삭제
-- ON DELETE SET NULL: 부모 삭제 시 자식의 FK를 NULL로 설정
-- ON DELETE RESTRICT: 자식이 있으면 부모 삭제 불가 (기본값)


-- [강의: CHECK 제약 조건]
-- 특정 조건을 만족하는 값만 허용합니다.
-- 음수 가격 테스트 → 에러!
-- INSERT INTO products (product_name, price, sku) VALUES ('테스트', -100, 'TEST');

-- 0개 주문 테스트 → 에러!
-- INSERT INTO order_items (product_id, quantity) VALUES (1, 0);


-- [정리]
-- | 제약 조건      | 역할                              |
-- |---------------|-----------------------------------|
-- | NOT NULL      | NULL 값 방지                       |
-- | UNIQUE        | 중복 값 방지                       |
-- | PRIMARY KEY   | NOT NULL + UNIQUE (고유 식별자)     |
-- | FOREIGN KEY   | 다른 테이블의 PK만 참조 가능         |
-- | CHECK         | 특정 조건을 만족하는 값만 허용        |
-- | DEFAULT       | 값을 넣지 않으면 기본값 사용          |
