#include <stdio.h>

/**
 * [컴퓨터 구조 및 메모리 계층 구조 종합 데모]
 * 
 * 1. CPU 내부: 제어 장치(CU), 연산 장치(ALU), 레지스터(Register)
 * 2. 캐시 메모리: L1, L2, L3 (지역성의 원리)
 * 3. 메모리 접근: 주소 지정과 데이터 읽기
 * 4. 명령어 처리: 명령어 레지스터(IR), 메모리 데이터 레지스터(MDR/MBR)
 */

// 2D 배열을 활용한 공간 지역성(Spatial Locality) 데모를 위한 상수
#define ROWS 1000
#define COLS 1000

int main() {
    printf("=== 컴퓨터 구조 및 CPU 동작 원리 데모 ===\n\n");

    /*
     * [1. 레지스터 (Register)]
     * CPU 내부에 위치한 가장 빠른 저장소입니다.
     * register 키워드는 이 변수를 레지스터에 둘 수 있음을 컴파일러에 힌트로 주는
     * 오래된 C 키워드입니다.
     * 다만 현대 컴파일러는 이 힌트를 무시할 수 있으며, 실제 배치는 컴파일러 최적화가 결정합니다.
     */
    register int fast_counter = 0;
    for (int i = 0; i < 100; i++) {
        fast_counter += i; // 실제 연산은 CPU가 수행하며, 값의 저장 위치는 컴파일러가 결정합니다.
    }
    printf("[1] Register 활용 연산 결과: %d\n", fast_counter);


    /*
     * [2. 메모리 주소와 데이터 읽기]
     * 포인터(ptr)는 메모리의 한 위치를 가리키는 '주소값'입니다.
     * CPU가 실제로 메모리를 읽을 때는 내부적으로 MAR(주소 레지스터), MDR/MBR(데이터 레지스터) 같은
     * 개념을 통해 주소를 지정하고 데이터를 받아옵니다.
     * 즉, 포인터 자체가 곧 MAR은 아니고, 메모리 접근 과정을 이해하기 위한 예시로 보는 편이 정확합니다.
     */
    int memory_data = 1234;
    int *address_example = &memory_data; // 프로그램이 다루는 주소값
    int loaded_value = *address_example; // 해당 주소에서 읽어 온 값

    printf("[2] 프로그램이 사용하는 주소값(보통 가상 주소): %p\n", (void*)address_example);
    printf("[2] 해당 주소에서 읽은 데이터 값: %d\n\n", loaded_value);


    /*
     * [3. 공간 지역성 (Spatial Locality) & 캐시 메모리]
     * A 방식: 가로로 읽기 (a[i][j]) - 캐시 히트율 높음 (효율적)
     * B 방식: 세로로 읽기 (a[j][i]) - 캐시 미스율 높음 (비효율적)
     * 
     * CPU 캐시(L1, L2, L3)는 한 번 메모리에서 가져올 때 주변 데이터까지 
     * 캐시 라인(cache line) 단위로 함께 가져와서 다음 접근 속도를 높입니다.
     */
    static int arr[ROWS][COLS]; // 스택 오버플로우 방지를 위해 static 선언
    long long sum = 0;

    // 가로로 순차 접근 (Spatial Locality 활용)
    // C의 2차원 배열은 행 우선(row-major)으로 저장되므로, 같은 행을 순서대로 읽으면 캐시 효율이 좋습니다.
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            sum += arr[i][j]; 
        }
    }
    printf("[3] 공간 지역성 활용 (가로 읽기) 완료\n");
    printf("    -> 예를 들어 캐시 라인이 64바이트이고 int가 4바이트라면, 인접한 값 약 %d개가 함께 캐시에 들어올 수 있습니다.\n\n", 16);


    /*
     * [4. 명령어 레지스터 (IR - Instruction Register)]
     * 우리가 짠 이 코드들이 기계어로 변환되어 실행될 때,
     * 현재 실행 중인 '명령어(예: ADD, JUMP)'는 IR에 보관됩니다.
     * 제어 장치(CU)는 IR의 명령어를 해석하고, 필요한 연산이나 메모리 접근을 제어합니다.
     */
    printf("[4] 명령어 처리 원리:\n");
    printf("    - Fetch: 메모리에서 명령어를 가져옴\n");
    printf("    - Decode: 제어 장치(CU)가 IR의 명령어를 해석\n");
    printf("    - Execute: ALU가 연산 수행\n\n");

    printf("==========================================\n");
    return 0;
}
