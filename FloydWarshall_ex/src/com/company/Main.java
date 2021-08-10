package com.company;

import java.util.Arrays;
import java.util.Scanner;

/*
2021/08/10(화)
유형 : 플로이드 워셜 알고리즘(Dynamic Programming) - '점화식' 사용
-> 다익스트라 알고리즘과(Greedy)의 차이?
--> 다익스트라 알고리즘은 '한 지점에서 다른 특정 지점까지의 최단 경로를 구하는 경우'
-> 플로이드 워셜 알고리즘 : '모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 경우'
 단계마다 '거쳐 가는 노드'를 기준으로 알고리즘 수행
 하지만 매번 방문하지 않은 노드 중에서 최단 거리를 갖는 노드를 찾을 필요가 없다는 점이 다름
 +)모든 노드에 대하여 다른 모든 노드로 가는 최단 거리 정보를 담아야 하기 때문에
 다익스트라 알고리즘은 1차원 리스트를 이용했지만, 이번에는 2차원 리스트를 이용

 --- 예제문제 ---
 'A에서 B로 가는 최소 비용'과 'A에서 K를 거쳐 B로 가는 비용'을 고려하여 더 작은 값으로 나타내기

 */
public class Main {
    public static final int INF = (int)1e9;
    //노드의 개수(N), 간선의 개수(M)
    //노드의 개수는 최대 500개 가정
    public static int n, m;
    //2차원 배열(graph 표현)을 만들기
    public static int[][] graph = new int[501][501];

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt(); m = sc.nextInt();

	    //최단 거리 테이블을 모두 무한으로 초기화
        for(int i = 0; i<501; i++){
            Arrays.fill(graph[i], INF); // 2차원 배열 초기화하는 법 숙지
        }

        //자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for(int a = 1; a<=n; a++){
            for(int b = 1; b<=n; b++){
                if(a==b) graph[a][b] = 0;
            }
        }

        //각 간선에 대한 정보를 입력받아, 그 값으로 초기화
        for(int i = 0; i<m; i++){
            //A에서 B로 가는 비용은 C라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph[a][b] = c;
        }

        //DP -> '점화식' 세우는 게 중요!!!
        //점화식에 따라 플로이드 워셜 알고리즘 수행
        for(int k = 1; k<=n; k++){  //k번 수를 거칠 때
            for(int a = 1; a<=n; a++){
                for(int b = 1; b<=n; b++){
                    graph[a][b] = Math.min(graph[a][b], graph[a][k]+graph[k][b]);
                }
            }
        }

        //수행 결과 출력
        for(int a = 1; a<=n; a++){
            for(int b = 1; b<=n; b++){
                //도달할 수 없는 경우, 무한이라 출력
                if(graph[a][b] == INF){
                    System.out.print("INFINITY");
                }
                else {
                    System.out.print(graph[a][b] + " ");
                }
            }
            System.out.println();
        }

    }
}
