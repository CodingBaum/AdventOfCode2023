package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/Day1/input"));
        List<Integer> ergList;
        int erg = 0;

        Map<String, String> mapping = new HashMap<>();
        mapping.put("one", "1");
        mapping.put("two", "2");
        mapping.put("three", "3");
        mapping.put("four", "4");
        mapping.put("five", "5");
        mapping.put("six", "6");
        mapping.put("seven", "7");
        mapping.put("eight", "8");
        mapping.put("nine", "9");

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (String a : mapping.keySet()) {
                s = s.replaceAll(a, a+mapping.get(a)+a);
            }
            newList.add(s);
        }

        for (int i = 0; i < newList.size(); i++) {
            ergList = new ArrayList<>();
            for (String s : newList.get(i).split("")) {
                try {
                    int a = Integer.parseInt(s);
                    ergList.add(a);
                } catch (NumberFormatException ignored) {}
            }
            System.out.println(ergList.get(0) + "" + ergList.get(ergList.size()-1));
            erg += Integer.parseInt(ergList.get(0) + "" + ergList.get(ergList.size()-1));
        }

        System.out.println(erg);
    }
}
