package com.company.chapter3_2;

import java.util.Scanner;

public class AVLTreeRoot {
    public static void main(String[] args) {
        AVLNode node = read();
        System.out.println(node.getData());
    }

    public static AVLNode read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        AVLTree tree = new AVLTree();
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            AVLNode node = new AVLNode(k);
            tree.insert(node,tree.getRoot(),null);
        }
        return tree.getRoot();
    }
}
class AVLNode{
    private int data;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(int data) {
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

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

}
class AVLTree{
    AVLNode root;

    public AVLTree(){
        root = null;
    }


    public void insert(AVLNode node,AVLNode sourse,AVLNode sourceFather){
        if(root!=null){
                if(node.getData()>sourse.getData()){
                    if (sourse.getRight() == null) {
                        sourse.setRight(node);
                    }else {
                        insert(node,sourse.getRight(),sourse);
                        if((getHeight(sourse.getLeft())-getHeight(sourse.getRight()))==-2){
                            if (node.getData()>sourse.getRight().getData()){
                                rrRotation(sourse,sourceFather);
                            }else if (node.getData()<sourse.getRight().getData()){
                                rlRotation(sourse,sourceFather);
                            }
                        }
                    }
                }else if(node.getData()<sourse.getData()){
                    if (sourse.getLeft() == null) {
                        sourse.setLeft(node);
                    }else {
                        insert(node,sourse.getLeft(),sourse);
                        if((getHeight(sourse.getLeft())-getHeight(sourse.getRight())==2)){
                            if (node.getData()>sourse.getLeft().getData()){
                                lrRotation(sourse,sourceFather);
                            }else if (node.getData()<sourse.getLeft().getData()){
                                llRotation(sourse,sourceFather);
                            }
                        }
                    }
                }

        }else {
            root = node;
        }

    }

    public void rrRotation(AVLNode node,AVLNode father){
        if (node.equals(root)){
            root = node.getRight();
        }
        AVLNode temp = node.getRight().getLeft();
        if (father != null) {
            if (father.getLeft().equals(node)){
                father.setLeft(node.getRight());
            }else if(father.getRight().equals(node)){
                father.setRight(node.getRight());
            }
        }
        node.getRight().setLeft(node);
        node.setRight(temp);

    }

    public void rlRotation(AVLNode node,AVLNode father){
        if (node.equals(root)){
            root = node.getRight().getLeft();
        }
        AVLNode tempLeft = node.getRight().getLeft().getLeft();
        AVLNode tempRight = node.getRight().getLeft().getRight();
        if (father != null) {
            if (father.getLeft().equals(node)){
                father.setLeft(node.getRight().getLeft());
            }else if(father.getRight().equals(node)){
                father.setRight(node.getRight().getLeft());
            }
        }
        node.getRight().getLeft().setLeft(node);
        node.getRight().getLeft().setRight(node.getRight());
        node.getRight().setLeft(tempRight);
        node.setRight(tempLeft);
    }

    public void llRotation(AVLNode node,AVLNode father){
        if (node.equals(root)){
            root = node.getLeft();
        }
        AVLNode temp = node.getLeft().getRight();
        if (father != null) {
            if (father.getLeft().equals(node)){
                father.setLeft(node.getLeft());
            }else if(father.getRight().equals(node)){
                father.setRight(node.getLeft());
            }
        }
        node.getLeft().setRight(node);
        node.setLeft(temp);
    }

    public void lrRotation(AVLNode node,AVLNode father){
        if (node.equals(root)){
            root = node.getLeft().getRight();
        }
        AVLNode tempLeft = node.getLeft().getRight().getLeft();
        AVLNode tempRight = node.getLeft().getRight().getRight();
        if (father != null) {
            if (father.getLeft().equals(node)){
                father.setLeft(node.getLeft().getRight());
            }else if(father.getRight().equals(node)){
                father.setRight(node.getLeft().getRight());
            }
        }
        node.getLeft().getRight().setLeft(node.getLeft());
        node.getLeft().getRight().setRight(node);
        node.getLeft().setRight(tempLeft);
        node.setLeft(tempRight);
    }


    public int getHeight(AVLNode node){
        if (node == null) {
            return 0;
        }
        if (node.getLeft()==null&&node.getRight()==null) {
            return 1;
        }
        int leftHeight=0;
        int rightHeight=0;
        if (node.getLeft()!=null){
            leftHeight = getHeight(node.getLeft())+1;
        }
        if (node.getRight()!=null){
            rightHeight = getHeight(node.getRight())+1;
        }
        if (leftHeight>rightHeight){
            return leftHeight;
        }else {
            return rightHeight;
        }

    }

    public AVLNode getRoot(){
        return root;
    }
}