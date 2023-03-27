package category.string;

public class P_MultipleBinaryFlip {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int iter = 0;
        int zeroCnt = 0;
        do {
            zeroCnt += countZeros(s);
            s = s.replace("0", "");
            s = Integer.toBinaryString(s.length());
            iter++;
        } while(s.length() > 1);

        answer[0] = iter;
        answer[1] = zeroCnt;
        return answer;
    }

    // O(N)
    int countZeros(String s) {
        int result = 0;
        for(char c : s.toCharArray()) {
            if(c == '0') { result++; }
        }
        return result;
    }
}
