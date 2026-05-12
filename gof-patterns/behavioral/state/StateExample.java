package gof.behavioral.state;

/**
 * State Pattern (상태 패턴)
 * 
 * 목적: 객체의 내부 상태가 바뀜에 따라 객체의 행동을 변경할 수 있게 해줍니다. 
 * 객체는 마치 클래스를 바꾸는 것처럼 보입니다.
 * Java 17의 Sealed Interface를 사용하여 상태의 종류를 명확히 정의할 수 있습니다.
 */

// 1. 상태 인터페이스 (Sealed)
sealed interface VendingMachineState permits ReadyState, OutOfStockState, ProcessingState {}

// 2. 구체적인 상태들
final class ReadyState implements VendingMachineState {
    public void handle() { System.out.println("대기 중: 동전을 넣어주세요."); }
}

final class ProcessingState implements VendingMachineState {
    public void handle() { System.out.println("처리 중: 상품을 준비하고 있습니다."); }
}

final class OutOfStockState implements VendingMachineState {
    public void handle() { System.out.println("품절: 상품이 없습니다."); }
}

// 3. 컨텍스트
class VendingMachine {
    private VendingMachineState state;

    public VendingMachine() {
        this.state = new ReadyState();
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void request() {
        // Java 17의 Pattern Matching for switch 활용 가능
        switch (state) {
            case ReadyState s -> s.handle();
            case ProcessingState s -> s.handle();
            case OutOfStockState s -> s.handle();
        }
    }
}

class StateMain {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        
        vm.request();
        
        vm.setState(new ProcessingState());
        vm.request();
        
        vm.setState(new OutOfStockState());
        vm.request();
    }
}
