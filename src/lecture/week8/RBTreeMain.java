package lecture.week8;
/**
 * 컴퓨터알고리즘과실습3 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 8주차 Red Black Tree Insertion 실행을 위한 RBTreeMain 클래스
 */

import java.io.IOException;
import java.util.Scanner;

public class RBTreeMain {
    public static void main(String[] args) throws IOException {
        RBTree rbt = new RBTree();
        Node node = new Node();

        Scanner sc = new Scanner(System.in);

        /* related to user inputs */
        char cmd;
        String user_input;
        int inputKey = 0;
        /* boolean twice = false;*/

        do {
            showCommands();
            rbt.show(); //트리를 보여줌

            System.out.print("Commands : ");

            cmd = (char) System.in.read();
            if (cmd == '+' ) {
                try {
                    inputKey = sc.nextInt();
                    System.out.println();
                } catch (Exception e) {
                    System.out.println("한번에 하나의 커맨드만 입력해주세요");
                    sc = new Scanner(System.in);  //Scanner을 재할당
                    continue;
                }
            }else{
                System.out.println("주어진 커맨드 +,?,- 또는 종료 Q,q 만을 입력해주세요.");
            }
            /*System.out.println(inputKey);*/

            if (cmd == '+') {
                try {
                    System.out.println("Insert : Key = " + inputKey);
                       /* if (!rbt.findNode(inputKey)) {
                            rbt.insert(inputKey);
                        } else {
                            System.out.println("중복됩니다");
                        }*/
                    rbt.insert(inputKey);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("올바른 값 입력하세요. (+)");
                }
            }


        } while (cmd != 'Q' && cmd != 'q');
    }

    static void showCommands() {
        System.out.println("2017112095 컴퓨터공학과 최준호");
        System.out.println("Commands : ");
        System.out.println("    +key : Insert element");
        System.out.println(" Q       : Quit the program.");
    }
}
