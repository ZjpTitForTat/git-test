package com.company.chapter1;


import java.util.Scanner;

public class SeqSumMax {

    public static void main(String[] args) {


        methodFour();



    }

    private static void methodFour(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        int seqSum = 0;
        int maxSum = 0;
        int start = 0;

        int end = n-1;

        int temp=0;
        for (int i = 0; i <num.length ; i++) {
            num[i] = scanner.nextInt();
            seqSum+=num[i];
            if(seqSum<0){
                seqSum = 0;
                temp = i+1;
            }
            if(seqSum>maxSum){
                maxSum = seqSum;
                start = temp;
                end = i;
            }else if(seqSum==0&&num[i]==0){
                start = i;
                end = i;
            }
        }
        int[] result =new int[3];
        result[0] = maxSum;
        result[1] = start;
        result[2] = end;
        System.out.print(result[0]+" "+num[result[1]]+" "+num[result[2]]);
    }
}
