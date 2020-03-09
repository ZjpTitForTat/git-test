package com.company.chapter5_1;

import java.util.Scanner;

public class SortTest {

    public static void main(String[] args) {
        int[] num = read();
        MergeSort sort = new MergeSort();
        int[] numSort = sort.sort(num);
        print(numSort);
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
    public static void print(int[] num){
        for (int i = 0; i < num.length; i++) {
            if (i==0){
                System.out.print(num[i]);
            }else {
                System.out.print(" "+num[i]);
            }

        }
    }
}
class BubbleSort{
    public int[] sort(int[] num){
        int temp;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length-i-1; j++) {
                if(num[j]>num[j+1]){
                    temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
        }
        return num;
    }
}
class InsertionSort{
    public int[] sort(int[] num){
        int temp;
        for (int i = 1; i < num.length; i++) {
            temp = num[i];
            int k = i;
            for (int j = i-1; j >= 0&&temp<num[j] ; j--) {
                    num[j+1] = num[j];
                    k=j;
            }
            num[k] = temp;
        }
        return num;
    }
}
class ShellSort{
    public int[] sort(int[] num){
        int k = 0;
        while (true) {
            if ((int) Math.pow(2, k) - 1 < num.length &&
                    (int) Math.pow(2, k + 1) - 1 >= num.length) {
                break;
            }
            k++;
        }
        for (int i = k; i > 0 ; i--) {
            int l = (int) Math.pow(2,i)-1;
            for (int j = l; j < num.length; j++) {
                int temp = num[j];
                int m;
                for (m = j-l ; m >= 0 && num[m] > temp   ; m-=l ) {
                    num[m+l] = num[m];
                }
                num[m+l] = temp;
            }
        }
        return num;
    }
}
class MergeSort{
    public int[] sort(int[] num){
        int[] tempNum = new int[num.length];
        int length = 1;
        while (length < num.length) {
            tempNum = mergePass(num,tempNum,length);
            length*=2;
            num = mergePass(tempNum,num,length);
            length*=2;
        }
        return num;
    }

    public int[] mergePass(int[] num,int[] tempNum,int length){
        int i = 0;
        for (; i <= num.length-2*length; i+=length*2) {
            tempNum = merge(num,tempNum,length,i,i+2*length-1);
        }
        if (i + length >= num.length) {
            for (; i < num.length; i++) {
                tempNum[i] = num[i];
            }
        }else {
            tempNum = merge(num,tempNum,length,i,num.length-1);
        }
        return tempNum;
    }

    public int[] merge(int[] num,int[] numTemp,int length,int start,int end){
        int i = start;
        int j = start+length;
        int tempI = start;
        while (i<=start+length-1&&j<=end){
            if (num[i] <= num[j]){
                numTemp[tempI] = num[i];
                i++;
            }else {
                numTemp[tempI] = num[j];
                j++;
            }
            tempI++;
        }
        if(i>start+length-1){
            while (j<=end){
                numTemp[tempI] = num[j];
                j++;
                tempI++;
            }
        } else if (j > end) {
            while (i <= start + length - 1) {
                numTemp[tempI] = num[i];
                i++;
                tempI++;
            }
        }
        return numTemp;
    }
}