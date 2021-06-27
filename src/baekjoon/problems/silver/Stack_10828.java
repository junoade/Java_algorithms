package baekjoon.problems.silver;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 알고리즘 기초
 * June 23th
 */
public class Stack_10828 {
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack_10828 stk = new Stack_10828();
        int N = Integer.parseInt(st.nextToken()); // Stack 배열 크기

        String cmd = "";
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<N; i++){
            // N 만큼 명령어 입력
            cmd = br.readLine();    // N-1 일때 문제
            String temp = stk.solve(cmd);
            if(temp.length()>=1)
                System.out.println(temp);
        }


    }
    public String solve(String str){
        String temp = "";

        if(str.contains("push")){
           //temp.append(str.substring(5));
            stack.push(Integer.parseInt(str.substring(5))); // stack top 에 넣음
        }else if(str.contains("pop")){
            if(!stack.isEmpty())
                temp = stack.pop().toString();
            else
                temp="-1";
        }else if(str.contains("size")){
            temp=stack.size()+" ";
        }else if(str.contains("empty")){
            if(stack.empty())
                temp="1";
            else
                temp="0";
        }else if(str.contains("top")){
            if(!stack.isEmpty())
                temp=stack.peek().toString();
            else
                temp="-1";
        }
        return temp;
    }

}
