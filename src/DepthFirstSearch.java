import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
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

    public static int solve(KnapsackProblem problem) {
        List<KnapsackItem> items = new ArrayList<>(problem.items);
        int W = problem.capacity;

        items.sort((a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 0, 0, bound(0, 0, 0, items, W)));

        int maxValue = 0;
        int operationCount = 0;

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            operationCount++;

            if (node.bound <= maxValue || node.level >= items.size()) continue;

            KnapsackItem item = items.get(node.level);

            int newWeight = node.weight + item.weight;
            int newValue = node.value + item.value;
            if (newWeight <= W && newValue > maxValue) {
                maxValue = newValue;
            }

            double boundWith = bound(node.level + 1, newValue, newWeight, items, W);
            if (boundWith > maxValue)
                stack.push(new Node(node.level + 1, newValue, newWeight, boundWith));

            double boundWithout = bound(node.level + 1, node.value, node.weight, items, W);
            if (boundWithout > maxValue)
                stack.push(new Node(node.level + 1, node.value, node.weight, boundWithout));
        }

        return operationCount;
    }

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
