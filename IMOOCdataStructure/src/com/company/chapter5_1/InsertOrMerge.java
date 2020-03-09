package com.company.chapter5_1;

import java.util.Scanner;

public class InsertOrMerge {
    private static int[] num;
    private static int[] target;

    public static void main(String[] args) {
        read();
        int[] tempNum = reset();
        boolean flag = false;
        int i = 1;
        for (; i < num.length; i++) {
            tempNum = insertSingle(tempNum,i);
            flag = check(tempNum);
            if (flag) {
                break;
            }
        }
        if (flag) {
            System.out.println("Insertion Sort");
            tempNum = insertSingle(tempNum,i+1);
            for (int j = 0; j < num.length; j++) {
                if (j == 0) {
                    System.out.print(tempNum[j]);
                }else {
                    System.out.print(" " + tempNum[j] );
                }

            }
        }
        if (!flag) {
            int length = 1;
            tempNum = reset();
            boolean flag1 = false;
            boolean flag2 = false;
            int[] tempNum2 = reset();
            while (length<num.length){
                tempNum2 = mergeSingle(tempNum,tempNum2,length);
                length*=2;
                flag1 = check(tempNum2);
                if (flag1) {
                    break;
                }
                tempNum = mergeSingle(tempNum2,tempNum,length);
                length*=2;
                flag2 = check(tempNum);
                if (flag2) {
                    break;
                }
            }
            if (flag1||flag2) {
                System.out.println("Merge Sort");
                if (flag1) {
                    tempNum = mergeSingle(tempNum2,tempNum,length);
                    for (int j = 0; j < num.length; j++) {
                        if (j == 0) {
                            System.out.print(tempNum[j]);
                        }else {
                            System.out.print(" " + tempNum[j] );
                        }

                    }
                } else if (flag2) {
                    tempNum2 = mergeSingle(tempNum,tempNum2,length);
                    for (int j = 0; j < num.length; j++) {
                        if (j == 0) {
                            System.out.print(tempNum2[j]);
                        }else {
                            System.out.print(" " + tempNum2[j] );
                        }

                    }
                }
            }
        }


    }

    public static int[] reset(){
        int[] tempNum = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            tempNum[i] = num[i];
        }
        return tempNum;
    }

    public static void read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        num = new int[n];
        target = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            target[i] = scanner.nextInt();
        }
    }

    public static boolean check(int[] tempNum){
        boolean flag = true;
        for (int i = 0; i < num.length; i++) {
            if(target[i]!=tempNum[i]){
                flag = false;
            }
        }
        return flag;
    }

    public static int[] insertSingle(int[] tempNum,int k){
        int i = k-1;
        int temp = tempNum[k];
        for (; i >= 0 && tempNum[k] < tempNum[i] ; i--) {
            tempNum[i+1] = tempNum[i];
        }
        tempNum[i+1] = temp;
        return tempNum;
    }

    public static int[] mergeSingle(int[] num,int[] tempNum,int length){
        int i = 0;
        for (; i <= num.length-2*length; i+=length*2) {
            tempNum = merge(num,tempNum,length,i,i+2*length-1);
        }
        if (i + length >= num.length) {
            for ( ; i < num.length; i++) {
                tempNum[i] = num[i];
            }
        }else{
            tempNum = merge(num,tempNum,length,i,num.length-1);
        }
        return tempNum;
    }

    public static int[] merge(int[] num,int[] tempNum,int length,int start,int end){
        int i = start;
        int j = start+length;
        int tempI = start;
        while (i <= start + length-1 && j <= end) {
            if(num[i]>num[j]){
                tempNum[tempI] = num[j];
                j++;
                tempI++;
            }else {
                tempNum[tempI] = num[i];
                i++;
                tempI++;
            }
        }
        if (i == start + length) {
            for ( ; j <= end; j++) {
                tempNum[tempI] = num[j];
                tempI++;
            }
        } else if (j == end + 1) {
            for ( ; i < start+length; i++) {
                tempNum[tempI] = num[i];
                tempI++;
            }
        }
        return tempNum;
    }
}
