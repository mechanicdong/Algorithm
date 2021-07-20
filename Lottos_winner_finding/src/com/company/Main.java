class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {0,0};
        int zeroCnt = 0;
        int matched = 0;

        for(int l : lottos){
            if( l == 0 ) zeroCnt++;
            else{
                for(int w : win_nums){
                    if(l == w) {
                        matched++;
                        break;
                    }
                }
            }
        }
        int min = matched;           //최저 등수
        int max = matched + zeroCnt; //최고 등수
        answer[0] = Math.min(7-max, 6);
        answer[1] = Math.min(7-min, 6);

        return answer;
    }
}