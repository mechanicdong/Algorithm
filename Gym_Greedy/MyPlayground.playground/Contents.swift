import Foundation

func solution(_ n:Int, _ lost:[Int], _ reserve:[Int]) -> Int {
    // 체육복애 여별의 수를 저장하는 배열, 초기값은 0
    var clothCount = Array(repeating: 0, count: n)
    
    // 체육복을 읽어 버린 경우 -1로 설정
    for i in lost { clothCount[i-1] += -1 }
    
    // 여벌 체육복이 있는 경우 1로 설정
    for j in reserve { clothCount[j-1] += 1 }
    
    // 전체 학생의 여벌 체육복 배열을 인덱스와 값을 하나씩 비교하면 순회
    for (idx, k) in clothCount.enumerated() {
        // 현재 학생이 체육복을 읽어 버린 경우
        if k == -1 {
            // 앞의 학생이 여벌 체육복이 있는지(== 1) 비교한 후 있으면 빌림
            if idx > 0 && clothCount[idx-1] == 1 {
                clothCount[idx] += 1
                clothCount[idx-1] += -1
            }
            // 뒤의 학생이 여벌 체육복이 있는지 비교한 후 있으면 빌림
            else if idx < clothCount.count - 1 &&
                    clothCount[idx+1] == 1 {
                clothCount[idx] += 1
                clothCount[idx+1] += -1
            }
        }
    }
    
    // 최소 체육복이 있는 경우(>= 0)의 학생의 수를 최종 반한
    return clothCount.filter{ $0 >= 0 }.count
}

solution(5, [2,4], [3])
