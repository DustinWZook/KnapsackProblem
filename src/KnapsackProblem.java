import java.util.List;
//represents a Knapsack problem with capacity and list of items
public class KnapsackProblem {
    // using capacity because using weight is tripping me up in other parts of the code when trying to track individual weight
    public int capacity;
    public List<KnapsackItem> items;

    public KnapsackProblem(int capacity, List<KnapsackItem> items) {
        this.capacity = capacity;
        this.items = items;
    }
}
