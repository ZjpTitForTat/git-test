package com.company.chapter5_3;

import java.util.Scanner;

public class PhoneCrazy {

    private static String[] hashTable;
    private static int[] count;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        initTable(n);
        for (int i = 0; i < n*2; i++) {
            insertAndCount(scanner.next());
        }
        print();
    }


    public static void initTable(int n){
        int i = n*2;
        while (true){
            if ((i - 3) % 4 == 0) {
                int j = 3;
                for (; j < (int) Math.sqrt((double) i); j+=2) {
                    if (i % j == 0) {
                        break;
                    }
                }
                if (j >= (int) Math.sqrt((double) i)) {
                    break;
                }
            }
            i++;
        }
        hashTable = new String[i];
        count = new int[i];
    }

    public static void insertAndCount(String s){
        int strNum = Integer.parseInt(s.substring(0,5));
        int num = strNum % hashTable.length;
        int countNum = 0;
        while (true) {
            if (hashTable[num]!=null){
                if (hashTable[num].equals(s)) {
                    count[num]++;
                    break;
                }else {
                    if (++countNum % 2 != 0) {
                        int numTemp = num + ((countNum+1)/2)*((countNum+1)/2);
                        if (hashTable[numTemp] == null){
                            hashTable[numTemp] = s;
                            count[numTemp] = 1;
                            break;
                        }else {
                            if (hashTable[numTemp].equals(s)) {
                                count[numTemp]++;
                                break;
                            }else {
                                continue;
                            }
                        }
                    }else {
                        int numTemp = num - (countNum/2)*(countNum/2);
                        if (hashTable[numTemp] == null) {
                            hashTable[numTemp] = s;
                            count[numTemp] = 1;
                        }else {
                            if (hashTable[numTemp].equals(s)) {
                                count[numTemp]++;
                                break;
                            }else {
                                continue;
                            }
                        }
                    }
                }

            }else {
                hashTable[num] = s;
                count[num] = 1;
                break;
            }
        }
    }

    public static void print(){
        int max = count[0];
        String maxStr = "";
        int tempNum = 1;
        for (int i = 1; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                maxStr = hashTable[i];
                tempNum = 1;
            } else if (count[i] == max && max!=0) {
                for (int j = 0; j < hashTable[i].length()-1; j++) {
                    int a = Integer.parseInt(maxStr.substring(j,j+1));
                    int b = Integer.parseInt(hashTable[i].substring(j,j+1));
                    if (a > b) {
                        max = count[i];
                        maxStr = hashTable[i];
                        break;
                    } else if (a < b) {
                        break;
                    }
                }
                tempNum++;
            }
        }
        if (tempNum != 1) {
            System.out.println(maxStr+" "+max+" "+tempNum);
        }else {
            System.out.println(maxStr+" "+max);
        }

    }
}
/*
13588625832 13505711862
13088625832 13588625832
13505711862 13088625832

*/