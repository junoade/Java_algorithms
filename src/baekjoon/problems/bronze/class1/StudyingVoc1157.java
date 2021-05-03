package baekjoon.problems.bronze.class1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class StudyingVoc1157 {
    private String str;
    HashMap<Character, Integer> map;

    StudyingVoc1157() {
        map = new HashMap<Character, Integer>(); //key : value
    }

    public static void main(String[] args) {
        String input;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            StudyingVoc1157 std = new StudyingVoc1157();
            std.setString(input);
            int length = std.str.length();
            char temp;
            int num;
            int cnt;
            for (int i = 0; i < length; i++) {
                temp = std.str.charAt(i);
                if ((num = std.check(temp))!=0) {
                    if(num==1){
                        cnt = std.map.get(temp);
                    }else if(num == 2){
                        cnt = std.map.get((char)(temp-32));
                    }else{
                        cnt = std.map.get((char)(temp+32));
                    }
                    std.map.put(temp, ++cnt);
                } else {
                    std.map.put(temp, 1);
                }
            }
            findMax(std.map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int check(char ch) {
        int result;
        if(map.containsKey(ch)){
            result =1;
        }else if(map.containsKey((char)(ch-32))){
            result =2;
        }else if(map.containsKey((char)(ch+32))){
            result =3;
        }else{
            result =0;
        }
        return result;
        /*return map.containsKey(ch) || map.containsKey((char)(ch-32)) || map.containsKey((char)(ch+32));*/
    }

    static void findMax(HashMap<Character,Integer> map) {
        Integer max = map.entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get().getValue();

        List listOfMax = map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(max))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        if(listOfMax.size() > 1){
            System.out.println("?");
        }else{
            System.out.println(listOfMax.get(0).toString().toUpperCase());
        }
        /*System.out.println(listOfMax);*/

    }

    void setString(String str) {
        this.str = str;
    }

}
