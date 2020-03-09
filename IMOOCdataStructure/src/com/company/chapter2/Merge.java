package com.company.chapter2;



import java.util.Scanner;

public class Merge {


    public static void main(String[] args) {
        NodeList list1 = read();
        NodeList list2 = read();
        NodeList list = mergeLists(list1,list2);
        print(list);
        print(list1);
        print(list2);

    }



    private static NodeList read(){
        Scanner scanner = new Scanner(System.in);
        NodeList list = new NodeList();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            list.insertFirst(k);
        }
        return list;
    }

    private static void print(NodeList list){
        if (list.isEmpty()) {
            System.out.print("NULL");
        }
        while (!list.isEmpty()){
            Node temp = list.deleteFirst();
            System.out.print(temp.getData()+" ");
        }
        System.out.println();
    }

    private static NodeList mergeLists(NodeList list1,NodeList list2){
        NodeList mergedList = new NodeList();
        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (list1.isEmpty()){
                Node temp = list2.deleteFirst();
                mergedList.insertFirst(temp.getData());
                continue;
            } else if (list2.isEmpty()){
                Node temp = list1.deleteFirst();
                mergedList.insertFirst(temp.getData());
                continue;
            }
            int i = list1.getFirstData();
            int j = list2.getFirstData();
            if (i > j) {
                Node temp = list1.deleteFirst();
                mergedList.insertFirst(temp.getData());
            } else {
                Node temp = list2.deleteFirst();
                mergedList.insertFirst(temp.getData());
            }
        }
        return mergedList;
    }
}

class Node{
    private int data;
    private Node next;

    public Node(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}

class NodeList{
    private Node first;

    public NodeList(){
        first = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insertFirst(int data){
        Node newNode = new Node(data);
        newNode.setNext(first);
        first = newNode;
    }

    public Node deleteFirst(){
        Node temp = first;
        first = first.getNext();
        return temp;
    }

    public int getFirstData(){
        return first.getData();
    }
}

