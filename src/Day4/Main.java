package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/Day4/input"));
        Map<Integer, Integer> copies = new HashMap<>();

        int erg = input.size();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < copies.getOrDefault(i, 0)+1; j++) {
                String s = input.get(i);
                String[] winningNums = s.split(": ")[1].split(" \\| ")[0].split(" ");
                String[] myNums = s.split(": ")[1].split(" \\| ")[1].split(" ");

                int value = 0;

                for (String a : myNums) {
                    if (a.equals("")) continue;
                    if (List.of(winningNums).contains(a)) {
                        value++;
                    }
                }

                erg += value;
                for (int k = 1; k <= value; k++) {
                    copies.put(i+k, copies.getOrDefault(i+k, 0)+1);
                }
            }
        }

        System.out.println(erg);
    }
}
