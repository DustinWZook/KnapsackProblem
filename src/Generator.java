import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
    public static void generate(String filename, int count) throws IOException {
        Random rand = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < count; i++) {
                int capacity = rand.nextInt(11) + 15;
                writer.write(capacity + " ");
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
