package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/Day3/input"));

        int sumOfGearRatios = calculateGearRatios(input);
        System.out.println("Sum of gear ratios: " + sumOfGearRatios);
    }

    public static int calculateGearRatios(List<String> engineSchematic) {
        int sum = 0;

        for (int i = 0; i < engineSchematic.size(); i++) {
            for (int j = 0; j < engineSchematic.get(i).length(); j++) {
                if (engineSchematic.get(i).charAt(j) == '*') {
                    int adjacentPartNumbers = countAdjacentPartNumbers(engineSchematic, i, j);
                    System.out.println("adjacent: " + adjacentPartNumbers);
                    if (adjacentPartNumbers > 1) {
                        int gearRatio = calculateGearRatio(engineSchematic, i, j);
                        sum += gearRatio;
                    }
                }
            }
        }
        return sum;
    }

    public static int countAdjacentPartNumbers(List<String> engineSchematic, int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < engineSchematic.size() &&
                        j >= 0 && j < engineSchematic.get(i).length() &&
                        engineSchematic.get(i).charAt(j) != '*' &&
                        Character.isDigit(engineSchematic.get(i).charAt(j))) {
                    if (Character.isDigit(engineSchematic.get(i).charAt(j+1))) {
                        j++;
                        if (Character.isDigit(engineSchematic.get(i).charAt(j+1))) {
                            j++;
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    public static int calculateGearRatio(List<String> engineSchematic, int row, int col) {
        List<Integer> adjacentNumbers = new ArrayList<>();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < engineSchematic.size() &&
                        j >= 0 && j < engineSchematic.get(i).length() &&
                        engineSchematic.get(i).charAt(j) != '*' &&
                        Character.isDigit(engineSchematic.get(i).charAt(j))) {
                    String num = engineSchematic.get(i).charAt(j)+"";
                    if (Character.isDigit(engineSchematic.get(i).charAt(j-1))) {
                        num = engineSchematic.get(i).charAt(j-1) + num;
                        if (Character.isDigit(engineSchematic.get(i).charAt(j-2))) {
                            num = engineSchematic.get(i).charAt(j-2) + num;
                        }
                    }
                    if (Character.isDigit(engineSchematic.get(i).charAt(j+1))) {
                        num += engineSchematic.get(i).charAt(j+1);
                        j++;
                        if (Character.isDigit(engineSchematic.get(i).charAt(j+1))) {
                            num += engineSchematic.get(i).charAt(j+1);
                            j++;
                        }
                    }

                    System.out.println(num);

                    adjacentNumbers.add(Integer.parseInt(num));
                }
            }
        }

        System.out.println(adjacentNumbers);

        int gearRatio = 1;
        for (int number : adjacentNumbers) {
            gearRatio *= number;
        }
        return gearRatio;
    }
}
