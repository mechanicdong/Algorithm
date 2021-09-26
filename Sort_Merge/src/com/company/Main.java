package com.company;
import java.util.ArrayList;
/*
2021/09/26/日 - 병합정렬 merge sort
크게 두 가지 단계로 진행
1) split(최대한 쪼갬) 2) merge(비교 후 새로운 mergedlist에 merge)
시간복잡도 - O(n)
 */
public class Main {
    public static class Merge{


        public ArrayList<Integer> mg(ArrayList<Integer> dataList){
            //배열을 앞뒤 두 배열로 짜르는 코드 작성해보기 (일반화)
            ArrayList<Integer> leftarr = new ArrayList<Integer>();
            ArrayList<Integer> rightarr = new ArrayList<Integer>();
            int medium = dataList.size()/2;
            if(dataList.size() <= 1) return dataList;
            else {
                //subList 활용하여 split
                leftarr = mg(new ArrayList<Integer>(dataList.subList(0,medium)));
                rightarr = mg(new ArrayList<Integer>(dataList.subList(medium, dataList.size())));
            }
            return mergefx(leftarr, rightarr);
        }


        public ArrayList<Integer> mergefx(ArrayList<Integer> leftarr, ArrayList<Integer> rightarr){
            ArrayList<Integer> mergedList = new ArrayList<Integer>();
            int leftPoint = 0; int rightPoint = 0;
            //CASE 1 : leftarr, rightarr가 모두 있을 때
            while(leftarr.size() > leftPoint && rightarr.size() > rightPoint) {
                if(leftarr.get(leftPoint) > rightarr.get(rightPoint)) {
                    mergedList.add(rightarr.get(rightPoint));
                    rightPoint += 1;
                }
                else{
                    mergedList.add(leftarr.get(leftPoint));
                    leftPoint += 1;
                }
            }
            //CASE 2 : leftarr 만 있을 때
            while(leftarr.size() > leftPoint){
                mergedList.add(leftarr.get(leftPoint));
                leftPoint+=1;
            }
            //CASE 3 : rightarr만 있을 때
            while(rightarr.size() > rightPoint){
                mergedList.add(rightarr.get(rightPoint));
                rightPoint += 1;
            }
            return mergedList;
        }
    }
    public static void main(String[] args) {
        Merge mg = new Merge();
        ArrayList<Integer> dataList = new ArrayList<Integer>();
        for(int i = 0; i<5; i++){
            dataList.add(i);
        }
        System.out.println(mg.mg(dataList));

    }
}
