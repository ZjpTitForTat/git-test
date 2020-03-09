package com.company.chapter5_3;

import java.util.Scanner;

public class Hashing {

    private static int[] hashTable;
    private static boolean hashFlag = false;
    private static int size;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        size = initTable(m);
        if ((size - 3) % 4 == 0) {
            hashFlag = true;
        }
        hashTable = new int[size];
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
            insert(num[i]);
        }
        print(num);
    }

    public static int initTable(int m){
        int size = m;
        if (size == 2) {
            return size;
        }
        if (size % 2 == 0) {
            size++;
        }
        boolean flag = false;
        while (true) {
            for (int i = 3; i < Math.sqrt(size)+1; i+=2) {
                if (size % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                size+=2;
            }else {
                break;
            }
        }
        return size;
    }

    public static void insert(int k){
            int i = k % size;
            if (hashTable[i] == 0) {
                hashTable[i] = k;
            }else {
                int tempNum = 1;
                while (true) {
                    if (tempNum % 2 == 1) {
                        int temp = i + (tempNum+1)/2 * (tempNum+1)/2;
                        while (temp > size) {
                            temp -= size;
                        }
                        if (hashTable[temp] == 0) {
                            hashTable[temp] = k;
                            break;
                        }else {
                            tempNum++;
                            if (tempNum > size) {
                                break;
                            }
                            continue;
                        }
                    }else {
                        int temp = i - tempNum/2 + tempNum/2;
                        while (temp < 0) {
                            temp += size;
                        }
                        if (hashTable[temp] == 0) {
                            hashTable[temp] = k;
                            break;
                        }else {
                            tempNum++;
                            if (tempNum > size) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }

    }

    public static void print(int[] num){
        for (int i = 0; i < num.length; i++) {
            int j = 0;
            for (; j < size; j++) {
                if (num[i] == hashTable[j]){
                    if (i == 0) {
                        System.out.print(j);
                        break;
                    }else {
                        System.out.print(" "+j);
                        break;
                    }
                }
            }
            if (j == size) {
                System.out.print(" -");
            }
        }
    }
}
