package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //회사 개수 N, 간선개수 M, 거쳐갈 노드 X, 최종 도착 노드 K
    public static int n, m, x, k;
    //기본값을 맥스로 설정
    public static int INF = (int)1e9;
    //2차원 배열(graph)을 만들기
    public static int[][] graph = new int[101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();



        //최단 거리 테이블을 모두 무한으로 초기화
        for(int i = 0; i < 101; i++){
            Arrays.fill(graph[i], INF);
        }

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(i==j) graph[i][j] = 0;
            }
        }

        for(int k = 0; k<m; k++){
            //A에서 B로 가는 이동거리는 1이라고 문제에서 주어졌으므로
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        // x = 최종 도착지점, k = 거쳐야 하는 곳
        x = sc.nextInt(); k = sc.nextInt();

        //모든 노드에 대해 다른 모든 노드까지 최단거리를 구함
        for(int k = 1; k<=n; k++){  //처음 : k=1을 거쳐야 할 경우부터 시작
            for(int a = 1; a<=n; a++){
                for(int b = 1; b<=n; b++){
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        int dis = graph[1][k] + graph[k][x];
        //도달할 수 없을 경우 INF 출력
        if(dis >= INF) System.out.println(-1);
        else {
            System.out.println(dis);
        }
    }
}
