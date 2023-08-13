package Sem2;

public class heapsort {
    public static void main(String[] args) {
        int[] arrayToSort;
        //arrayToSort = Sem2.generatearray.generateArray();
        arrayToSort = new int[] { 1, 3, 2, 3, 4, 2, 6, 9, 8, 7, 8 };

        Sem2.sortArray.printArrray(arrayToSort);

        heapSort(arrayToSort);
        Sem2.sortArray.printArrray(arrayToSort);

    }

    public static void heapSort(int[] array) {
        // Задаем рамеры кучи внутри массива
        int indexOfLastElementOfHeap = array.length - 1;

        while(indexOfLastElementOfHeap > 0){
            // Начинаем с нижнего правого треугольника, затем поднимаемаемся по
            // треугольникам по очереди, до верху
            for (int elementOfTriangleToSift = indexOfLastElementOfHeap; elementOfTriangleToSift > -1; elementOfTriangleToSift -= 2) {
                // Находим родителя, для данного треугольника (не важно левый это или правый
                // ребенок)
                int rootIndexTriangleToSift = (elementOfTriangleToSift - elementOfTriangleToSift % 2) / 2;

                //Просеиваем этот треугольник
                sift(array, indexOfLastElementOfHeap, rootIndexTriangleToSift);

                //System.out.println(" rootIndexTriangleToSift: " + rootIndexTriangleToSift);
            }
            //После первого прохода по всей кучи, мы имеем на верху максимальный элемент
            //Ложем этот элемент в конец массива, размер кучи уменьшаем на 1
            int temp = array[0];
            array[0] = array[indexOfLastElementOfHeap];
            array[indexOfLastElementOfHeap--] = temp;
        }
    }

    public static void sift (int[] arr, int indexOfLastElementOfHeap, int rootIndexTriangleToSift){
        int leftChild = rootIndexTriangleToSift * 2 + 1;
        int rightChild = leftChild + 1;

        // Смотрим какой элеменент из 3х самый большой
        int indexOfLargestElement = rootIndexTriangleToSift;

        if(leftChild <= indexOfLastElementOfHeap && arr[leftChild] > arr[indexOfLargestElement])
            indexOfLargestElement = leftChild;

        if(rightChild <= indexOfLastElementOfHeap && arr[rightChild] > arr[indexOfLargestElement])
            indexOfLargestElement = rightChild;    

        if(indexOfLargestElement != rootIndexTriangleToSift){
            int temp = arr[indexOfLargestElement];
            arr[indexOfLargestElement] = arr[rootIndexTriangleToSift];
            arr[rootIndexTriangleToSift] = temp;
            int indexOfElementThatWasChanged = indexOfLargestElement;

            //Раз мы сделали изменения, должны посмотреть все вложенные треугольники в этой части кучи
            sift(arr, indexOfLargestElement, indexOfElementThatWasChanged);
        }
    }
}
