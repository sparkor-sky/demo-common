package pdd;

import java.util.Scanner;

/**
 * 1.比较版本号
 * */
public class VersionCompare {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String version1 = scanner.nextLine();
        String version2 = scanner.nextLine();

        if(null != version1){
            version1 = version1.trim();
        }
        if(null != version2){
            version2 = version2.trim();
        }
        System.out.println(compare(version1, version2));
    }


    private static int compare(String version1, String version2){
        if(version1.startsWith(".")){
            version1 = "0" + version1;
        }
        if(version2.startsWith(".")){
            version2 = "0" + version2;
        }

        String[]  arr1 = version1.split("\\.");
        String[]  arr2 = version2.split("\\.");
        int min = arr1.length > arr2.length ? arr2.length : arr1.length;
        for(int i = 0;i < min;i++){
            try {
                int result = Integer.valueOf(arr1[i]) - Integer.valueOf(arr2[i]);
                if(result != 0){
                    return result > 0 ? 1 : -1;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        if(arr1.length > arr2.length){
            return 1;
        }else if(arr1.length < arr2.length){
            return -1;
        }
        return 0;
    }

}
