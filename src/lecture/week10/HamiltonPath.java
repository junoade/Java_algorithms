package lecture.week10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class HamiltonPath {
    public static boolean isFirst=false;

    public static void main(String[] args) {

        int lmer;
        Stack<String> stack = new Stack<>();
        /* 주어진 스펙트럼 1 */
        stack.add("AGT");
        stack.add("AAA");
        stack.add("ACT");
        stack.add("AAC");
        stack.add("CTT");
        stack.add("GTA");
        stack.add("TTT");
        stack.add("TAA");

        System.out.println(stack.toString());
        System.out.println(stack.pop());
        stack.clear();

        /* 주어진 스펙트럼 집합 2 */
        /*stack.add("ATG");
        stack.add("AGG");
        stack.add("TGC");
        stack.add("TCC");
        stack.add("GTC");
        stack.add("GGT");
        stack.add("GCA");
        stack.add("CAG");

        stack.clear();*/

        /* 주어진 스펙트럼 집합 3 */
        /*stack.add("ATG");
        stack.add("TGG");
        stack.add("TGC");
        stack.add("GTG");
        stack.add("GGC");
        stack.add("GCA");
        stack.add("GCG");
        stack.add("CGT");

        stack.clear();*/

        /* 주어진 스펙트럼 집합 4 */
        /*stack.add("ATGC");
        stack.add("TGCG");
        stack.add("GCGG");
        stack.add("CGGC");
        stack.add("GGCT");
        stack.add("GCTG");
        stack.add("CTGT");
        stack.add("TGTA");
        stack.add("GTAT");
        stack.add("TATG");
        stack.add("ATGG");
        stack.add("TGGT");
        stack.add("GGTG");*/

    }
    public static void mkArray(){
        /* 스펙트럼의 원소들을 Hamilton Path을 위한 정점으로 만들기 전, 배열을 만든다 */
    }
    public static void inputstack(Stack<String> stack){

    }
    public static void hamilton(){

    }
    public static void BFS(){

    }
}
class Vertex{
    String element;
    boolean visited=false;
}