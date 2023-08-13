package Sem2;

import java.util.Random;

public class sortArray {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arrayToSort;
        int iMax = 100;
        int valueMin = 0;
        int valueMax = 100;

        arrayToSort = new int[iMax];
        for (int i = 0; i < iMax; i++) {
            arrayToSort[i] = rand.nextInt(valueMax - valueMin) + valueMin;
        }

        printArrray(arrayToSort);
        printArrray(bubbleSort(arrayToSort), "Сортировка пузырьком");
        printArrray(directSort(arrayToSort), "Сортировка выборкой");
        printArrray(insertSort(arrayToSort), "Сортировка вставкой");

    }

    public static void printArrray(int[] arrayOriginal) {
        printArrray(arrayOriginal, "");
    }

    public static void printArrray(int[] arrayOriginal, String prompt) {
        System.out.println("\nПечать массива: " + prompt);
        for (int i = 0; i < arrayOriginal.length; i++) {
            System.out.print(arrayOriginal[i] + " ");
        }
        System.out.println();
    }

    /**
     * Пузырьковая сортировка
     * 
     * @param arrayOriginal оригинальный массив
     * @return отсортированный массив
     */
    public static int[] bubbleSort(int[] arrayOriginal) {
        int[] array = arrayOriginal.clone();
        int count = 0;
        boolean finish;
        do {
            count++;
            finish = true;
            for (int i = 0; i < array.length - count; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    finish = false;
                }
            }
        } while (!finish);

        System.out.printf("\nСортировка пузырьком, было проходов %d, при двойном for было бы: %d", count, array.length);
        return array;
    }

    /**
     * Сортировка выбором
     * 
     * @param arrayOriginal оригинальный массив
     * @return отсортированный массив
     */
    public static int[] directSort(int[] arrayOriginal) {
        int[] array = arrayOriginal.clone();
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }
            if (minPosition != i) {
                int temp = array[i];
                array[i] = array[minPosition];
                array[minPosition] = temp;
            }
        }
        System.out.printf("\nСортировка выборкой, было проходов %d (двойной for)", array.length - 1);
        return array;
    }

    /**
     * Сортировка вставкой
     * 
     * @param arrayOriginal оригинальный массив
     * @return отсортированный массив
     */
    public static int[] insertSort(int[] arrayOriginal) {
        int[] array = arrayOriginal.clone();
        for (int i = 0; i < array.length - 1; i++){
            for(int j = i + 1; j < array.length; j++){
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.printf("\nСортировка вставкой, было проходов %d (двойной for)", array.length - 1);
        return array;
    }
}