package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int N, M, total, result, start, end;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        start = 0;
        //끝점은 가장 긴 떡의 길이, 문제에서 떡의 개별높이(길이)는 20억까지 주어진다
        end = (int) 1e9; // 1,000,000,000 = 10억
        result = 0;
        //이진탐색 시작(반복적)
        //시작위치(start)가 끝위치(end)보다 같거나 작으면 계속 반복
        while (start <= end) {
            total = 0;
            //중간위치 선정
            int mid = (start + end) / 2;
            for (int i = 0; i < N; i++) {
                //떡의 길이가 mid 값 보다 커야 잘랐을 때 잘린 떡이 남으므로 다음과 같은 조건
                if (arr[i] > mid) total += arr[i] - mid;
            }
            //만약 잘린떡의 총합길이가 고객이 요청한 길이(M) 보다 작으면 끝위치를 줄인다(=더 작은 절단기로, 요청한 M보다 길도록 더 많이 자름 = 왼쪽 부분 탐색)
            if (total < M) end = mid - 1;
            //적어도 고객이 요청한 길이(M) 만큼의 떡을 얻은 경우엔 중간값을 result에 넣고
            //최댓값을 구해야 하므로 while 조건에서 벗어나지 않는 범위안에서 시작점을 늘린다(더 긴 절단기로, 요청한 M보다 같거나 길도록 자름)
            else {
                result = mid;
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}
