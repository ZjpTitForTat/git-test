package com.company.chapter2;

import java.util.Scanner;

public class PopSequence {

    private static int M; //max of stack
    private static int N; //max of num
    private static int K; //num of group

    public static void main(String[] args) {
        int[][] sequences = read();
        String[] ss = new String[K];
        for (int i = 0; i < K; i++) {
            boolean flag = judgePop(sequences[i]);
            if (flag){
                ss[i] = "YES";
            }else {
                ss[i] = "NO";
            }
        }
        print(ss);
    }

    public static int[][] read(){
        Scanner scanner = new Scanner(System.in);
        M = scanner.nextInt();
        N = scanner.nextInt();
        K = scanner.nextInt();
        int[][] sequences = new int[K][N];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                sequences[i][j]=scanner.nextInt();
            }
        }
        return sequences;
    }

    public static void print(String[] ss){
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
        }
    }

    public static boolean judgePop(int[] sequence){
        PopList list = new PopList(M);
        int k = sequence[0];
        for (int j = 1; j <= k; j++) {
            PopNode node = new PopNode(j);
            boolean isPushed = list.push(node);
            if(!isPushed){
                return false;
            }
        }
        int max = list.pop().getData();
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i]>max){
                for (int j = 1; j <= (sequence[i]-max); j++) {
                    PopNode node = new PopNode(max+j);
                    boolean isPushed = list.push(node);
                    if (!isPushed){
                        return false;
                    }
                }
                max = list.pop().getData();
                if (max!=sequence[i]){
                    return false;
                }
            }else {
                int firstNum = list.pop().getData();
                if(firstNum!=sequence[i]){
                    return false;
                }
            }
        }

        return true;

    }


}

class PopNode{
    private int data;
    private PopNode next;

    public PopNode(int data) {
        this.data = data;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public PopNode getNext() {
        return next;
    }

    public void setNext(PopNode next) {
        this.next = next;
    }
}

class PopList{

    private PopNode first;
    private int num;
    private int maxNum;

    public PopList(int maxNum) {
        first = null;
        this.maxNum = maxNum;
        num = 0;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public boolean isFull(){
        return (num==maxNum);
    }


    public boolean push(PopNode node){
        if (!isFull()){
            node.setNext(first);
            first = node;
            num++;
            return true;
        }else {
            return false;
        }
    }

    public PopNode peek(){
        PopNode temp = first;
        return temp;
    }

    public PopNode pop(){
        PopNode temp = first;
        first = first.getNext();
        num--;
        return temp;
    }
}