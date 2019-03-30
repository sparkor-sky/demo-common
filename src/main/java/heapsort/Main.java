package heapsort;

import java.util.Random;
import java.util.Scanner;

/**
 *                         0
 *            1                           2
 *      3           4             5              6
 *   7     8     9     10     11     12     13     14
 * 15 16
 *
 * */

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
            HeapUtils.sort(data);
            print(data);
        }
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
