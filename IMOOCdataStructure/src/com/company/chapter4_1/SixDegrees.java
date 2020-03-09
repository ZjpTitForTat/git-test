package com.company.chapter4_1;

import java.util.Scanner;

public class SixDegrees {

    private static LinkedNode[] linkedNodes;
    private static int N;
    private static int M;
    private static final int MAXLEVEL = 6;

    public static void main(String[] args) {
        read();
        double[] rates = cal();
        print(rates);

    }

    public static void read(){
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        linkedNodes = new LinkedNode[N];
        for (int i = 0; i < N; i++) {
            LinkedNode nodes = new LinkedNode();
            Node node = new Node(i);
            nodes.insert(node);
            linkedNodes[i] = nodes;
        }
        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt()-1;
            int b = scanner.nextInt()-1;
            Node nodeA = new Node(a);
            Node nodeB = new Node(b);
            linkedNodes[a].insert(nodeB);
            linkedNodes[b].insert(nodeA);
        }
    }

    public static double[] cal(){
        double[] rates = new double[N];
        for (int i = 0; i < N; i++) {
            Node source = linkedNodes[i].getFirst();
            int count = 1;
            int level = 0;
            Node last = source;
            Queue queue = new Queue();
            queue.insert(source);
            while (!queue.isEmpty()){
                Node popNode = queue.pop();
                Node temp = popNode;
                while ( temp != null &&
                        temp.getLinkedNext() != null) {
                    if(!linkedNodes[temp.getLinkedNext().getId()].getFirst().isVisited()){
                        queue.insert(linkedNodes[temp.getLinkedNext().getId()].getFirst());
                        temp = temp.getLinkedNext();
                        count++;
                    }else {
                        temp = temp.getLinkedNext();
                    }
                }
                if (last.equals(popNode)) {
                    level++;
                    last = queue.getLast();
                }
                if (level>=MAXLEVEL){

                    break;
                }
            }
            double rate = (double) count/N*100;
            rates[i] = rate;
            reset();
        }
        return rates;
    }

    public static void print(double[] rates){
        for (int i = 0; i < N; i++) {
            System.out.printf("%d: %.2f%%\n",i+1,rates[i]);
        }
    }

    public static void reset(){
        for (int i = 0; i < N; i++) {
            linkedNodes[i].getFirst().setVisited(false);
            linkedNodes[i].getFirst().setNext(null);
        }
    }


}
class Node{
    private int id;
    private Node next;
    private boolean visited;
    private Node linkedNext;

    public Node(int id) {
        this.id = id;
        this.next = null;
        visited = false;
        linkedNext = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getLinkedNext() {
        return linkedNext;
    }

    public void setLinkedNext(Node linkedNext) {
        this.linkedNext = linkedNext;
    }
}
class LinkedNode{
    private Node first;
    private Node last;

    public LinkedNode() {
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(Node node){
        if (!isEmpty()) {
            last.setLinkedNext(node);
            last = last.getLinkedNext();
        }else {
            first = node;
            last = first;
        }
    }

    public Node getFirst() {
        return first;
    }
}
class Queue{
    private Node first;
    private Node last;

    public Queue(){
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(Node node){
        if(!isEmpty()){
            last.setNext(node);
            last = last.getNext();
            node.setVisited(true);
        }else {
            first = node;
            last = first;
            node.setVisited(true);
        }
    }

    public Node pop(){
        if (!isEmpty()){
            if (first.getNext() != null) {
                Node temp = first;
                first = first.getNext();
                return temp;
            }else {
                Node temp = first;
                first = null;
                last = null;
                return temp;
            }
        }else {
            return null;
        }
    }

    public Node getLast() {
        return last;
    }
}