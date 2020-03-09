package com.company.chapter2;



import java.util.Scanner;

public class Polynomial {

    public static void main(String[] args) {
        LinkedNodeList list1 = read();
        LinkedNodeList list2 = read();
        LinkedNodeList addList = add(list1,list2);
        LinkedNodeList multiList = multi(list1,list2);
        print(multiList);
        System.out.println();
        print(addList);
        System.out.println();
    }

    public static LinkedNodeList read(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedNodeList list = new LinkedNodeList();
        for (int i = 0; i < n; i++) {
            int coef = scanner.nextInt();
            int exp = scanner.nextInt();
            list.insertFirst(coef,exp);
        }
        return list;
    }

    public static void print(LinkedNodeList list){
        if(list.isEmpty()){
            System.out.print("0 0");
        }
        while (!list.isEmpty()){
            LinkedNode temp = list.deleteFirst();
            System.out.print(temp.getCoef() + " " + temp.getExp());
            if (temp.getNext()!=null){
                System.out.print(" ");
            }
        }
    }

    public static LinkedNodeList add(LinkedNodeList list1,LinkedNodeList list2){
        LinkedNodeList addList = new LinkedNodeList();
        list1.initPeek();
        list2.initPeek();
        while (list1.getRear()!=null || list2.getRear()!=null){
            if (list1.getRear()==null){
                LinkedNode temp = list2.peek();
                addList.insertFirst(temp.getCoef(),temp.getExp());
                continue;
            }
            if (list2.getRear()==null){
                LinkedNode temp = list1.peek();
                addList.insertFirst(temp.getCoef(),temp.getExp());
                continue;
            }
            int exp1 = list1.getRearExp();
            int exp2 = list2.getRearExp();
            if (exp1 > exp2){
                LinkedNode temp = list2.peek();
                addList.insertFirst(temp.getCoef(),temp.getExp());
            }else if (exp1 < exp2){
                LinkedNode temp = list1.peek();
                addList.insertFirst(temp.getCoef(),temp.getExp());
            }else {
                LinkedNode temp1 = list1.peek();
                LinkedNode temp2 = list2.peek();
                int addCoef = temp1.getCoef() + temp2.getCoef();
                if (addCoef == 0){
                    continue;
                }
                addList.insertFirst(addCoef,exp1);
            }
        }
        return addList;
    }

    public static LinkedNodeList multi(LinkedNodeList list1,LinkedNodeList list2){
        LinkedNodeList multiList = new LinkedNodeList();
        list1.initPeek();
        list2.initPeek();
        LinkedNode rear = list1.peek();
        while (list2.getRear()!=null){
            LinkedNode temp = list2.peek();
            int multiExp = rear.getExp() + temp.getExp();
            int multiCoef = rear.getCoef() * temp.getCoef();
            if(multiCoef!=0){
                multiList.insertFirst(multiCoef,multiExp);
            }
        }
        while (list1.getRear()!=null) {
            list2.initPeek();
            LinkedNode temp1 = list1.peek();
            while (list2.getRear()!=null){
                LinkedNode temp2 = list2.peek();
                int multiExp = temp1.getExp() + temp2.getExp();
                int multiCoef = temp1.getCoef() * temp2.getCoef();
                if (multiCoef!=0){
                    multiList.insert(multiCoef,multiExp);
                }
            }
        }
        return multiList;
    }
}

class LinkedNode{
    private int coef;
    private int exp;
    private LinkedNode next;

    public LinkedNode(int coef,int exp) {
        this.coef = coef;
        this.exp = exp;
        this.next = null;
    }

    public int getCoef() {
        return coef;
    }


    public int getExp() {
        return exp;
    }


    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}

class LinkedNodeList{
    private LinkedNode first;
    private LinkedNode rear;

    public LinkedNodeList(){
        first = null;
        rear = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insertFirst(int coef,int exp){
        LinkedNode newNode = new LinkedNode(coef,exp);
        newNode.setNext(first);
        first = newNode;
    }

    public LinkedNode deleteFirst(){
        LinkedNode temp = first;
        first = first.getNext();
        return temp;
    }

    public LinkedNode peek(){
        LinkedNode temp = rear;
        rear = rear.getNext();
        return temp;
    }

    public void initPeek(){
        rear = first;
    }


    public int getRearExp(){
        return rear.getExp();
    }

    public void insert(int coef,int exp){
        LinkedNode newNode = new LinkedNode(coef,exp);
        if (first.getExp() < newNode.getExp()) {
            newNode.setNext(first);
            first = newNode;
        }else {
            LinkedNode temp = first;
            while (temp.getNext() != null){
                if (temp.getNext().getExp() < newNode.getExp()){
                    newNode.setNext(temp.getNext());
                    temp.setNext(newNode);
                    break;
                }else if(temp.getNext().getExp() == newNode.getExp()){
                     temp.getNext().setCoef(temp.getNext().getCoef() + newNode.getCoef());
                     break;
                }else {
                    temp = temp.getNext();
                }
            }
            if (temp.getNext() == null){
                temp.setNext(newNode);
            }
        }
    }

    public LinkedNode getRear() {
        return rear;
    }
}