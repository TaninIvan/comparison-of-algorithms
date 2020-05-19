import algorithms.Algorithm;
import org.decimal4j.util.DoubleRounder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Control {

    static String algorithmName;
    static double sum;
    static double runtime;
    static boolean newFile = false;
    static int[][] result;

    public static void measureTime(int numberOfStarts, Algorithm algorithm, double[][] matrix) {

        algorithmName = algorithm.getName();
        List<Long> runtimeStatistics = new ArrayList<>();

        while(numberOfStarts > 0) {
            long start = System.currentTimeMillis();
            result = algorithm.execute();
            long finish = System.currentTimeMillis();
            runtimeStatistics.add(finish - start);
            numberOfStarts--;
        }

        sum = getSum(matrix);
        writeResults(result);
        runtime = runtimeStatistics
                .stream()
                .mapToLong(i -> i)
                .average()
                .orElse(0.0);
    }

    private static void writeResults(int[][] result) {

        try (FileReader reader = new FileReader("src/main/resources/SpeedTestResults.csv")) {
            reader.read();
        } catch (FileNotFoundException e){
            newFile = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("src/main/resources/SpeedTestResults.csv", true))
        {
            if (newFile) {
                writer.append("Algorithm Name").append(String.valueOf(';'));
                writer.append("Dimension").append(String.valueOf(';'));
                writer.append("Sum").append(String.valueOf(';'));
                writer.append("Runtime").append(String.valueOf(';'));
                writer.append("Result").append(String.valueOf(';'));
                writer.append('\n');
                newFile = false;
            }

            writer.append(algorithmName).append(String.valueOf(';'));
            writer.append(Integer.toString(result.length)).append(String.valueOf(';'));
            writer.append(Double.toString(DoubleRounder.round(sum,1))).append(String.valueOf(';'));
            writer.append(Double.toString(runtime)).append(String.valueOf(';'));
            writer.append(Arrays.deepToString(result)).append(String.valueOf(';'));
            writer.append('\n');

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static double getSum(double[][] matrix) {
        double sum = 0.0;

        for (int i = 0;i < matrix.length;i++)
            sum += matrix[result[i][0]][result[i][1]];
        return sum;
    }
}
