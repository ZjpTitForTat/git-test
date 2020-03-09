package com.company.chapter5_2;

import java.util.Scanner;

public class PTAJudge {

    public static void main(String[] args) {
        User[] users = read();
        int[] userIds = sort(users);
        print(users,userIds);
    }

    public static int[] sort(User[] users){
        int[] userIds = new int[users.length];
        for (int i = 0; i < userIds.length; i++) {
            userIds[i] = users[i].getId();
        }
        for (int i = 1; i < users.length; i++) {
            int temp = userIds[i];
            int j = i-1;
            for (; j >=0 ; j--) {
                if (users[temp].getTotalScore() < users[userIds[j]].getTotalScore() ||
                        users[temp].getTotalScore() == users[userIds[j]].getTotalScore() && users[temp].getFullNum() < users[userIds[j]].getFullNum() ||
                        users[temp].getTotalScore() == users[userIds[j]].getTotalScore() && users[temp].getFullNum() == users[userIds[j]].getFullNum() && users[temp].getId() > users[userIds[j]].getId()) {
                    userIds[j+1] = userIds[j];
                }else {
                    break;
                }
            }
            userIds[j+1] = temp;
        }
        return userIds;
    }



    public static User[] read(){
        Scanner scanner = new Scanner(System.in);
        int numUser = scanner.nextInt();
        int numPro = scanner.nextInt();
        int numInput = scanner.nextInt();
        int[] fullScores = new int[numPro];
        for (int i = 0; i < numPro; i++) {
            fullScores[i] = scanner.nextInt();
        }
        User[] users = new User[numUser];
        for (int i = 0; i < numUser; i++) {
            User user = new User(i,numPro);
            users[i] = user;
        }
        for (int i = 0; i < numInput; i++) {
            int id = scanner.nextInt();
            int proId = scanner.nextInt();
            int score = scanner.nextInt();
            users[id-1].setScore(proId-1,score,fullScores);

        }
        return users;
    }
    
    public static void print(User[] users,int[] userIds){
        int num = 1;
        for (int i = users.length-1; i >=0; i--) {
            if (i!=users.length-1 && users[userIds[i]].getTotalScore() != users[userIds[i+1]].getTotalScore()){
                num++;
            }
            if (users[userIds[i]].isActive()){
                System.out.print(num + " ");
                System.out.printf("%05d",users[userIds[i]].getId()+1);
                System.out.print(" "+users[userIds[i]].getTotalScore());
                for (int j = 0; j < users[userIds[i]].getScores().length; j++) {
                    if (users[userIds[i]].getScores()[j] == -2) {
                        System.out.print(" -");
                    }else if(users[userIds[i]].getScores()[j] == -1){
                        System.out.print(" 0");
                    }else {
                        System.out.print(" "+users[userIds[i]].getScores()[j]);
                    }
                }
                System.out.println();
            }
        }
    }
}
class User{
    private int id;
    private int totalScore = 0;
    private int[] scores;
    private int fullNum = 0;
    private boolean active = false;

    public User(int id, int num) {
        this.id = id;
        scores = new int[num];
        for (int i = 0; i < num; i++) {
            scores[i] = -2;
        }
    }

    public void setScore(int proId,int score,int[] fullScores){
        if(scores[proId]<score){
            if(score != -1 && scores[proId] >=0 ){
                totalScore -= scores[proId];
                totalScore += score;
                scores[proId] = score;
            } else if (score == -1) {
                scores[proId] = score;
            } else {
                totalScore += score;
                scores[proId] = score;
            }
        }
        if(score==fullScores[proId]){
            fullNum++;
        }
        if(score!=-1){
            active = true;
        }
    }

    public int getId() {
        return id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int[] getScores() {
        return scores;
    }

    public int getFullNum() {
        return fullNum;
    }

    public boolean isActive() {
        return active;
    }
}
