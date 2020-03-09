package com.company.chapter3_2;

import java.util.Scanner;

public class CompleteSearchTree {


    public static void main(String[] args) {
        int[] input = read();
        TreeNode root = buildTree(input);
        print(root);
    }

    public static int[] read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (input[i]>input[j]){
                    temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    public static TreeNode buildTree(int[] input){
        if (input.length==1){
            return new TreeNode(input[0]);
        }
        if (input.length==0){
            return null;
        }
        int h =(int) (Math.log((double) input.length)/Math.log(2)) + 1;
        int pow = (int) Math.pow(2,h-1);
        int k = input.length - pow + 1;
        int leftNum;
        int rightNum;
        if (k<=pow/2){
            leftNum = (input.length-1+k)/2;
            rightNum = (input.length-1-k)/2;
        }else {
            leftNum = (input.length-1-k)/2 + pow/2;
            rightNum = (input.length-1-k)/2 + k - pow/2;
        }
        int[] left = new int[leftNum];
        int[] right = new int[rightNum];
        TreeNode node = null;
        for (int i = 0; i < input.length; i++) {
            if (i<leftNum){
                left[i] = input[i];
            }else if(i>leftNum){
                right[i-leftNum-1] = input[i];
            }else {
                node = new TreeNode(input[i]);
            }
        }
        TreeNode leftNode = buildTree(left);
        TreeNode rightNode = buildTree(right);
        node.setLeft(leftNode);
        node.setRight(rightNode);
        return node;
    }

    public static void print(TreeNode root){
        TreeLinkedList linkedList = new TreeLinkedList();
        linkedList.insert(root);
        int i=0;
        while (!linkedList.isEmpty()){
            TreeNode temp = linkedList.pop();
            if (i==0){
                System.out.print(temp.getData());
            }else {
                System.out.print(" "+temp.getData());
            }
            i++;
            if (temp.getLeft() != null) {
                linkedList.insert(temp.getLeft());
            }
            if (temp.getRight() != null) {
                linkedList.insert(temp.getRight());
            }
        }
    }

}
class TreeNode{
    private int data;
    private TreeNode left;
    private TreeNode right;
    private TreeNode next;

    public TreeNode(int data){
        this.data = data;
        left = null;
        right = null;
        next = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }
}
class TreeLinkedList{
    private TreeNode first;
    private TreeNode last;

    public TreeLinkedList(){
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(TreeNode node){
        if (!isEmpty()){
            last.setNext(node);
            last = node;
        }else {
            first = node;
            last = node;
        }
    }

    public TreeNode pop(){
        TreeNode temp = first;
        first = first.getNext();
        return temp;
    }
}
