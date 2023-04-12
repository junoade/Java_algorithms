package category.bruteforce;

import java.util.*;
import java.util.stream.Collectors;

public class P_MaximumExpr {
    static long MAX_VALUE;
    static final String REGEX = "[+*-]";
    static final String REGEX_OPS = "[^+*-]";
    static String[] SYMBOL;

    static List<String> ops;
    static List<Long> operands;
    static boolean[] visited;

    public long solution(String expression) {
        MAX_VALUE = 0;
        ops = getOperators(expression);
        operands = getOperands(expression);
        SYMBOL = ops.stream().distinct().toArray(String[]::new);
        visited = new boolean[SYMBOL.length];

        rec(0, "");
        return MAX_VALUE;
    }


    // 우선순위의 순서를 백트랙킹으로 정한다
    void rec(int depth, String priorities) {
        // base case
        if (depth == SYMBOL.length) {
            MAX_VALUE = Math.max(MAX_VALUE, getValue(priorities));
            return;
        }

        // 해당 시점에서 다양한 우선순위를 시도할 수 있도록
        // 값 할당 주의
        for (int i = 0; i < SYMBOL.length; i++) {
            // base case : 이미 우선순위를 배정한 연산자 심볼
            if (visited[i]) continue;

            visited[i] = true;
            rec(depth + 1, priorities + SYMBOL[i]);
            visited[i] = false;
        }
    }


    private List<String> getOperators(String expression) {
        String[] ops = expression.split(REGEX_OPS);
        return Arrays.stream(ops)
                .filter(s -> !s.isEmpty())
                //.sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    private List<Long> getOperands(String expression) {
        return Arrays.stream(expression.split(REGEX))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    // 좌측 피연산자 priorSum, 우측 피연산자 operadns[depth + 1]
    private long getValue(String priorities) {
        List<Long> copyOperands = new ArrayList<>(operands.size());
        List<String> copyOps = new ArrayList<>(ops.size());
        copyOperands.addAll(operands);
        copyOps.addAll(ops);

        // 우선순위 연산자별로 진행
        for (String op : priorities.split("")) {
            // 동일한 우선순위인 경우 왼쪽부터
            int opIdx;
            while ((opIdx = copyOps.indexOf(op)) != -1) {
                long newValue = calculate(op, copyOperands.get(opIdx), copyOperands.get(opIdx + 1));
                copyOperands.remove(opIdx);
                copyOperands.add(opIdx, newValue); // 중간 idx에 add하면 그 이후는 arraycopy 하면서 이동됨
                copyOperands.remove(opIdx + 1);
                copyOps.remove(opIdx);
            }
        }

        return Math.abs(copyOperands.get(0));
    }

    private long calculate(String op, long leftValue, long rightValue) {
        if (op.equals("+")) return leftValue + rightValue;
        else if (op.equals("-")) return leftValue - rightValue;
        else return leftValue * rightValue;
    }

    public static void main(String[] args) {
        P_MaximumExpr p = new P_MaximumExpr();
        // p.solution("100-200*300-500+20");
        p.solution("50*6-3*2");
        System.out.println(MAX_VALUE);
    }
}
