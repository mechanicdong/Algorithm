package com.company;
import java.util.*;

public class Main {
    public static int d[][] = new int[50][50];      //가본 위치 저장
    public static int arr[][] = new int[50][50];    //전체 맵 정보
    public static int n, m, direction,x, y;
    public static int dx[] = {-1, 0, 1, 0};         //북쪽에서 떨어진 위치의 변경값
    public static int dy[] = {0, 1, 0, -1};         //서쪽에서 떨어진 위치의 변경값
    
    public static void turn_left(){ //N-0, E-1, S-2, W-3
        direction -= 1;
        if(direction == -1){
            direction = 3;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); m = sc.nextInt();
        x = sc.nextInt(); y = sc.nextInt(); direction = sc.nextInt();
        //현재 좌표 방문처리
        d[x][y] = 1;

        for (int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        //Start Simul
        int cnt = 1;
        int turn_time = 0;
        while(true){
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            //왼쪽 방향에 가보지 않은 칸이 존재할 때
            if(d[nx][ny] == 0 && arr[nx][ny] == 0){
                d[nx][ny] = 1;
                x = nx; y = ny;
                cnt += 1;
                turn_time = 0; //why?
                continue;
            }
            else turn_time += 1;
            //4면 모두 갈 수 없을 때
            if(turn_time == 4){
                nx = x - dx[direction];
                ny = y - dy[direction];
                //뒤로 갈 수 있다면 가기(육지)
                if(arr[nx][ny] == 0){
                    x = nx;
                    y = ny;
                }
                //만약 바다로 막힌 경우
                else break;
                turn_time = 0;
            }
        }
        System.out.println(cnt);
    }
}
