package com.company.chapter1;

import java.util.Random;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] num = rand(n);
            int ii=0;
            while (ii<n){
                for (int j = 0; j < 50; j++) {
                    System.out.print(num[ii]+" ");
                    ii++;
                }
                System.out.println();
            }
//
//            int maxTwo = methodTwo(num,n);
//            int maxOne = methodOne(num,n);
            int maxThree = methodThree(num,0,n-1);
            int maxFour = methodFour(num);
            System.out.println(maxThree+" "+maxFour);
        }

        private static int methodTwo(int[] num,int n){

//            int[] num = new int[n];
//            for(int i=0;i<n;i++){
//                num[i]=scanner.nextInt();
//            }
            int maxsum=0;
            for (int i = 0; i < n; i++) {
                int seqsum=0;
                for (int j = i; j < n; j++) {
                    seqsum+=num[j];
                    if(seqsum>maxsum){
                        maxsum = seqsum;
                    }
                }
            }
            return maxsum;
        }


    private static int methodOne(int[] num,int n){
//        int[] num = new int[n];
//        for(int i=0;i<n;i++){
//            num[i]=scanner.nextInt();
//        }
        int maxsum = 0;
        for(int i = 0; i < n; i++){
            for (int j = i; j <n; j++) {
                int seqsum=0;
                for (int k = i; k <= j; k++) {
                    seqsum+=num[k];
                }
                if(seqsum>maxsum){
                    maxsum = seqsum;
                }
            }
        }
        return maxsum;
    }

    private static int methodThree(int[] num,int left, int right){
        int middle = (left+right)/2;
        int leftMax = 0;
        int rightMax = 0;
        if(right-left>1){
            leftMax = methodThree(num,left,middle);
            rightMax = methodThree(num,middle+1,right);
        }else {
            leftMax = num[left];
            rightMax = num[right];
        }
        int leftSeqSum = 0;
        int leftSeqMax = 0;
        for (int i = middle; i >= left; i--) {
            leftSeqSum+=num[i];
            if (leftSeqSum>leftSeqMax){
                leftSeqMax = leftSeqSum;
            }
        }
        int rightSeqSum = 0;
        int rightSeqMax = 0;
        for (int i = middle+1; i <=right; i++) {
            rightSeqSum+=num[i];
            if (rightSeqSum>rightSeqMax){
                rightSeqMax = rightSeqSum;
            }
        }
        int seqMax = leftSeqMax + rightSeqMax;
        int maxsum = leftMax;
        if(rightMax>leftMax){
            maxsum = rightMax;
        }
        if(seqMax>leftMax){
            maxsum = seqMax;
        }
        return maxsum;
    }

    private static int methodFour(int[] num){
        int seqSum = 0;
        int maxSum = 0;
        for (int i = 0; i <num.length ; i++) {
            seqSum+=num[i];
            if(seqSum<0){
                seqSum = 0;
            }
            if(seqSum>maxSum){
                maxSum = seqSum;
            }
        }

        return maxSum;
    }

    private static int[] rand(int n){
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            int randInt = (new Random().nextInt(20))*((int) Math.pow(-1,new Random().nextInt()));
            num[i] = randInt;
        }
        return num;
    }

}
