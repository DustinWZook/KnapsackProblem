import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Solve using Breadth-First Search
public class BreadthFirstSearch {
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

    // solve with breadth first and return the number of expansions
    public static int solve(KnapsackProblem problem) {
        List<KnapsackItem> items = new ArrayList<>(problem.items);
        int W = problem.capacity;

        // sort by ratio in descending order
        items.sort((a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, bound(0, 0, 0, items, W)));

        int maxValue = 0;
        int operationCount = 0;

        // do loop in fifo
        while (!queue.isEmpty()) {
            // take front of queue
            Node node = queue.poll();
            operationCount++;

            // prune if bound is worse or all items are covered
            if (node.bound <= maxValue || node.level >= items.size()) continue;

            KnapsackItem item = items.get(node.level);

            // include current item
            int newWeight = node.weight + item.weight;
            int newValue = node.value + item.value;
            if (newWeight <= W && newValue > maxValue) {
                maxValue = newValue;
            }

            double boundWith = bound(node.level + 1, newValue, newWeight, items, W);
            if (boundWith > maxValue)
                queue.add(new Node(node.level + 1, newValue, newWeight, boundWith));

            // dont include current item
            double boundWithout = bound(node.level + 1, node.value, node.weight, items, W);
            if (boundWithout > maxValue)
                queue.add(new Node(node.level + 1, node.value, node.weight, boundWithout));
        }

        return operationCount;
    }

    // calculate bound
    private static double bound(int level, int value, int weight, List<KnapsackItem> items, int W) {
        if (weight >= W) return 0;
        double bound = value;
        int totalWeight = weight;

        // use greedy approach then fraction last item
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
