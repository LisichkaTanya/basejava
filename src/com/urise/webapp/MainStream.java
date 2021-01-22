package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int result1 = minValue(new int[]{2, 1, 3, 3, 2, 3});
        System.out.println(result1);

        int result2 = minValue(new int[]{9, 8});
        System.out.println(result2);

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 7));
        System.out.println(oddOrEven(list1));
    }

    /*
     * реализовать метод через стрим int minValue(int[] values).
     * Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
     * составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
     * Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */
    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    /*
     * Реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная
     *  - удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
     *  Optional - решение в один стрим.
     */
    static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .reduce(0, Integer::sum);

        return integers.stream()
                .filter(x -> (x % 2) != (sum % 2))
                .collect(Collectors.toList());
    }
}
