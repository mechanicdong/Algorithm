package com.company;
/*
 ****** 2021 / 09 / 19 / Sun ******
 * 자료 구조 해쉬 테이블의 장단점과 용도
 * 장점
 *   데이터 저장/읽기 속도가 빠르다(검색 속도가 빠름)
 *   해쉬는 key에 대한 data가 있는지(중복) 확인이 쉬움
 * 단점
 *   일반적으로 저장공간이 좀 더 많이 필요함
 *   여러키에 해당하는 주소가 동일할 경우 충돌을 해결하기 위한 별도 자료구조가 필요
 * 주요 용도
 *   검색이 많이 필요할 경우
 *   저장, 삭제, 읽기가 빈번한 경우
 *   캐쉬 구현 시 (중복 확인이 쉽기 때문)
 * 
 * * 충돌 방지를 위한 두 가지 기법 중 Chaining 기법
 *  = 개방 해슁 or Open Hashing 기법
 *  = 충돌이 일어나면, LinkedList 자료구조를 사용하여 LinkedList로 데이터를 추가로 뒤에 연결시켜서 저장하는 기법
 */


import java.lang.reflect.TypeVariable;

public class Main {

    public static class MyHash {
        public Slot[] hashTable;

        public MyHash(Integer size){
            this.hashTable = new Slot[size];
        }

        public class Slot{
            String key;
            String value;
            Slot next; // LinkedList의 Pointer
            Slot(String key, String value){
                this.value = value;
                this.key = key;
                this.next = null;
            }
        }
        //key가 문자열일 때, 문자열의 앞 글자를 숫자로 변환해서 Division 기법을 사용하여 Key의 주소(인덱스 번호) 계산
        public int hashFunc(String key){
            return (int)(key.charAt(0)) % this.hashTable.length;
        }

        public boolean saveData(String key, String value){
            Integer address = this.hashFunc(key);
            if(this.hashTable[address] != null){
                //this.hashTable[address].value = value;
                //내부 코드가 LinkedList일 수도 있으므로 순회
                Slot findSlot = this.hashTable[address];
                Slot prevSlot = this.hashTable[address];
                while(findSlot != null){
                    if(findSlot.key == key){
                        findSlot.value = value;
                        return true;
                    }
                    else {
                        prevSlot = findSlot;
                        findSlot = findSlot.next;
                    }
                }
                prevSlot.next = new Slot(key, value);
            }
            else {
                this.hashTable[address] = new Slot(key, value);
            }
            return true;
        }

        public String getData(String key){
            Integer address = this.hashFunc(key);
            if(this.hashTable[address] != null){
                //return this.hashTable[address].value;
                Slot findSlot = this.hashTable[address];
                while(findSlot != null){
                    if(findSlot.key == key){
                        return findSlot.value;
                    }
                    else {
                        findSlot = findSlot.next;
                    }
                }
                return null;
            }
            else {
                return null;
            }
        }
    }
    public static void main(String[] args) {
        MyHash mainObj = new MyHash(20);
        mainObj.saveData("leedonghee", "1");
        mainObj.saveData("leedong", "2");
        System.out.println(mainObj.getData("leedonghee"));
    }
}
