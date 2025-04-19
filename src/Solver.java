import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    public static void evaluateAll(List<KnapsackProblem> problems) {
        run("Best-First", problems, BestFirstSearch::solve);
        run("Breadth-First", problems, BreadthFirstSearch::solve);
        run("Depth-First", problems, DepthFirstSearch::solve);
    }

    private static void run(String label, List<KnapsackProblem> problems,
                            java.util.function.Function<KnapsackProblem, Integer> strategy) {
        List<Integer> ops = new ArrayList<>();
        for (KnapsackProblem problem : problems) {
            ops.add(strategy.apply(problem));
        }

        int min = Collections.min(ops);
        int max = Collections.max(ops);
        double avg = ops.stream().mapToInt(i -> i).average().orElse(0);
        double standardDeviation = Math.sqrt(ops.stream().mapToDouble(i -> Math.pow(i - avg, 2)).sum() / ops.size());

        System.out.printf("%s Strategy Stats:\n", label);
        System.out.printf("Min: %d\nMax: %d\nAvg: %.2f\nStd Dev: %.2f\n\n", min, max, avg, standardDeviation);
    }
}
