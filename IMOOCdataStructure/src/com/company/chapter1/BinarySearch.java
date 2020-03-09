package com.company.chapter1;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        int findNum = scanner.nextInt();
        int find = binarySearch(num,0,n-1,findNum);
        System.out.print(find+1);
    }

    private static int binarySearch(int[] num,int left,int right,int findNum){
        int middle = (left+right)/2;
        int find = -1;
        if (left!=right){
            if(num[middle]>=findNum){
                find = binarySearch(num,left,middle,findNum);
            }else {
                find = binarySearch(num,middle+1,right,findNum);
            }
        }else {
            if(num[left]==findNum){
                find = left;
            }
        }
        return find;
    }
}
