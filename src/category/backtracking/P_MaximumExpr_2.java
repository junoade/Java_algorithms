package category.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --------------------------------------------------------------<br/>
 * <b> 프로그래머스 수식 최대화</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 입력 조건에 따른 문제 연산자, 피연산자 최대 개수 분석 <br/>
 * 어떻게 우선순위 별 최대값을 확인할 것인가?
 * - 우선순위는 어떻게 결정할 수 있을까?
 * - 현재 우선순위에 대해 어떻게 계산할 것인가?
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 우선순위 완전탐색을 위해 모든 경우를 담은 배열을 생성하는 방법<br/>
 * 2. 반복 + 재귀의 백트랙킹을 이용해 모든 상태 공간을 찾아 보는 방법<br/>
 * 3. 시간 복잡도 O(N^3 * 3!)
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 약 80MB , 실행시간  6ms ~ 10ms<br/>
 * --------------------------------------------------------------
 */
public class P_MaximumExpr_2 {
    // 피연산자 - 연산자 구분을 위해
    List<Long> operands;
    List<String> ops;

    // 우선순위 결정을 위해 백트랙킹 할 때
    String[] symbol;
    boolean[] visited;

    long MAX_VALUE;

    public long solution(String expression) {
        /* step1 입력 처리 */
        operands = splitOperands(expression);
        ops = splitOps(expression);
        symbol = getDistinctSymbol();
        visited = new boolean[symbol.length];
        // testSplit();

        /* step2) */
        pick(0, "");
        return MAX_VALUE;
    }

    // 우선순위 선정 (backtracking을 이용해서 )
    void pick(int depth, String priorities) {
        // base case : 모든 깊이 탐색 완료
        if (depth == symbol.length) {
            MAX_VALUE = Math.max(MAX_VALUE, calculate(priorities));
            return;
        }

        // base case : 이미 방문한 프레임
        for(int i = 0; i < symbol.length; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            pick(depth + 1, priorities + symbol[i]);
            visited[i] = false;
        }
    }

    // step3 - 특정된 우선순위에 대해 계산 수행
    // O(N^3)
    long calculate(String priorities) {
        // 여러 우선순위 검색 중에 객체 operands, ops 에 CRUD가 진행되어 의도치 않은 semantic error 있을 수 있음
        // 이번엔 링크드리스트로 한번 풀어봄
        List<Long> copiedOperands = new LinkedList<>();
        List<String> copiedOps = new LinkedList<>();
        copiedOperands.addAll(operands);
        copiedOps.addAll(ops);

        //O(N/2); N: 입력 문자열 길이
        for (String op : priorities.split("")) {
            // 만약 연산자 c 가 2개 이상 존재하면,
            // 왼쪽부터 계산한다
            int opIdx;
            // O(N/2)
            while((opIdx = copiedOps.indexOf(op)) != -1) {
                //O(N)
                long temp = calculate(op, copiedOperands.get(opIdx), copiedOperands.get(opIdx + 1));
                //O(1)
                copiedOperands.remove(opIdx);
                copiedOperands.add(opIdx, temp);
                copiedOperands.remove(opIdx + 1);
                copiedOps.remove(opIdx);
            }
        }
        return Math.abs(copiedOperands.get(0));
    }

    long calculate(String op, long lhs, long rhs) {
        if(op.equals("+")) return lhs + rhs;
        else if(op.equals("-")) return lhs - rhs;
        else return lhs * rhs;
    }

    private List<Long> splitOperands(String s) {
        return Arrays.stream(s.split("[*+-]"))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<String> splitOps(String s) {
        String[] temp = s.split("[^*+-]");
        return Arrays.stream(temp)
                .filter(o -> !o.isEmpty())
                .collect(Collectors.toList());
    }

    private String[] getDistinctSymbol() {
        return ops.stream().distinct().toArray(String[]::new);
    }

    private void testSplit() {
        System.out.println("> testSplit() result");
        System.out.println(operands);
        System.out.println(ops);
        System.out.println(Arrays.toString(symbol));
    }

    public static void main(String[] args) {
        P_MaximumExpr_2 p = new P_MaximumExpr_2();
        String test = "100-200*300-500+20";
        p.solution(test);
        System.out.println(p.MAX_VALUE);
    }
}
