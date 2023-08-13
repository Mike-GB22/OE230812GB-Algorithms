package Sem2;

public class quicksort {
    public static int count = 1;

    public static void main(String[] args) {

        int[] arrayToSort = Sem2.generatearray.generateArray();
        //arrayToSort = new int[] { 1, 3, 2, 3, 4, 2, 6, 9, 8, 7, 8 };
        
        Sem2.sortArray.printArrray(arrayToSort);

        sort(arrayToSort);

        Sem2.sortArray.printArrray(arrayToSort);
        
        System.out.println("---------");
        count = 1;
        int[] arrayToSort2 = new int[] { 1, 3, 2, 3, 4, 2, 6, 9, 8, 7, 8 };
        Sem2.sortArray.printArrray(arrayToSort2);

        sort2(arrayToSort2);

        Sem2.sortArray.printArrray(arrayToSort2);

    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int startPosition, int endPosition) {
        System.out.print("\n[" + count++ + "] startPosition: " + startPosition + ", endPosition: " + endPosition);
        Sem2.sortArray.printArrray(arr);

        int l = startPosition;
        int r = endPosition;
        int pivot = arr[(l + r) / 2];
        System.out.println("pivot: " + pivot);
        do {
            while (arr[l] < pivot) {
                l++;
                System.out.println("L: l :" + l + ", r : " + r + " arr[L]= " + arr[l]);
            }
            while (arr[r] > pivot) {
                r--;
                System.out.println("R: l :" + l + ", r : " + r + " arr[R]= " + arr[r]);
            }

            if (l <= r) {
                if (l < r) {
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                    System.out.println("arr[" + l + "] = " + arr[l] + " <-> arr[" + r + "] = " + arr[r]);
                }
                l++;
                r--;
            }
        } while (l <= r);

        if (l < endPosition)
            sort(arr, l, endPosition);
        if (r > startPosition)
            sort(arr, startPosition, r);
    }

    public static void sort2(int[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    public static void sort2(int[] arr, int startPosition, int endPosition) {
        System.out.print("\n[" + count++ + "] startPosition: " + startPosition + ", endPosition: " + endPosition);
        Sem2.sortArray.printArrray(arr);

        int l = startPosition;
        int r = endPosition;
        int pivot = arr[(l + r) / 2];
        System.out.println("pivot: " + pivot);
        do {
            for(;;l++){
                System.out.println("L: l :" + l + ", r : " + r + " arr[L]= " + arr[l]);
                if(arr[l] > pivot) break;
                
            }
            for(;;r--) {
                System.out.println("R: l :" + l + ", r : " + r + " arr[R]= " + arr[r]);
                if(arr[r] < pivot) break;
            }

            if (l <= r) {
                if (l < r) {
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                    System.out.println("arr[" + l + "] = " + arr[l] + " <-> arr[" + r + "] = " + arr[r]);
                }
                l++;
                r--;
            }
        } while (l <= r);

        if (l < endPosition)
            sort(arr, l, endPosition);
        if (r > startPosition)
            sort(arr, startPosition, r);
    }
    
}
