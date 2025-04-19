import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Reads from the file and parses it into Java objects to be used in the program
public class Parser {
    public static List<KnapsackProblem> parse(String filename) throws IOException {
        List<KnapsackProblem> problems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //need to split lines into tokens
                String[] tokens = line.trim().split("\\s+");
                // first int is capacity
                int capacity = Integer.parseInt(tokens[0]);

                List<KnapsackItem> items = new ArrayList<>();

                //next i need to assign the remaining tokens into value and weight
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
