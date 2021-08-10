package com.company;

import java.lang.reflect.Array;
import java.util.*;

/*
2021/08/10(화) "졸리지만...하자 ! 힘들 때 웃어야 일류다~"
유형 : 최단 경로를 찾는 문제(개선된 다익스트라 알고리즘)
-> 다익스트라 알고리즘 중 우선순위 큐(Priority Queue)를 사용

기존 다익스트라 알고리즘과 비교했을 때 get_smallest_node()라는 함수를 작성할 필요가 없음
'최단 거리가 가장 짧은 노드'를 선택하는 과정을 다익스트라 최단 경로 함수 안에서 우선순위 큐를 이용하는
방식으로 대체할 수 있기 때문
 */
class Node implements Comparable<Node>{
    private int index;
    private int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    public int getIndex(){
        return this.index;
    }
    public int getDistance(){
        return this.distance;
    }
    
    //거리(비용)가 짧은 게 높은 우선순위를 갖도록 설정
    @Override
    public int compareTo(Node other) {
        if(this.distance < other.distance){
            return -1;
        }
        return 1;
    }
}

public class Main {
    public static final int INF = (int) 1e9;
    //노드의 개수(N), 간선의 개수(M), 시작 노드 번호(start)
    //노드의 개수는 최대 100,000개로 가정
    public static int n, m, start;
    //각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    //최단 거리 테이블 만들기
    public static int[] d = new int[100001];

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //시작 노드로 가기 위한 최단 경로는 0으로 설정하여 큐에 삽입
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while(!pq.isEmpty()){ // 큐가 비어있지 않다면
            //가장 최단 거리가 짧은 노드에 대한 정보를 꺼내기
            Node node = pq.poll();
            int dist = node.getDistance();  //현재 노드까지의 비용
            int now = node.getIndex();      //현재 노드
            //현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if(d[now] < dist) continue;
            //현재 노드와 연결된 다른 인접한 노드들을 확인
            for(int i = 0; i < graph.get(now).size(); i++){
                int cost = d[now] + graph.get(now).get(i).getDistance();
                //현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if(cost < d[graph.get(now).get(i).getIndex()]){
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }


    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt(); m = sc.nextInt(); start = sc.nextInt();

	    //graph 초기화
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<Node>());
        }

        //모든 간선 정보를 입력받기
        for(int j = 0; j < m; j++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            //a번 노드에서 b번 노드로 가는 비용이 c라는 의미
            graph.get(a).add(new Node(b, c));
        }
        //최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);

        //다익스트라 알고리즘 수행
        dijkstra(start);

        //모든 노드로 가기 위한 최단 거리를 출력
        for(int i = 1; i<=n; i++){
            //도달할 수 없는 경우, 무한이라고 출력
            if(d[i] == INF){
                System.out.println("INFINITY");
            }
            else {
                System.out.println(d[i]);
            }
        }
    }
}
