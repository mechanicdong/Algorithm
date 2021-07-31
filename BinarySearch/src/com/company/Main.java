package com.company;

import java.util.Arrays;
import java.util.Scanner;
//이진탐색(재귀함수 사용) 
//이진탐색은 배열 내부 데이터가 정렬되어 있어야만 사용할 수 있는 알고리즘
//탐색 범위를 '절반씩' 좁혀가며 데이터를 탐색하는 특징
public class Main {
    public static int N, M;
    public static int BinarySearch(int[] arr, int target, int start, int end) {
        Arrays.sort(arr);
        int mid = (start + end) / 2;
        if(start > end) return -1;
        if(arr[mid] == target) return 1;
        else if(arr[mid] > target) return BinarySearch(arr, target, start, mid-1);
        else return BinarySearch(arr, target, mid+1, end);
    }
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
    //있는 제품
	N = sc.nextInt();
    int[] arr = new int[N];
	for(int i = 0; i< N; i++){
	    arr[i] = sc.nextInt();
    }
    //고객이 찾는 제품
    M = sc.nextInt();
    int[] arr2 = new int[M];
	for(int j = 0; j<M; j++){
	    arr2[j] = sc.nextInt();
    }
	for(int k = 0; k<M; k++){
	    int target = arr2[k];
        int result = BinarySearch(arr, target, 0, N-1);
        if(result == 1) System.out.println("yes");
        else System.out.println("No");
    }
    }
}
