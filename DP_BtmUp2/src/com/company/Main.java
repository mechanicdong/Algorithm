package com.company;

import java.util.Scanner;

public class Main {
    public static long[] d = new long[10001];
    public static int num;
    public static int[] soko = new int[4];
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    num = sc.nextInt();
	    for(int i = 0; i<soko.length; i++){
	        soko[i] = sc.nextInt();
        }

	    d[0] = soko[0];
	    //num=2 일때도 최댓값을 출력하기 위해 max로 비교
	    d[1] = Math.max(soko[0], soko[1]);
        for(int i = 2; i<num; i++){
            d[i] = Math.max(d[i-1], d[i-2]+soko[i]);
        }
        System.out.println(d[num-1]);
    }
}
