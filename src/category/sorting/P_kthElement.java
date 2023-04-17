package category.sorting;

import java.util.Arrays;

public class P_kthElement {
    public int[] solution(int[] array, int[][] commands) {
        final int C = commands.length;
        int[] answer = new int[C];
        for(int T = 0; T < C; T++) {
            answer[T] = getKthElement(array, commands[T]);
        }
        return answer;
    }

    private int getKthElement(int[] array, int[] command) {
        if(command.length != 3) {
            return -1;
        }

        int i = command[0], j = command[1], k = command[2];
        int[] temp = new int[j - i + 1];
        System.arraycopy(array, i-1, temp, 0, temp.length);
        Arrays.sort(temp);
        return temp[k - 1];
    }
}
