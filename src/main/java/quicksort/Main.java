package quicksort;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (true){
            int n = scanner.nextInt();
            int[] data = new int[n];
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                data[i] = random.nextInt(100);
            }
            quickSort(data,0,data.length);
            print(data);
        }
    }

    /**
     * 快速排序，排序范围为data数组从下标[index1,index2)
     * @param data  待排序数组
     * @param index1 排序起始下标  包括index1
     * @param index2 排序结束下标  不包括index2
     */
    public static void quickSort(int[] data, int index1, int index2){
        if(data == null || data.length == 1){
            return;
        }
        if(index2 - index1 < 2){
            return;
        }

        int pivot = data[index1];
        // data[left]比pivot大  或者left表示数组最后一个元素
        int left = index1;
        int right = index2 - 1;
        while (left < right){
            //找到比pivot小的数
            while (data[right] >= pivot && left < right){
                right--;
            }
            data[left] = data[right];
            //找到比pivot大的数
            while (data[left] <= pivot && left < right){
                left++;
            }
            data[right] = data[left];
        }
        data[left] = pivot;
        quickSort(data,index1,left);
        quickSort(data,left + 1,index2);
    }

    /**
     * 打印数组结果
     */
    public static void print(int[] data){
        if(data == null)return;
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            if(i == 0){
                System.out.print(data[i]);
            }else {
                System.out.print(" --> " + data[i]);
            }
        }

        System.out.println();
    }
}
