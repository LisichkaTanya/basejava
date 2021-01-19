package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int result1 = minValue(new int[]{1, 2, 3, 3, 2, 3});
        System.out.println(result1);

        int result2 = minValue(new int[]{9, 8});
        System.out.println(result2);

        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);

        System.out.println(oddOrEven(list1));
    }

    /*
     * реализовать метод через стрим int minValue(int[] values).
     * Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
     * составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
     * Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */
    static int minValue(int[] values) {
        Set<Integer> set = Arrays.stream(values).boxed().collect(Collectors.toSet());
        double result = 0;
        int power = set.size();
        for (Integer integer : set) {
            power--;
            result += integer * (Math.pow(10, power));
        }
        return (int) result;
    }

    /*
     * Реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная
     *  - удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
     *  Optional - решение в один стрим.
     */
    static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce(Integer::sum).get() % 2 == 1) {
            return integers.stream().filter(integer -> integer%2 != 1).collect(Collectors.toList());
        } else {
            return integers.stream().filter(integer -> integer%2 == 1).collect(Collectors.toList());
        }
    }
}
