package com.company.chapter2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReversingList {

    private static String initAddress;
    private static int n;
    private static int k;


    public static void main(String[] args) {
        ReverseNodeList list = read();
        reverse(n,n,list);
        reverse(n,k,list);
        print(list);
    }

    public static ReverseNodeList read(){
        ReverseNodeList list = new ReverseNodeList();
        Scanner scanner = new Scanner(System.in);
        initAddress = scanner.next();
        n = scanner.nextInt();
        k = scanner.nextInt();
        List<ReverseNode> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String address = scanner.next();
            int data = scanner.nextInt();
            String next = scanner.next();
            ReverseNode node = new ReverseNode(address,data,next);
            nodes.add(node);
        }
        String address = initAddress;
        int flag = 0;
        while (nodes.size()>0){
            Iterator<ReverseNode> iterator = nodes.iterator();
            while (iterator.hasNext()){
                ReverseNode node = iterator.next();
                if(address.equals(node.getAddress())){
                    list.insertFirst(node);
                    address = node.getNext();
                    nodes.remove(node);
                    if (node.getNext().equals("-1")){
                        flag = 1;
                    }
                    break;
                }
            }
            if (flag==1){
                break;
            }
        }
        list.initPeek();
        int i = 0;
        while (list.getRear()!=null){
            i++;
            list.peekFoot();
        }
        n = i;
        return list;
    }

    public static void print(ReverseNodeList list){
        list.initPeek();
        while (list.getRear()!=null){
            ReverseNode node = list.peekFoot();
            String address = node.getAddress();
            int data = node.getData();
            String next = node.getNext();
            System.out.println(address + " " + data + " " + next);
        }
    }

    public static void reverse(int n,int k,ReverseNodeList list){
        int times = n/k;
        for (int i = 0; i < times; i++) {
            int t = k/2;
            for (int j = 0; j < t; j++) {
                list.initPeek();
                for (int l = 0; l < i*k+j; l++) {
                    list.peekFoot();
                }
                ReverseNode head = list.getRear();
                list.initPeek();
                for (int l = 0; l < (i+1)*k-j-1; l++) {
                    list.peekFoot();
                }
                ReverseNode foot = list.getRear();
                int tempData = head.getData();
                head.setData(foot.getData());
                foot.setData(tempData);
                String tempString = head.getAddress();
                head.setAddress(foot.getAddress());
                foot.setAddress(tempString);
            }
        }
        list.initPeek();
        while (list.getRear().getFootNode()!=null){
            ReverseNode node = list.peekFoot();
            node.setNext(node.getFootNode().getAddress());
        }
        if (list.getRear().getFootNode() == null) {
            ReverseNode node = list.peekFoot();
            node.setNext("-1");
        }
    }

}

class ReverseNode{
    private ReverseNode headNode;
    private String address;
    private int data;
    private String next;
    private ReverseNode footNode;

    public ReverseNode(String address,int data,String next){
        this.headNode = null;
        this.address = address;
        this.data = data;
        this.next = next;
        this.footNode = null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ReverseNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(ReverseNode headNode) {
        this.headNode = headNode;
    }

    public ReverseNode getFootNode() {
        return footNode;
    }

    public void setFootNode(ReverseNode footNode) {
        this.footNode = footNode;
    }
}

class ReverseNodeList{
    private ReverseNode first;
    private ReverseNode rear;


    public ReverseNodeList(){
        first = null;
        rear = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insertFirst(ReverseNode node){
        if (first !=null){
            node.setFootNode(first);
            first.setHeadNode(node);
            first = node;
        }else {
            node.setFootNode(first);
            first = node;
        }
    }


    public void initPeek(){
        rear = first;
    }


    public ReverseNode peekFoot(){
        ReverseNode node = rear;
        rear = rear.getFootNode();
        return node;
    }

    public ReverseNode peekHead(){
        ReverseNode node = rear;
        rear = rear.getHeadNode();
        return node;
    }

    public ReverseNode getRear() {
        return rear;
    }



}