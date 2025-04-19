import java.util.List;

public class KnapsackProblem {
    public int capacity;
    public List<KnapsackItem> items;

    public KnapsackProblem(int capacity, List<KnapsackItem> items) {
        this.capacity = capacity;
        this.items = items;
    }
}
