package com.company.chapter3_3;

import java.util.Scanner;

public class HeapPath {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Heap heap = new Heap(n);
        for (int i = 1; i <= n; i++) {
            int k = scanner.nextInt();
            heap.insert(i,k);
        }
        for (int i = 0; i < m; i++) {
            int k = scanner.nextInt();
            heap.output(k);
        }
    }



}
class Heap{
    private int[] heap;

    public Heap(int n){
        heap = new int[n+1];
    }

    public void insert(int i,int k){
        heap[i] = k;
        int parent = i/2;
        int child = i;
        int temp = -1;
        while (parent>0){
            if (heap[child] < heap[parent]) {
                temp = heap[parent];
                heap [parent] = heap[child];
                heap[child] = temp;
                child = parent;
                parent/=2;
            }else {
                break;
            }
        }
    }





    public void output(int i){
        int child = i;
        int j = 0;
        while (child>0){
            if (j==0){
                System.out.print(heap[child]);
                j++;
            }else {
                System.out.print(" " + heap[child]);
            }
            child/=2;
        }
        System.out.println();
    }
}
