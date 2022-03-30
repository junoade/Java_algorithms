package category.hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 백준 1620 - 포켓몬 마스터
 * 키-값 이용하기
 * https://www.acmicpc.net/problem/1620
 * 
 * 해당 풀이는 메모리를 96164 KB, 시간이 1604ms로 시간을 많이 소모하였음
 *
 * 또한 다른 풀이에 비해 메모리 역시 많이 소모하였음.
 * 그 이유로, 반복적으로 System.out.println()을 호출하기 때문
 *
 * 예외 처리 static 함수부가 메모리, 시간을 좀 차지함.
 * 그 이유로 빈번한 숫자형 문자열 입력에 대해 예외처리 로직을 처리해야만 하기 때문으로 여겨짐.
 *
 */
public class PocketmonMaster {

    public static HashMap<String, Integer> dict; // 문자열을 많이 입력 받는데, 아무래도 해시 함수를 이용한 HashMap을 이용해 빠르게 키 값 찾는게 좋을 듯
    public static String[] pocketMons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int T = Integer.parseInt(temp[0]);
        int N = Integer.parseInt(temp[1]);

        dict = new HashMap<>();
        pocketMons = new String[T+1]; // 1 부터 시작 하기 때문

        for(int i = 0; i<T; i++){
            String name = br.readLine();
            dict.put(name, i+1);
            pocketMons[i+1] = name;
        }

        for(int i = 0; i<N; i++){
            System.out.println(solution(br.readLine()));
        }
    }
    public static String solution(String s){
        if(isIntNumber(s)){
            return pocketMons[Integer.parseInt(s)];
        }else{
            return String.valueOf(dict.get(s));
        }
    }

    public static boolean isIntNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /*public static String solution(String s){
        String answer = "";
        if(dict.containsKey(s)){
            answer = dict.get(s); // value
            return answer;
        }else{
            if(dict.containsValue(s)){
                *//* 시간 초과 *//*
                for(Map.Entry<String, String> entry : dict.entrySet()){
                    if(entry.getValue().equals(s)) {
                        answer = entry.getKey();
                        return answer;
                    }
                }

                 List<String> foundKeys = dict.entrySet()
                        .stream()
                        .filter(entry -> Objects.equals(entry.getValue(), s))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                return foundKeys.get(0);
            }
        }
        return answer;
    }*/
}
