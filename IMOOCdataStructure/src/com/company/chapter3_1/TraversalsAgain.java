package com.company.chapter3_1;


import java.util.Scanner;

public class TraversalsAgain {

    private static int num = 0;

    public static void main(String[] args) {
        String[] inputStrs = read();
        TreeAgainNode root = getRoot(inputStrs);
        postOrder(root);
    }

    public static String[] read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] inputStrs = new String[n*2];
        for (int i = 0; i < 2*n; i++) {
            String input = scanner.next();
            if (input.equals("Push")){
                input = input + " " + scanner.next();
            }
            inputStrs[i] = input;
        }
        return inputStrs;
    }

    public static TreeAgainNode getRoot(String[] inputStrs){
        LinkedTreeNode nodes = new LinkedTreeNode();
        String[][] tempStrs = new String[inputStrs.length][2];
        TreeAgainNode node = null;
        TreeAgainNode root = null;
        for (int i = 0; i < inputStrs.length; i++) {
            tempStrs[i] = inputStrs[i].split(" ");
            if (tempStrs[i][0].equals("Push")){
                int data = Integer.parseInt(tempStrs[i][1]);
                TreeAgainNode temp = new TreeAgainNode(data);
                if (i!=0){
                    if (tempStrs[i-1][0].equals("Push")){
                        TreeAgainNode first = nodes.getFirst();
                        first.setLeft(temp);
                    }else if (tempStrs[i-1][0].equals("Pop")){
                        node.setRight(temp);
                    }
                } else if(i==0){
                    root = temp;
                }
                nodes.push(temp);
            }else if(tempStrs[i][0].equals("Pop")){
                node = nodes.pop();
            }
        }
        return root;
    }

    public static void postOrder(TreeAgainNode node){

        if (node.getLeft()!=null){
            postOrder(node.getLeft());
        }
        if (node.getRight()!=null){
            postOrder(node.getRight());
        }
        num++;
        if(num==1){
            System.out.print(node.getData());
        }else {
            System.out.print(" "+node.getData());
        }
    }

}
class TreeAgainNode{

    private int data;
    private TreeAgainNode left;
    private TreeAgainNode right;

    public TreeAgainNode getNext() {
        return next;
    }

    public void setNext(TreeAgainNode next) {
        this.next = next;
    }

    private TreeAgainNode next;

    public TreeAgainNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeAgainNode getLeft() {
        return left;
    }

    public void setLeft(TreeAgainNode left) {
        this.left = left;
    }

    public TreeAgainNode getRight() {
        return right;
    }

    public void setRight(TreeAgainNode right) {
        this.right = right;
    }
}
class LinkedTreeNode{

    private TreeAgainNode first;

    public LinkedTreeNode() {
        this.first = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void push(TreeAgainNode node){
        node.setNext(first);
        first = node;
    }

    public TreeAgainNode pop(){
        TreeAgainNode temp = first;
        first = first.getNext();
        return temp;
    }

    public TreeAgainNode getFirst(){
        return first;
    }
}