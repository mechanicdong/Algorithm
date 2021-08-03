package com.company;

import java.util.Scanner;

public class Main {
    public static int num;
    public static long[] d = new long[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        //DP, BottomUp 방식
        for(int i = 2; i<=num; i++){
            //+1을 하는 이유는 함수의 호출 횟수를 구해야 하기 때문
            d[i] = d[i-1] + 1;
            if(i % 2 == 0) d[i] = Math.min(d[i], d[i/2]+1);
            //만약 num=3이고 i=3이면 d[3] = Math.min(d[2]+1, d[1]+1)이 된다
            //d[2]는 다시 함수를 호출하여 횟수가 늘어나기 때문에 뒤 값인 d[1]+1번만큼으로 최소호출을 구할 수 있게 됨
            if(i % 3 == 0) d[i] = Math.min(d[i], d[i/3]+1);
            if(i % 5 == 0) d[i] = Math.min(d[i], d[i/5]+1);

            System.out.println(d[num]);
        }
    }
}
