package com.company.chapter4_2;

import java.util.Scanner;

public class HarryPotter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] d = new int[n][n];
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i!=j) {
                    d[i][j] = 101;
                }
            }
            path[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int dist = scanner.nextInt();
            d[start-1][end-1] = dist;
            d[end-1][start-1] = dist;
            path[start-1] = end-1;
            path[end-1] = start;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(d[i][k]+d[k][j]<d[i][j]){
                        d[i][j] = d[i][k]+d[k][j];
                        d[j][i] = d[i][j];
                        path[j] = k;
                    }
                }
            }
        }
        int maxId = 0;
        int minMaxDist = 101;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            int maxDist = d[i][0];
            for (int j = 0; j < n; j++) {
                if (d[i][j] > maxDist) {
                    maxDist = d[i][j];
                } else if (d[i][j] == 101) {
                    flag = true;
                    break;
                }
            }
            if (flag==true){
                break;
            }else if(maxDist<minMaxDist){
                minMaxDist = maxDist;
                maxId = i+1;
            }
        }
        if (flag == true) {
            System.out.println("0");
        }else {
            System.out.println(maxId+" "+minMaxDist);
        }
    }
}