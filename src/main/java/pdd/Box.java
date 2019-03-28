package pdd;


import java.util.*;

/**
 * 2.纸箱收纳
 * */
public class Box {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //纸箱个数
        int N = scanner.nextInt();

        //Map<纸箱边长, 相同边长的箱子数目>
        Map<Long, Integer> boxMap = new HashMap<>();
        for(int i = 0; i< N; i++){
            long len = scanner.nextLong();
            int value = 1;
            if(null != boxMap.get(len)){
                value = boxMap.get(len) + 1;
            }
            boxMap.put(len, value);
        }

        //最大边长相等箱子数目
        int maxCount = 0;

        //由于边长范围很大，箱子数目范围较小，故可以用循环遍历
        for(Map.Entry<Long,Integer> entry:boxMap.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
            }
        }

        System.out.println(maxCount);
    }

}
