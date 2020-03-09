package com.company.chapter4_1;

import java.util.Scanner;

public class SaveBond {

    private static int numCro;
    private static int jumpDistance;
    private static int[][] croCoordinates;
    private static Crocodile[] crocodiles;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        numCro = scanner.nextInt();
        jumpDistance = scanner.nextInt();
        croCoordinates = new int[numCro][2];
        for (int i = 0; i < numCro; i++) {
            croCoordinates[i][0] = scanner.nextInt();
            croCoordinates[i][1] = scanner.nextInt();
        }
        int[] vertex = buildGragh();
        crocodiles = new Crocodile[numCro];
        for (int i = 0; i < numCro; i++) {
            Crocodile c = new Crocodile(i);
            crocodiles[i] = c;
        }
        isEscape(vertex);

    }

    public static int[] buildGragh(){
        int[] vertex = new int[numCro*(numCro-1)/2];
        for (int i = 0; i < numCro; i++) {
            for (int j = i+1; j < numCro; j++) {
                double a1 = (double) croCoordinates[i][0];
                double a2 = (double) croCoordinates[j][0];
                double b1 = (double) croCoordinates[i][1];
                double b2 = (double) croCoordinates[j][1];
                double distance = Math.sqrt(Math.pow(a1-a2,2)+Math.pow(b1-b2,2));
                if (distance <= jumpDistance) {
                    vertex[j*(j-1)/2+i] = 1;
                }
            }
        }
        return vertex;
    }

    public static void isEscape(int[] vertex){
        Crocodile c = null;
        double semidiameter = 15.0/2;
        for (int i = 0; i < numCro; i++) {
            double a = croCoordinates[i][0];
            double b = croCoordinates[i][1];
            double distance = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
            if (distance<=(semidiameter+jumpDistance)&&!crocodiles[i].isVisited()){
                c = jumpStart(i,vertex);
            }
        }
        if (c!=null) {
            Crocodile[] temps = new Crocodile[numCro];
            int i=0;
            //System.out.println("Yes");  question16
            while (c.getPath() != -1) {
                temps[i] = c;
                c = crocodiles[c.getPath()];
                i++;
            }
            System.out.println(i+2);
            System.out.println(croCoordinates[c.getId()][0]+" "+croCoordinates[c.getId()][1]);
            i--;
            while (true) {
                if (i < 0) {
                    break;
                }
                System.out.println(croCoordinates[temps[i].getId()][0]+" "+croCoordinates[temps[i].getId()][1]);
                i--;
            }
        }else {
            //System.out.println("No");   question16
            System.out.println("0");
        }
    }

    public static Crocodile jumpStart(int id,int[] vertex){
        //BFS
        CroQueue queue = new CroQueue();
        queue.insert(crocodiles[id]);
        while (!queue.isEmpty()) {
            Crocodile c = queue.pop();
            id = c.getId();
            for (int i = 0; i < numCro; i++) {
                for (int j = i+1; j < numCro; j++) {
                    if (j == id && !crocodiles[i].isVisited()  &&
                            vertex[j*(j-1)/2+i]==1) {
                        queue.insert(crocodiles[i]);
                        crocodiles[i].setPath(j);
                    }else if( i == id && !crocodiles[j].isVisited() &&
                            vertex[j*(j-1)/2+i]==1){
                        queue.insert(crocodiles[j]);
                        crocodiles[j].setPath(i);
                    }
                }
            }
            if (c.escape((double)croCoordinates[c.getId()][0],
                    (double) croCoordinates[c.getId()][1],(double) jumpDistance)){
                return c;
            }
        }
        return null;
    }


}
class Crocodile{
    private boolean visited;
    private Crocodile next;
    private int id;
    private int path;

    public Crocodile(int id) {
        this.visited = false;
        next = null;
        this.id = id;
        path = -1;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean escape(double x,double y,double jumpDistance){
        double a = 50-x;
        double b = 50-y;
        double c = x+50;
        double d = y+50;
        if (a <= jumpDistance || b <= jumpDistance || c <= jumpDistance ||
                d <= jumpDistance) {
            return true;
        }else {
            return false;
        }
    }

    public Crocodile getNext() {
        return next;
    }

    public void setNext(Crocodile next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
class CroQueue{
    private Crocodile first;
    private Crocodile last;

    public CroQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(Crocodile c){
        if(!isEmpty()){
            last.setNext(c);
            last = last.getNext();
            c.setVisited(true);
        }else {
            first = c;
            last = first;
            c.setVisited(true);
        }
    }

    public Crocodile pop(){
        if (!isEmpty()) {
            if (first.getNext() != null) {
                Crocodile c = first;
                first = first.getNext();
                return c;
            }else {
                Crocodile c = first;
                first = null;
                last = null;
                return c;
            }
        }else {
            return null;
        }
    }

    public Crocodile getFirst() {
        return first;
    }
}