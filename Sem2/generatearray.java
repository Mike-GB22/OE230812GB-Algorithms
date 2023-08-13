package Sem2;
import java.util.Random;

public class generatearray {

    public static int[] generateArray() {
        Random rand = new Random();
        int[] arrayToSort;
        int iMax = 100;
        int valueMin = 0;
        int valueMax = 100;

        arrayToSort = new int[iMax];
        for (int i = 0; i < iMax; i++) {
            arrayToSort[i] = rand.nextInt(valueMax - valueMin) + valueMin;
        }

        return arrayToSort;
    }

}
