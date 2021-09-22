package com.company;
/*
********** 2021/09/20/Mon **********
Tree - 이진탐색 트리
* 이진트리 : 노드의 최대 Branch가 2인 트리
* 이신 탐색 트리(Binary Search Tree, BST) : 이진 트리에 다음과 같은 추가 조건이 있는 트리
*  -> 왼쪽 노드는 해당 노드보다 작은 값, 오른쪽 노드는 해당 노드보다 큰 값을 가지고 있음
* 
* 자료구조 이진 탐색 트리의 장점과 주요 용도
* 1) 주요 용도 : 데이터 검색(탐색)
* 2) 장점     : 탐색 속도를 개선할 수 있음

------------------------------------------
+) 2021.09.22(Wed)
* 이진 탐색 삭제 Method(delete) 기능 추가
*
 */
// 테스트를 위해, inner class 인 Node 클래스를 별도의 클래스로 선언함


public class Main {

    public class Node{
        Node left;
        Node right;
        int value;
        Node(int data) {
            this.value = data;
            this.left = null;
            this.right = null;
        }
    }
    public static class NodeMgmt{
        Node head = null;
        //Inner class 생성
        public class Node {
            Node left;
            Node right;
            int value;
            public Node(int data){
                this.value = data;
                this.left = null;
                this.right = null;
            }
        }
        public boolean insertNode(int data){
            if(this.head == null){
                this.head = new Node(data);
            }
            else {
                Node findNode = this.head;
                while(true){
                    if(data < findNode.value){
                        if(findNode.left != null){
                            findNode = findNode.left;
                        } else {
                            findNode.left = new Node(data);
                            break;
                        }
                    }
                    else {
                        if(findNode.right != null){
                            findNode = findNode.right;
                        }
                        else {
                            findNode.right = new Node(data);
                            break;
                        }
                    }
                }
            }
            return true;
        }

        public Node searchNode(int data){
            //case1 : 해당 data를 가진 Node가 없을 때
            if(this.head == null){
                return null;
            }
            //case2 : 해당 data를 가진 Node 있을 때
            else {
                Node findNode = this.head;
                while(this.head != null){
                    if (findNode.value == data) {
                        return findNode;
                    }
                    else if(data < findNode.value){
                        findNode = findNode.left;
                    }
                    else {
                        findNode = findNode.right;
                    }
                }
                return  null;
            }
        }

        public boolean delete(int value){
            boolean searched = false;

            Node currParentNode = this.head;
            Node currNode = this.head;

            //예외 케이스1 : Node가 하나도 없을 때
            if(this.head == null){
                return false;
            }
            //예외 케이스2 : Node가 단지 하나만 있고, 해당 Node가 삭제 할 Node일 때
            else {
                if(this.head.value == value && this.head.left == null && this.head.right == null){
                    this.head = null;
                    return true;
                }
            }
            while(currNode != null){
                if(currNode.value == value){
                    searched = true;
                    break;
                } else if(value < currNode.value){
                    currParentNode = currNode;
                    currNode = currNode.left;
                } else {
                    currParentNode = currNode;
                    currNode = currNode.right;
                }
            }
            if(searched == false){
                return false;
            }
        /*
        여기까지 실행되면, currNode에는 해당 데이터를 갖고 있는 Node,
        currParentNode 에는 해당 데이터를 갖고 있는 Node의 부모 Node
         */

        // Case 1 : 삭제 할 Node가 Leaf Node인 경우
            if(currNode.left == null && currNode.right ==null){
                if(value < currParentNode.value){
                    currParentNode.left = null;
                    currNode = null;
                }
                else {
                    currParentNode.right = null;
                    currNode = null;
                }
                return true;
            }

        // Case 2 : 삭제할 Node가 Child Node를 한 개 가지고 있을 경우
            else if (currNode.left != null && currNode.right == null){
                if(value < currParentNode.value){
                    currParentNode.left = currNode.left;
                    currNode = null;
                } else {
                    currParentNode.left = currNode.right;
                    currNode = null;
                }
                return true;
            }
            else if (currNode.left == null & currNode.right != null){
                if(value > currParentNode.value){
                    currParentNode.left = currNode.right;
                    currNode = null;
                } else {
                    currParentNode.left = currNode.left;
                    currNode = null;
                }
                return true;
            } // end of Case 2

        // Case 3 : 삭제할 Node 가 Child Node를 두 개 갖고 있을 경우 (삭제할 Node가 Parent Node 왼쪽에 있을 때)
            else {
                // Case 3-1 : 삭제 할 Node가 Parent Node 왼쪽에 있을 때
                if( value < currParentNode.value){
                    //가장 왼쪽에 있는 Node를 changeNode로 지정 + changeNode가 ChildNode를 갖고 있는 경우도 고려(이 경우 changeNode 오른쪽에 있음)
                    Node changeNode = currNode.right;
                    Node changeParentNode = currNode.right;
                    //순회
                    while(changeNode.left != null){
                        changeParentNode = changeNode;
                        changeNode = changeNode.left;
                    } //여기까지 실행되면, changeNode에는 삭제할 Node의 오른쪽 Node 중에서
                      //가장 작은 값을 가진 Node가 들어 있음

                    //Case 3-1-2 : changeNode의 오른쪽 childNode가 있을 때(right에만 있음, 작으면 그게 changeNode가 되겠지?)
                    if(changeNode.right != null){
                        changeParentNode.left = changeNode.right;
                    }
                    //Case 3-1-1 : changeNode의 오른쪽 childNode가 없을 때
                    else {
                        changeParentNode.left = null;
                    }
                    //Case 3-1 은 ParentNode의 왼쪽에 삭제할 Node가 위치했을 때이므로
                    //currParentNode 의 왼쪽 ChildNode에 삭제할 Node의 오른쪽 자식 중
                    //가장 작은 값을 가진 changeNode를 연결
                    currParentNode.left = changeNode;

                    //ParentNode의 왼쪽 ChildNode가 현재 changeNode이고,
                    //changeNode의 왼쪽, 오른쪽 childNode를 모두 삭제할 currNode의 기존 왼쪽, 오른쪽 노드로 변경
                    changeNode.right = currNode.right;
                    changeNode.left = currNode.left;
                    currNode = null;
                }
                // Case 3-2 : 삭제 할 Node가 Parent Node 오른쪽에 있을 때
                else {
                    Node changeNode = currNode.right;
                    Node changeParentNode = currNode.right;
                    //순회
                    while(changeNode.left != null){
                        changeParentNode = changeNode;
                        changeNode = changeNode.left;
                    } //여기까지 실행되면, changeNode에는 삭제할 Node의 오른쪽 Node 중에서
                    //가장 작은 값을 가진 Node가 들어 있음

                    //Case 3-1-2 : changeNode의 오른쪽 childNode가 있을 때(right에만 있음, 작으면 그게 changeNode가 되겠지?)
                    if(changeNode.right != null){
                        changeParentNode.left = changeNode.right;
                    }
                    //Case 3-1-1 : changeNode의 오른쪽 childNode가 없을 때
                    else {
                        changeParentNode.left = null;
                    }
                    //Case 3-2 은 ParentNode의 오른쪽에 삭제할 Node가 위치했을 때이므로
                    //currParentNode 의 오른쪽 ChildNode에 삭제 할 Node의 오른쪽 자식 중
                    //가장 작은 값을 가진 changeNode를 연결
                    currParentNode.right = changeNode;

                    //ParentNode의 오른쪽 ChildNode가 현재 changeNode이고,
                    //changeNode의 왼쪽, 오른쪽 childNode를 모두 삭제할 currNode의 기존 왼쪽, 오른쪽 노드로 변경
                    changeNode.right = currNode.right;
                    changeNode.left = currNode.left;
                    currNode = null;
                }
                return true;
            }
        }

    }


    public static void main(String[] args) {
        // Case3-1: 삭제할 Node가 Child Node를 두 개 가지고 있을 경우
        NodeMgmt myTree = new NodeMgmt();
        myTree.insertNode(10);
        myTree.insertNode(15);
        myTree.insertNode(13);
        myTree.insertNode(11);
        myTree.insertNode(14);
        myTree.insertNode(18);
        myTree.insertNode(16);
        myTree.insertNode(19);
        myTree.insertNode(17);
        myTree.insertNode(7);
        myTree.insertNode(8);
        myTree.insertNode(6);
        System.out.println(myTree.delete(15));
        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);
    }
}
