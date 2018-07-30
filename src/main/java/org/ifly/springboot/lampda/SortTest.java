package org.ifly.springboot.lampda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SortTest {
    public static void main(String[] args) {

//        List<Integer> list = Arrays.asList(10,20,30);
//
//        int[] arr = new int[list.size()];
//        list.toArray(arr);

        int[] arr= {10,20,30};

        System.out.println(IntStream.of(arr).parallel().min().getAsInt());
        System.out.println(IntStream.of(arr).parallel().max().getAsInt());



    }
}
