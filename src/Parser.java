import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<KnapsackProblem> parse(String filename) throws IOException {
        List<KnapsackProblem> problems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                int capacity = Integer.parseInt(tokens[0]);
                List<KnapsackItem> items = new ArrayList<>();
                for (int i = 1; i < tokens.length; i += 2) {
                    int value = Integer.parseInt(tokens[i]);
                    int weight = Integer.parseInt(tokens[i + 1]);
                    items.add(new KnapsackItem(value, weight));
                }
                problems.add(new KnapsackProblem(capacity, items));
            }
        }

        return problems;
    }
}
