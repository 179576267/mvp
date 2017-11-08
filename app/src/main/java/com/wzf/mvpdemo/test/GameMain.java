package com.wzf.mvpdemo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMain {   //创建一副卡牌
    String[] HuaSe = {"黑桃", "红桃", "梅花", "方块"};
    String[] pai = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    List<Object> PokerList = new ArrayList<Object>();
    List<Object> PlayerList = new ArrayList<Object>();

    public GameMain() {
    }

    public void CrePlayer() {//创建玩家

        Scanner input = new Scanner(System.in);
        int score = 0;
        while (score == 0) {
            try {
                System.out.println("\n" + "----------请输入玩家数量：-----------");
                score = input.nextInt();
            } catch (Exception e) {
                System.out.println("\n" + "----------输入不合法：-----------");
//                e.printStackTrace();
                score = 0;
                input.next();
            }
        }

        for (int j = 1; j <= score; j++) {
            System.out.println("请输入第" + j + "个玩家的姓名");
            String name = input2.next();
            if (name == null) {
                System.out.println("您未输入玩家姓名");
                j--;
                continue;
            } else {
                System.out.println("成功添加第" + j + "位玩家");

            }
        }
    }

    public static void main(String[] args) {
        new GameMain().CrePlayer();
    }
}