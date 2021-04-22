package lecture.week6;
/**
 * 2017112095 컴퓨터공학과 최준호
 * 컴퓨터알고리즘과 실습 _ 주종화 교수님
 * 과제6 BST 구현하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BST_Main {
    public static void main(String[] args) throws IOException {
        BST bst = new BST();
        Node node = new Node();

        Scanner sc = new Scanner(System.in);

        /* related to user inputs */
        char cmd;
        String user_input;
        int inputKey = 0;
        /* boolean twice = false;*/
        
        do {
            showCommands();
            bst.show(); //트리를 보여줌

            System.out.print("Commands : ");

            cmd = (char) System.in.read();
            if (cmd == '+' || cmd == '?' || cmd == '-') {
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

            switch (cmd) {
                case '+':
                    try {
                        System.out.println("Insert : Key = " + inputKey);
                        if (!bst.findNode(inputKey)) {
                            bst.insert(inputKey);
                        } else {
                            System.out.println("중복됩니다");
                        }

                    } catch (Exception e) {
                        System.out.println("올바른 값 입력하세요. (+)");
                    }
                    break;
                case '?': //
                    try {
                        if (bst.findNode(inputKey)) {
                            if (bst.getNode(inputKey).getLeft() != null) {
                                System.out.println("left child is " + bst.getNode(inputKey).getLeft().getKey());
                            } else {
                                System.out.println("left child is null");
                            }
                            if (bst.getNode(inputKey).getRight() != null) {
                                System.out.println("right child is " + bst.getNode(inputKey).getRight().getKey());
                            } else {
                                System.out.println("right child is null");
                            }
                        } else {
                            System.out.println("해당 키값을 갖는 노드가 BST에 없습니다. ");
                        }
                    } catch (Exception e) {
                        System.out.println("올바른 값을 입력하세요 (?) ");
                    }
                    break;
                case '-':
                    try {
                        bst.delete(inputKey); // 내부에서 노드가 존재하는지 확인해서 굳이 또 안해도됨
                    } catch (Exception e) {
                        System.out.println("올바른 값을 입력하세요 (-)");
                    }

            }


        } while (cmd != 'Q' && cmd != 'q');
    }

    static void showCommands() {
        System.out.println("2017112095 컴퓨터공학과 최준호");
        System.out.println("Commands : ");
        System.out.println("    +key : Insert(or update) element");
        System.out.println("    ?key : retrieve element");
        System.out.println("    -key : remove element");
        System.out.println(" Q       : Quit the program.");
    }

}
