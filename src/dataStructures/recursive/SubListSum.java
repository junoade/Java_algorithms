package dataStructures.recursive;

import java.util.ArrayList;

public class SubListSum {
    int recursiveSum(ArrayList<Integer> list){
        /* base case */
        if(list.size() <= 0){
            return 0;
        }
        return list.get(0) + recursiveSum(new ArrayList<>(list.subList(1, list.size())));
    }
}
