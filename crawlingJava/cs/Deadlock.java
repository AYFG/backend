package cs;

/**
 * [데드락(Deadlock) 실습]
 * 
 * 이 프로그램은 두 개의 스레드가 서로가 가진 자원을 무한히 기다리게 만들어
 * 프로그램이 끝나지 않는 '교착 상태'를 재현합니다.
 */
public class Deadlock {

    // 1. 상호 배제(Mutual Exclusion): 한 번에 한 명만 쓸 수 있는 자원 2개를 준비합니다.
    private static final Object ResourceA = new Object();
    private static final Object ResourceB = new Object();

    public static void main(String[] args) {

        // [스레드 1: ResourceA를 먼저 잡고 ResourceB를 기다림]
        Thread thread1 = new Thread(() -> {
            synchronized (ResourceA) { // ResourceA 점유
                System.out.println("스레드 1: ResourceA 점유 완료 (Hold)");

                try {
                    // 점유와 대기(Hold and Wait)를 유도하기 위해 잠시 대기
                    // 스레드 2가 ResourceB를 잡을 시간을 줍니다.
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("스레드 1: ResourceB를 기다리는 중... (Wait)");
                synchronized (ResourceB) { // ResourceB 대기
                    System.out.println("스레드 1: 모든 자원 점유 성공!");
                }
            }
        });

        // [스레드 2: ResourceB를 먼저 잡고 ResourceA를 기다림]
        Thread thread2 = new Thread(() -> {
            synchronized (ResourceB) { // ResourceB 점유
                System.out.println("스레드 2: ResourceB 점유 완료 (Hold)");

                try {
                    // 점유와 대기(Hold and Wait)를 유도하기 위해 잠시 대기
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("스레드 2: ResourceA를 기다리는 중... (Wait)");
                synchronized (ResourceA) { // ResourceA 대기
                    System.out.println("스레드 2: 모든 자원 점유 성공!");
                }
            }
        });

        // 스레드 시작
        thread1.start();
        thread2.start();
    }
}

/*
 * [데드락 발생의 4가지 필수 조건]
 * 
 * 1. 상호 배제 (Mutual Exclusion)
 * - 정의: 자원은 한 번에 한 프로세스(또는 스레드)만 사용할 수 있어야 합니다.
 * - 설명: 만약 여러 명이 동시에 쓸 수 있는 자원이라면 애초에 서로 기다릴 일이 없겠죠? 
 *         하지만 프린터나 특정 메모리 영역처럼 공유가 불가능한 자원일 때 문제가 생깁니다.
 * 
 * 2. 점유와 대기 (Hold and Wait)
 * - 정의: 최소한 하나의 자원을 점유하고 있으면서, 다른 프로세스가 사용 중인 자원을 추가로 얻기 위해 대기하고 있는 상태여야 합니다.
 * - 설명: "내가 가진 건 안 놓을 거야, 근데 네가 가진 것도 필요해"라고 버티는 상황입니다.
 * 
 * 3. 비선점 (No Preemption)
 * - 정의: 다른 프로세스에 할당된 자원은 사용이 끝날 때까지 강제로 빼앗을 수 없어야 합니다.
 * - 설명: 강제로 뺏어올 수 있다면(선점 가능하다면) 기다릴 필요 없이 뺏어서 일을 끝내면 되는데, 그게 안 되는 상황입니다.
 * 
 * 4. 환형 대기 (Circular Wait)
 * - 정의: 대기자의 집합이 원형을 이루며 서로의 자원을 기다리는 상태입니다.
 * - 설명: A는 B가 가진 걸 기다리고, B는 C가 가진 걸 기다리고, C는 다시 A가 가진 걸 기다리는 꼬리에 꼬리를 무는 순환 구조입니다.
 */
