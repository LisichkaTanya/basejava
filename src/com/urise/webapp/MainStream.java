package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int result1 = minValue(new int[]{2, 1, 3, 3, 2, 3});
        System.out.println(result1);

        int result2 = minValue(new int[]{9, 8});
        System.out.println(result2);

        List<Integer> list1 = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 7));
        System.out.println(oddOrEven(list1));
        System.out.println(oddOrEven(list2));

    }

    /*
     * реализовать метод через стрим int minValue(int[] values).
     * Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
     * составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
     * Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */
    static int minValue(int[] values) {
//        return Arrays.stream(values).boxed().collect(Collectors.toSet()).stream().reduce(0, (x, y) -> Integer.parseInt(x + "" + y));
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> Integer.parseInt(x + "" + y));
    }

    /*
     * Реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная
     *  - удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
     *  Optional - решение в один стрим.
     */
    static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().filter(x -> (x % 2) != ((integers.stream().reduce(0, Integer::sum)) % 2))
                .collect(Collectors.toList());
    }

    /*
    Еще один вариант решения через Map
     */
//    public static List<Integer> oddOrEven2(List<Integer> integers) {
//        Map<Boolean, List<Integer>> oddsAndEvens = integers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
//        return oddsAndEvens.get(false).size() % 2 == 0 ? oddsAndEvens.get(false) : oddsAndEvens.get(true);
//    }
}
