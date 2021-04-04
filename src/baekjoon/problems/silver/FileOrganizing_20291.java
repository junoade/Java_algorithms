package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FileOrganizing_20291 {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            String[] files = new String[N];

            for(int i=0; i<N; i++) {
                files[i]=br.readLine();
            }
            checkFileExtension(files);
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static void checkFileExtension(String[] str){
        int N=str.length;
        /*ArrayList<String> arr = new ArrayList<>();*/
        Map<String, Integer> map = new TreeMap<>();
        // TreeMap은 keySet()으로 가져올때, 정렬된 상태로 가져옴.
        // HashMap은 hash값 기준으로..

        String temp;

        for(int i=0; i<N; i++){
            temp=str[i].substring(str[i].indexOf(".")+1);
            if(i==0){
                /*arr.add(temp);*/
                map.put(temp,1);
            }else{
                if(!map.containsKey(temp)) { // 중복되지 않을 때
                    /*arr.add(temp);*/
                    map.put(temp,1);
                }else{ //이미 있으면 key값에 해당하는 temp의 value값 ++1
                    map.put(temp,map.get(temp)+1);
                }
            }
        }
        /*System.out.println(arr);
        System.out.println(map);*/
        /*Map의 key값 사전순 정렬*/
        Iterator<String> iter = map.keySet().iterator(); // 오름차순 정렬
        String k;
        int v;
        while(iter.hasNext()){
            k=(String)iter.next();
            v=map.get(k);
            System.out.println(k+" "+v);
        }

    }
    /*static void printExtensions(String[] str){
        for(String i: str)
            System.out.println(i);
    }*/

}
