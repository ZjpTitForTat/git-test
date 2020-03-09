package com.company.chapter3_1;

import java.util.Scanner;

public class LeavesList {

    public static void main(String[] args) {
        TreeNode[] treeNodes = read();
        TreeNode root = findRoot(treeNodes);
        printLeaves(treeNodes,root);
    }

    public static TreeNode[] read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TreeNode[] treeNodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            String leftStr = scanner.next();
            int left = -1;
            if(!leftStr.equals("-")){
                left = Integer.parseInt(leftStr);
            }
            String rightStr = scanner.next();
            int right = -1;
            if(!rightStr.equals("-")){
                right = Integer.parseInt(rightStr);
            }
            TreeNode treeNode = new TreeNode(i,left,right);
            treeNodes[i] = treeNode;
        }
        return treeNodes;
    }

    public static TreeNode findRoot(TreeNode[] treeNodes){
        TreeNode root = treeNodes[0];
        for (int i = 1; i < treeNodes.length; i++) {
            for (int j = 0; j < treeNodes.length; j++) {
                if(treeNodes[j].getLeft()==i||treeNodes[j].getRight()==i){
                    root = treeNodes[j];
                    break;
                }
            }
        }
        return root;
    }

    public static void printLeaves(TreeNode[] treeNodes,TreeNode root){
        Queue queue = new Queue();
        queue.insert(root);
        int n = 0;
        while (!queue.isEmpty()){

            TreeNode temp = queue.getFirst();

            if(temp.getLeft()!=-1){
                queue.insert(treeNodes[temp.getLeft()]);
            }
            if(temp.getRight()!=-1){
                queue.insert(treeNodes[temp.getRight()]);
            }

            TreeNode node = queue.pop();
            if (node.getLeft()==-1&&node.getRight()==-1){
                if(n==0){
                    System.out.print(node.getData());
                }else {
                    System.out.print(" "+node.getData());
                }
                n++;
            }
        }
    }
}
class TreeNode{
    private int data;
    private int left;
    private int right;
    private TreeNode next;

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }

    public TreeNode(int data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
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

class Queue{

    private TreeNode first;
    private TreeNode last;
    private TreeNode rear;

    public Queue(){
        first = null;
        last = null;
        rear = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(TreeNode treeNode){
        if (last!=null){
            last.setNext(treeNode);
            last = treeNode;
        }else {
            first = treeNode;
            last = treeNode;
        }
    }

    public TreeNode pop(){
        TreeNode temp = null;
        if(!isEmpty()){
            temp = first;
            first = first.getNext();
        }
        return temp;
    }

    public void initPeek(){
        rear = first;
    }

    public TreeNode peek(){
        TreeNode temp = rear;
        rear = rear.getNext();
        return temp;
    }

    public TreeNode getLast() {
        return last;
    }

    public TreeNode getFirst() {
        return first;
    }
}