package com.company.chapter5_2;

import java.util.Scanner;

public class SortAge {
    public static void main(String[] args) {
        int[] age = read();
        print(age);
    }

    public static int[] read(){
        Scanner scanner = new Scanner(System.in);
        int[] age = new int[51];
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            age[k] += 1;
        }
        return age;
    }

    public static int[] sort(int[] age){
        for (int i = 0; i < age.length; i++) {
            for (int j = 0; j < age.length-i-1; j++) {
                if(age[j]>age[j+1]){
                    int temp = age[j];
                    age[j] = age[j+1];
                    age[j+1] = temp;
                }
            }
        }
        return age;
    }

    public static void print(int[] age){
        for (int i = 0; i < age.length; i++) {
            if (age[i] == 0) {
                continue;
            }else {
                System.out.println(i+ ":" + age[i]);
            }
        }
    }
}
