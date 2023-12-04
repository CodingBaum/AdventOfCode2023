package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/Day2/input"));

        int erg = 0;

        outerLoop:for (String s :input) {
            int id = Integer.parseInt(s.split(":")[0].split(" ")[1]);

            Map<String, Integer> records = new HashMap<>();
            records.put("blue", 0);
            records.put("green", 0);
            records.put("red", 0);

            for (String a :s.split(": ")[1].split("; ")) {
                Map<String, Integer> validation = new HashMap<>();
                validation.put("blue", 0);
                validation.put("green", 0);
                validation.put("red", 0);

                for (String b :a.split(", ")) {
                    validation.put(b.split(" ")[1], validation.get(b.split(" ")[1]) + Integer.parseInt(b.split(" ")[0]));
                }

                for (String c :validation.keySet()) {
                    if (records.get(c) < validation.get(c)) {
                        records.put(c, validation.get(c));
                    }
                }
            }

            System.out.println(records);

            int sum = 1;

            for (int i :records.values()) {
                sum *= i;
            }

            erg += sum;
        }

        System.out.println("------------------------------------------------");
        System.out.println("Ergebnis: " + erg);
    }
}
