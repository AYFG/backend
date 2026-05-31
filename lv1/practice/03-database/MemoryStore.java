import java.util.HashMap;
import java.util.Map;

public class MemoryStore {
    public static void main(String[] args) {
        Map<Long, String> store = new HashMap<>();

        store.put(1L, "John");
        store.put(2L, "Park");

        String item = store.get(1L);

        System.out.println(store);
    }

}
