package ru.spbau.kirilenko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A class with second part of streams homework
 */
public final class SecondPartTasks {

    private SecondPartTasks() {}

    /**
     * Найти строки из переданных файлов, в которых встречается указанная подстрока.
     */
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths
                .stream()
                .flatMap(path -> {
                    try {
                        return Files.lines(Paths.get(path));
                    } catch (IOException e) {
                        return Stream.empty();
                    }
                })
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }

    /**
     * В квадрат с длиной стороны 1 вписана мишень.
     * Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
     * Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
     */
    public static double piDividedBy4() {
        Random random = new Random();
        return IntStream
                .generate( () -> Double.compare(Math.pow(random.nextDouble() - 0.5, 2.0) + Math.pow(random.nextDouble() - 0.5, 2.0), 0.25) != 1 ? 1 : 0)
                .limit(1000000)
                .average()
                .orElse(0);
    }

    /**
     * Дано отображение из имени автора в список с содержанием его произведений.
     * Надо вычислить, чья общая длина произведений наибольшая.
     */
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .entrySet()
                .stream()
                .max(Comparator.comparing(element -> element.getValue()
                        .stream()
                        .reduce("", (a, b) -> a + b).length()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
     * Необходимо вычислить, какой товар и в каком количестве надо поставить.
     */
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders
                .stream()
                .flatMap(el -> el.entrySet().stream())
                .collect(Collectors
                        .groupingBy(Map.Entry::getKey,
                                Collectors
                                        .summingInt(Map.Entry::getValue)));
    }
}