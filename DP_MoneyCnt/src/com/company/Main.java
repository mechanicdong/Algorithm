package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static long[] d = new long[1001];
    public static int n,m;
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt(); m = sc.nextInt();
	    int[] arr = new int[n];
	    for(int i = 0; i<n; i++){
	        arr[i] = sc.nextInt();
        }
		//각 인덱스는 최대금액보다 큰(10,000보다) 10,001로 설정
        Arrays.fill(d,10001);
	    //0원인 경우 화폐를 하나도 사용하지 않았을 때도 만들 수 있으므로 0을 설정
	    d[0] = 0;
	    //화폐 개수만큼 반복
	    for(int i = 0; i<n; i++){
	    	//arr[] = {2,3} 이라면 2원부터 시작
	        for(int j = arr[i]; j<=m; j++){
	        	//d[2-2=0]은 10,001이 아니므로, 조건 만족
	            if(d[j - arr[i]] != 10001){	//초기 d[0]의 중요성..
	                d[j] = Math.min(d[j], d[j - arr[i]] + 1 );
                }
            }
        }
	    if(d[m] == 10001) System.out.println(-1);
	    else System.out.println(d[m]);
    }
}
