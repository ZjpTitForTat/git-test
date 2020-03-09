package com.company.chapter5_2;


import java.util.Scanner;

public class SwapN {

    private static int[] num;

    public static void main(String[] args) {
        num = read();
        int k = swapN();
        System.out.println(k);
    }

    public static int[] read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        return num;
    }


    public static int swapN(){
        int k = 0;
        int loc = 0;
        for (; loc < num.length; loc++) {
            if (num[loc] == 0) {
                break;
            }
        }
        while (true){
            if (loc == 0) {
                if(check()){
                    break;
                }else {
                    loc = adjust();
                    k++;
                }
            }
            for (int i = 0; i < num.length; i++) {
                if (num[i] == loc) {
                    int temp = num[i];
                    num[i] = num[loc];
                    num[loc] = temp;
                    loc = i;
                    k++;
                    break;
                }
            }
        }
        return k;
    }


    public static boolean check(){
        boolean flag = false;
        int i = 0;
        for (; i < num.length; i++) {
            if (num[i] != i) {
                break;
            }
        }
        if (i==num.length){
            flag = true;
        }
        return flag;
    }

    public static int adjust(){
        int i = 0;
        for (; i < num.length; i++) {
            if (num[i] != i) {
                int temp = num[i];
                num[i] = num[0];
                num[0] = temp;
                break;
            }
        }
        return i;
    }
}
