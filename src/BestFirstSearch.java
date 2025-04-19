import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BestFirstSearch {
    static class Node {
        int level, value, weight;
        double bound;

        public Node(int level, int value, int weight, double bound) {
            this.level = level;
            this.value = value;
            this.weight = weight;
            this.bound = bound;
        }
    }

    // need to solve problem with best first and return number of node expansions
    public static int solve(KnapsackProblem problem) {
        List<KnapsackItem> items = new ArrayList<>(problem.items);
        int W = problem.capacity;

        //sort items by ratio
        items.sort((a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));
        pq.add(new Node(0, 0, 0, bound(0, 0, 0, items, W)));

        int maxValue = 0;
        int operationCount = 0;

        // do the branch and bound loop while counting operations
        while (!pq.isEmpty()) {
            // take the best node first
            Node node = pq.poll();
            operationCount++;

            //check to see if i need this node or skip it
            if (node.bound <= maxValue || node.level >= items.size()){
                continue;
            }


            // if i need it i need to expand it
            KnapsackItem item = items.get(node.level);

            //if i keep current item
            int newWeight = node.weight + item.weight;
            int newValue = node.value + item.value;
            if (newWeight <= W && newValue > maxValue) {
                maxValue = newValue;
            }

            double boundWith = bound(node.level + 1, newValue, newWeight, items, W);
            if (boundWith > maxValue)
                pq.add(new Node(node.level + 1, newValue, newWeight, boundWith));

            // if i dont keep current item
            double boundWithout = bound(node.level + 1, node.value, node.weight, items, W);
            if (boundWithout > maxValue)
                pq.add(new Node(node.level + 1, node.value, node.weight, boundWithout));
        }

        return operationCount;
    }

    //need to calculate upperbound
    private static double bound(int level, int value, int weight, List<KnapsackItem> items, int W) {
        if (weight >= W) return 0;
        double bound = value;
        int totalWeight = weight;

        for (int i = level; i < items.size(); i++) {
            if (totalWeight + items.get(i).weight <= W) {
                totalWeight += items.get(i).weight;
                bound += items.get(i).value;
            } else {
                int remain = W - totalWeight;
                bound += (double) items.get(i).value / items.get(i).weight * remain;
                break;
            }
        }

        return bound;
    }
}
