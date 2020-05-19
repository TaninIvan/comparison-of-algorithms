import lombok.Getter;
import org.decimal4j.util.DoubleRounder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

@Getter
public class Matrix {
    private double[][] matrix;

    public void readMatrixFromFile(String pathToFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(pathToFile));
        int n = scanner.nextInt(); // размерность матрицы
        matrix = new double[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.matrix[i][j] = scanner.nextDouble();
    }

    public void generateMatrix(String filename, int dimension) {

        try (FileReader reader = new FileReader("src/main/resources/" + filename + ".txt")) {
            reader.read();
            throw new Exception("Файл с таким именем уже существует!");
        } catch (FileNotFoundException ignored) {
        } catch (Exception e){
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("src/main/resources/" + filename + ".txt",true))
        {
            this.matrix = new double[dimension][dimension];
            writer.append(Integer.toString(dimension));
            writer.append('\n');
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    this.matrix[i][j] = DoubleRounder.round(Math.random()*100, 1);
                    writer.append(Double.toString(DoubleRounder.round(this.matrix[i][j], 1))).append(' ');
                }
                writer.append('\n');
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
