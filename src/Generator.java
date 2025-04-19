import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Generates the random knapsack problems and writes them to a text file
public class Generator {
    public static void generate(String filename, int count) throws IOException {
        Random rand = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < count; i++) {
                // have a capacity between 11 and 25
                int capacity = rand.nextInt(11) + 15;
                writer.write(capacity + " ");

                //generates random values bewtween 10 and 50 and weight between 1 and 9
                for (int j = 0; j < 5; j++) {
                    int value = rand.nextInt(41) + 10;
                    int weight = rand.nextInt(9) + 1;
                    writer.write(value + " " + weight + " ");
                }
                writer.newLine();
            }
        }
    }
}
