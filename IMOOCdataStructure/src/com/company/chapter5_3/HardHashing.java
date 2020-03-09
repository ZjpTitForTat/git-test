package com.company.chapter5_3;

import java.util.Scanner;

public class HardHashing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] hashTable = new int[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = scanner.nextInt();
        }
        Heap hashing = new Heap();

        int[] modTemp = new int[size];
        boolean[] flags = new boolean[size];
        boolean[] outFlags = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != -1) {
                modTemp[i] = hashTable[i] % size;
                flags[i] = false;
                outFlags[i] = false;
            }else{
                modTemp[i] = -1;
                flags[i] = true;
                outFlags[i] = true;
            }

        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k <= i; k++) {
                    if (!flags[j] && (modTemp[j]+k == j || modTemp[j]+k-size == j)) {
                        int l = j-1;
                        for (; l >= modTemp[j] || l<= modTemp[j]-size; l--) {
                            if (!outFlags[l]){
                                break;
                            }
                            if (l - 1 < 0) {
                                l+=size;
                            }
                        }
                        if (l == modTemp[j] - 1) {
                            hashing.insert(hashTable[j]);
                            flags[j] = true;
                        }
                    }
                }
            }
            if (hashing.getSize() > 0) {
                if (i == 0) {
                    int temp = hashing.pop();
                    System.out.print(temp);
                    for (int j = 0; j < size; j++) {
                        if (temp==hashTable[j]){
                            outFlags[j] = true;
                        }
                    }
                }else {
                    int temp = hashing.pop();
                    System.out.print(" "+temp);
                    for (int j = 0; j < size; j++) {
                        if (temp==hashTable[j]){
                            outFlags[j] = true;
                        }
                    }
                }
            }
        }

    }



}
class Heap{
    private int[] nums;
    private int size;
    private static int maxSize = 1000;

    public Heap(){
        nums = new int[maxSize];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1;
        }
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void insert(int num){
        size+=1;
        int temp = size;
        while (num<nums[temp/2]){
            nums[temp] = nums[temp/2];
            temp /=2;
        }
        nums[temp] = num;
    }

    public int pop(){
        int min = nums[1];
        int last = nums[size];
        nums[1] = last;
        size-=1;
        int temp = 1;
        while (temp*2<=size) {
            int minTemp = nums[temp*2];
            int pt = temp*2;
            if(minTemp>nums[temp*2+1] && temp*2+1<=size){
                minTemp = nums[temp*2+1];
                pt = temp*2+1;
            }
            if (nums[temp] > minTemp) {
                nums[temp] = minTemp;
            }else {
                break;
            }
            temp = pt;
        }
        nums[temp] = last;
        return min;
    }
}
