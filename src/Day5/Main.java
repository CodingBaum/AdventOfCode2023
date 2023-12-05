package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/Day5/input"));

        List<String> seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).toList();
        List<String> ergs = new ArrayList<>(seeds);

        System.out.println(ergs);

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains("map")) {
                i++;
                List<String> mappings = new ArrayList<>();
                for (; i < input.size(); i++) {
                    if (input.get(i).equals("")) break;
                    mappings.add(input.get(i));
                }

                seedMapping:for (int j = 0; j < ergs.size(); j+=2) {
                    for (int k = 0; k < mappings.size(); k++) {
                        long destRange = Long.parseLong(mappings.get(k).split(" ")[0]);
                        long srcRange = Long.parseLong(mappings.get(k).split(" ")[1]);
                        long rangeLength = Long.parseLong(mappings.get(k).split(" ")[2]);
                        long seed = Long.parseLong(ergs.get(j));

                        /*System.out.println("Seed: " + seed);
                        System.out.println(destRange);
                        System.out.println(srcRange);
                        System.out.println(rangeLength);*/

                        if (seed >= srcRange && seed <= srcRange+rangeLength) {
                            ergs.remove(j);
                            ergs.add(j, (destRange + (seed - srcRange))+"");

                            continue seedMapping;
                        }
                    }
                }
            }
        }

        System.out.println("-------------------------------------------");
        System.out.println("Ergebnis: " + ergs.stream().min(String::compareTo).get());
    }
}
