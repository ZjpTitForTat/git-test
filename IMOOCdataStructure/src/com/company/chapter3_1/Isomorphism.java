package com.company.chapter3_1;

import java.util.Scanner;

public class Isomorphism {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node[] N1 = read(scanner);
        Node[] N2 = read(scanner);
        boolean flag = isomorphism(N1,N2);
        if(flag){
            System.out.printf("Yes");
        }else {
            System.out.printf("No");
        }
    }

    public static Node[] read(Scanner scanner){
        int n = scanner.nextInt();
        Node[] N = new Node[n];
        for (int i = 0; i < n; i++) {
            String data = scanner.next();
            String leftStr = scanner.next();
            String rightStr = scanner.next();
            int left = -1;
            if (!leftStr.equals("-")){
                left = Integer.parseInt(leftStr);
            }
            int right = -1;
            if (!rightStr.equals("-")){
                right = Integer.parseInt(rightStr);
            }
            Node node = new Node(data,left,right);
            N[i] = node;
        }
        return N;
    }

    public static boolean isomorphism(Node[] N1,Node[] N2){
        int[] check1 = new int[N1.length];
        int[] check2 = new int[N2.length];
        boolean flag = true;
        for (int i = 0; i < N1.length; i++) {
            check1[i] = 0;
            if(N1[i].getLeft()!=-1){
                check1[i] += 1;
            }
            if(N1[i].getRight()!=-1){
                check1[i] += 1;
            }
        }
        for (int i = 0; i < N2.length; i++) {
            check2[i] = 0;
            if(N2[i].getLeft()!=-1){
                check2[i] += 1;
            }
            if(N2[i].getRight()!=-1){
                check2[i] += 1;
            }
        }
        for (int i = 0; i < N1.length; i++) {
            for (int j = 0; j < N2.length; j++) {
                if (N1[i].getData().equals(N2[j].getData())){
                    if (check1[i]==check2[j]){
                        if(check1[i]==1){
                            if(N1[i].getLeft()==-1&&((N2[j].getLeft()==-1&&
                                    !N1[N1[i].getRight()].getData().equals(N2[N2[j].getRight()].getData()))||N2[j].getRight()==-1&&
                                    !N1[N1[i].getRight()].getData().equals(N2[N2[j].getLeft()].getData()))) {
                                flag = false;
                            }else if(N1[i].getRight()==-1&&((N2[j].getLeft()==-1&&
                                    !N1[N1[i].getLeft()].getData().equals(N2[N2[j].getRight()].getData()))||N2[j].getRight()==-1&&
                                    !N1[N1[i].getLeft()].getData().equals(N2[N2[j].getLeft()].getData()))){
                                flag = false;
                            }
                        }else if(check1[i]==2&&
                                !(N1[N1[i].getLeft()].getData().equals(N2[N2[j].getLeft()].getData())&&N1[N1[i].getRight()].getData().equals(N2[N2[j].getRight()].getData()))&&
                                !(N1[N1[i].getLeft()].getData().equals(N2[N2[j].getRight()].getData())&&N1[N1[i].getRight()].getData().equals(N2[N2[j].getLeft()].getData()))){
                            flag = false;
                        }
                    }else {
                        flag = false;
                    }
                }else if (N1.length==1&&N2.length==1&&!N1[i].getData().equals(N2[i].getData())){
                    flag = false;
                }
            }
        }
        return flag;
    }
}
class Node{
    private String data;
    private int left;
    private int right;

    public Node(String data,int left,int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}