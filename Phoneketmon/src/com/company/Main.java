import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int cnt = 0;
        List<Integer> list = new ArrayList<Integer>();
        cnt = nums.length / 2;
        list.add(nums[0]);
        for(int i = 1; i<nums.length; i++){
            if(!list.contains(nums[i]))
                list.add(nums[i]);
        }
        answer = (list.size()<cnt) ? list.size() : cnt;

        return answer;
    }
}