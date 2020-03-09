package com.company.chapter4_1;

import java.util.Scanner;

public class GraghTest {

    private static Vertex[] vertices;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();
        vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            Vertex v = new Vertex(i);
            vertices[i] = v;
        }
        int[] vertex = buildVertex(scanner,n,e);
        printDFS(vertex,n);
        printBFS(vertex,n);
    }

    public static int[] buildVertex(Scanner scanner,int n,int e){
        int[] vertex = new int[(n-1)*n/2];
        for (int i = 0; i < e; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a<b){
                int temp = a;
                a = b;
                b = temp;
            }
            vertex[(a-1)*a/2+b] = 1;
        }
        return vertex;
    }

    public static void printDFS(int[] vertex,int n){
        int i = 0;
        while (i<n){
            if (!vertices[i].isVisited()){
                System.out.print("{ ");
                dfs(i,vertex);
                System.out.println("}");
            }
            i++;
        }
    }

    public static void dfs(int i,int[] vertex){
        vertices[i].setVisited(true);
        System.out.print(i + " ");
        int j = (i+2)*(i+1)/2-1;
        while (j < vertex.length) {
            i++;
            if (vertex[j] == 1&&!vertices[i].isVisited()) {
                dfs(i,vertex);
            }
            j+=i;
        }
    }

    public static void printBFS(int[] vertex,int n){
        for (int i = 0; i < n; i++) {
            vertices[i].setVisited(false);
        }
        VertexQueue queue = new VertexQueue();
        for (int k = 0; k < n; k++) {
            if(!vertices[k].isVisited()){
                queue.insert(vertices[k]);
                System.out.print("{ ");
                while (!queue.isEmpty()) {
                    Vertex v = queue.getFirst();
                    int id = v.getId();
                    int j = (id+2)*(id+1)/2-1;
                    for (int i = id+1; i < n; i++) {
                        if (vertex[j] == 1 &&!vertices[i].isVisited()) {
                            queue.insert(vertices[i]);
                        }
                        j+=i;
                    }
                    queue.pop();
                    System.out.print(v.getId() + " ");
                }
                System.out.println("}");
            }

        }

    }

}
class Vertex{
    private int id;
    private boolean visited;
    private Vertex next;

    public Vertex(int id){
        visited = false;
        next = null;
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class VertexQueue{
    private Vertex first;
    private Vertex last;

    public VertexQueue() {
        first = null;
        last = null;
    }
    public boolean isEmpty(){
        return (first==null);
    }

    public void insert(Vertex vertex){
        vertex.setVisited(true);
        if (!isEmpty()) {
            last.setNext(vertex);
            last = last.getNext();
        }else {
            first = vertex;
            last = first;
        }
    }

    public Vertex pop(){
        if (!isEmpty()){
            if (first.getNext() != null) {
                Vertex temp = first;
                first = first.getNext();
                temp.setVisited(true);
                return temp;
            }else {
                Vertex temp = first;
                first = null;
                last = null;
                temp.setVisited(true);
                return temp;
            }
        }else {
            return null;
        }
    }

    public Vertex getFirst() {
        return first;
    }
}
