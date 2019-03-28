package pdd;

import java.util.Scanner;

/**
 * 3.打乱字符串树
 * */
public class DisorderedTree {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        if(null != str1){
            str1 = str1.trim();
        }
        if(null != str2){
            str2 = str2.trim();
        }

        System.out.println(judge(str1, str2));
    }

    private static int judge(String str1, String str2){
        if(str1.length() != str2.length()){
            return 0;
        }

        if("".equals(str1)){
            return 1;
        }

        if(str1.length() == 1){
            if(str1.equals(str2)){
                return 1;
            }else {
                return 0;
            }
        }

        int middle = str1.length()/2;

        String preStr1 = str1.substring(0,middle);
        String sufStr1 = str1.substring(middle);

        String preStr2 = str2.substring(0,middle);
        String sufStr2 = str2.substring(middle);


        if (compare(preStr1, sufStr1, preStr2, sufStr2)) {
            return 1;
        }

        if(middle * 2 != str1.length()){
            middle = middle + 1;
            preStr2 = str2.substring(0,middle);
            sufStr2 = str2.substring(middle);
        }

        if (compare(preStr1, preStr2, sufStr2, sufStr1)) {
            return 1;
        }

        return 0;
    }

    private static boolean compare(String preStr1, String sufStr1,
                                   String preStr2, String sufStr2) {
        if(same(preStr1,preStr2) && same(sufStr1,sufStr2)) {
            int result = judge(preStr1,preStr2);

            if(result == 1){
                result = judge(sufStr1,sufStr2);
                if(result == 1){
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean same(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }

        for(int i=0;i< str1.length();i++){
            str2 = str2.replaceFirst(str1.charAt(i)+"","");
        }

        if("".equals(str2)){
            return true;
        }
        return false;
    }
}
