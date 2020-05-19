import algorithms.HungarianAlgorithm;
import algorithms.lapjv.Lapjv;

public class Main {
    public static void main(String[] args) throws Exception {

        Matrix task1 = new Matrix();
        task1.readMatrixFromFile("src/main/resources/task1.txt");

        HungarianAlgorithm hungarian = new HungarianAlgorithm(task1.getMatrix());
        Lapjv lapjv = new Lapjv(task1.getMatrix());

        Control.measureTime(5,hungarian, task1.getMatrix());
        Control.measureTime(5,lapjv, task1.getMatrix());

        Matrix test = new Matrix();
        test.generateMatrix("test",400);

        HungarianAlgorithm hungarian1 = new HungarianAlgorithm(test.getMatrix());
        Lapjv lapjv1 = new Lapjv(test.getMatrix());
        Control.measureTime(5,hungarian1, test.getMatrix());
        Control.measureTime(5,lapjv1, test.getMatrix());
    }
}
