import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String filename = "problems.txt";
        Generator.generate(filename, 100);
        List<KnapsackProblem> problems = Parser.parse(filename);
        Solver.evaluateAll(problems);
    }
}