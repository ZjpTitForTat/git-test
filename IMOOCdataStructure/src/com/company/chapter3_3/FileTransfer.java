package com.company.chapter3_3;

import java.util.Scanner;

public class FileTransfer {

    private static int[] computers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
       computers = new int[n+1];
        for (int i = 1; i <= n; i++) {
            computers[i] = -1;
        }
        while (true) {
            String s = scanner.next();
            if (s.equals("C")) {
                check(scanner);
            } else if (s.equals("I")) {
                input(scanner);
            } else if (s.equals("S")) {
                int k = 0;
                for (int i = 1; i <= n; i++) {
                    if (computers[i] < 0) {
                        k++;
                    }
                }
                if(k>1){
                    System.out.println("There are "+ k +" components.");
                }else {
                    System.out.println("The network is connected.");
                }
                break;
            }
        }
    }

    public static void check(Scanner scanner){
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        while (computers[a]>0){
            a = computers[a];
        }
        while (computers[b]>0){
            b = computers[b];
        }
        if (a == b) {
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }

    public static void input(Scanner scanner){
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (computers[a] == -1 && computers[b] == -1) {
            computers[a] = b;
            computers[b] = -2;
        }else if (computers[a]!=-1&&computers[b]==-1){
            computers[b] = a;
            while (computers[a]>0){
                a = computers[a];
            }
            computers[a]-=1;
        } else if (computers[a] == -1 && computers[b] != -1) {
            computers[a] = b;
            while (computers[b]>0){
                b = computers[b];
            }
            computers[b]-=1;
        } else if (computers[a] != -1 && computers[b] != -1) {
            while (computers[b] > 0) {
                b = computers[b];
            }
            while (computers[a] > 0) {
                a = computers[a];
            }
            if(computers[a]<computers[b]){
                computers[a] = computers[a]+computers[b];
                computers[b] = a;
            }else{
                computers[b] = computers[a]+computers[b];
                computers[a] = b;
            }
        }
    }


}
