package category.string;

public class P_MultipleBinaryFlip {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int iter = 0;
        int zeroCnt = 0;
        do {
            int before = s.length();
            s = s.replace("0", "");
            int after = s.length();
            zeroCnt += before - after;

            s = Integer.toBinaryString(s.length());
            iter++;
        } while(s.length() > 1);

        answer[0] = iter;
        answer[1] = zeroCnt;
        return answer;
    }
}
