package lecture.week7;
/**
 *  컴퓨터알고리즘과실습3_ 주종화교수님
 *  실습7- 스트링 매칭
 *  2017112095 컴퓨터공학과 최준호
 *
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class RandomGenerator {
    private String filepath;
    private int SIZE;

    RandomGenerator(String filepath){
        this.filepath=filepath;
    }

    static void mkFile(String filepath,int size) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        PrintWriter pw = new PrintWriter(writer);
        int random=0;

        for(int i=0; i<size; i++ ){
           random= (int) (Math.random() * 10);
           /*pw.printf("%d ", random);*/
           pw.print(random);
        }
        System.out.println("파일 출력 완료");
        pw.flush();
        pw.close();
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
