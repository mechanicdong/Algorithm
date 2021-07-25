package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class Main {
    public static int n, m;
    public static int nx, ny;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static int[][] miro = new int[201][201];

    public static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        while (!q.isEmpty()) {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (miro[nx][ny] == 0) continue;
                if (miro[nx][ny] == 1) {
                    miro[nx][ny] = miro[x][y] + 1;
                    q.offer(new Node(nx, ny));
                }
            }
        }
        return miro[n-1][m-1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i<n; i++){
            String str = sc.nextLine();
            for(int j = 0; j<m; j++){
                miro[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs(0,0));
    }
}
