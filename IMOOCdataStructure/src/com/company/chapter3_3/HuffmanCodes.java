package com.company.chapter3_3;



import java.util.Scanner;

public class HuffmanCodes {

    private static Node[] frequency;
    private static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        frequency = new Node[N+1];
        frequency[0] = new Node(-1);
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            String s = scanner.next();
            int a = scanner.nextInt();
            frequency[i] = new Node(a);
            sum+=a;
        }
        createTree(sum);
        int k = scanner.nextInt();
        String[] codes = new String[N];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < N; j++) {
                scanner.next();
                codes[j] = scanner.next();
            }
        }
    }


    public static Node createTree(int sum){
        Node newNode = new Node(-1);
        while (newNode.getData()!=sum){
            Node node1 = heapDelete();
            Node node2 = heapDelete();
            newNode = new Node(node1.getData()+node2.getData());
            heapInsert(newNode);
            newNode.setLeft(node1);
            newNode.setRight(node2);
        }
        return newNode;
    }

    public static void heapInsert(Node temp){
        int i = frequency.length-1;
        while (i > 0) {
            if(frequency[i-1].getData()!=-1&&frequency[i].getData()==-1){
                frequency[i].setData(temp.getData());
            }
            if (frequency[i].getData()!=-1){
                if (frequency[i / 2].getData() > temp.getData()) {
                    frequency[i].setData(frequency [i/2].getData());
                    i/=2;
                }else {
                    frequency[i].setData(temp.getData());
                    break;
                }
            }else {
                i--;
            }
        }
    }

    public static Node heapDelete(){
        Node minNode = new Node(frequency[1].getData());
        int i = frequency.length-1;
        while (frequency[i].getData() == -1) {
            i--;
        }
        frequency[1].setData(frequency[i].getData());
        frequency[i].setData(-1);
        int tempData = frequency[1].getData();
        int j = 1;
        while (j*2 < i) {
            Node min = frequency[j*2];
            int num = j*2;
            if(frequency[j*2].getData()>frequency[j*2+1].getData()){
                min = frequency[j*2+1];
                num = j*2+1;
            }
            if(tempData>min.getData()){
                frequency[j].setData(min.getData());
                j = num;
            }else {
                break;
            }
        }
        frequency[j].setData(tempData);
        return minNode;
    }





}
class Node{
    private int data;
    private Node left;
    private Node right;

    public Node(int  data) {
        this.data = data;
        left = null;
        right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
class NodeTree{
    private Node root;

    public NodeTree(){
        root = null;
    }

}
