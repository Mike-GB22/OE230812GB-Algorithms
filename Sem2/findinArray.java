package Sem2;

import java.util.Random;
import Sem2.sortArray;

public class findinArray {
    public static int count = 0;
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

        int value = 55;

        System.out.println(simpleFind(arrayToSort, value));
        Sem2.sortArray.printArrray(arrayToSort);

        int[] sortedArray = Sem2.sortArray.bubbleSort(arrayToSort);
        Sem2.sortArray.printArrray(sortedArray);
        System.out.println("Ищем элемент [" + value +"], он находится в позиции : " + binarySearch(sortedArray, value));
        System.out.println("Было сделанно итераций разбивки массива: "+ count);


    
    }   
    
    /**
     * Простой поиск в массиве
     * @param array оригиналоный массив
     * @param value искомый элемент
     * @return индекс элемента, если не найденно, то возвращает -1
     */
    public static int simpleFind(int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value) return i;
        }
        return -1;
    }

    /**
     * Бинарный поиск элемента в сортированном массиве
     * @param array сортированный массив
     * @param value значение для поиска
     * @return индекс элемента, отрицательный если не найденно
     */
    public static int binarySearch(int[] array, int value){
        if(value > array[array.length - 1]) return -3;
        if(value < array[0]) return -4;    

        return binarySearch(array, value, 0, array.length - 1);
    }

    /**
     * Бинарный поиск элемента в сортированном массиве
     * @param array сортированный массив
     * @param value значение для поиска
     * @param l граница массива для поиска, слева
     * @param r граница массива для поиска, справа
     * @return индекс элемента, отрицательный если не найденно
    */ 
    public static int binarySearch(int[] array, int value, int l, int r){
        count ++;
        if(r<l) return -1;
        if(r > array.length - 1) return -2;

        int midPoint = (r-l)/2 + l;
        //System.out.print("midPoint: " + midPoint + ", = " + array[midPoint]);
        //System.out.println(" --- l: "+ l + ", r: " + r);

        if(value > array[midPoint])
            return binarySearch(array, value, midPoint + 1, r);
        else if(value < array[midPoint])
            return binarySearch(array, value, l, midPoint - 1);
        else return midPoint;
    }
}
