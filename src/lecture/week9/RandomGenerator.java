package lecture.week9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RandomGenerator {
    private String filepath;
    private int SIZE;
    private static char[] AGTC = {'A', 'G', 'T', 'C'};

    RandomGenerator(String filepath) {
        this.filepath = filepath;
    }

    static void mkFile(String filepath, int size) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        PrintWriter pw = new PrintWriter(writer);
        int random = 0;
        char temp;
        for (int i = 0; i < size; i++) {
            random = (int) (Math.random() * 4); // 0,1,2,3 중 랜덤하게 하나를 고름
            temp = AGTC[random];

            /*pw.printf("%d ", random);*/
            pw.print(temp);
        }
        System.out.println("파일 출력 완료");
        pw.flush();
        pw.close();
    }

    /* 패턴 만들기 구현 */
    static String mkPattern(int length){
        int random;
        String result = "";
        for (int i = 0; i < length; i++) {
            random = (int) (Math.random() * 4); // 0,1,2,3 중 랜덤하게 하나를 고름
            result += AGTC[random];
        }
        return result;
    }


    String getFilepath() {
        return filepath;
    }

    int getSIZE() {
        return SIZE;
    }

    void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

}
