package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str1 = scanner.next();
        String str2 = scanner.next();
        String[] strs1 = str1.split("");
        String[] strs2 = str2.split("");
        judge(strs1,strs2,n);
    }


    public static void judge(String[] strs1,String[] strs2,int n){
        int k = 0;
        int[] nums = new int[n];
        int num = 0;

        Stack stack = new Stack(n);
        int i = 0;
        while (i<strs2.length){
            while (!strs1[k].equals(strs2[i])) {
                stack.push(strs1[k]);
                num++;
                nums[k] = num;
                k++;
            }
            stack.push(strs1[k]);
            num++;
            nums[k] = num;
            k++;
            if (!stack.isEmpty() ) {
                while (!stack.isEmpty() && stack.peek().equals(strs2[i])) {
                    String s = stack.pop();
                    num = findStr(nums,strs1,s);
                    i++;
                }
            }
        }

        System.out.println(findMax(nums));

    }

    public static int findMax(int[] nums){
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max<nums[i]){
                max = nums[i];
            }
        }
        return max;
    }

    public static int findStr(int[] nums,String[] strs,String s){
        int num = -1;
        for (int i = 0; i < strs.length; i++) {
            if (s.equals(strs[i])) {
                num = nums[i];
            }
        }
        return num;

    }
}
class Stack{

    private String[] strs;
    private int size;

    public Stack(int n){
        strs = new String[n];
        size = -1;
    }

    public void push(String s){
        strs[++size] = s;
    }

    public String pop(){
        String s = strs[size];
        size--;
        return s;
    }

    public String peek(){
        return strs[size];
    }

    public boolean isEmpty(){
        return size<0;
    }
}