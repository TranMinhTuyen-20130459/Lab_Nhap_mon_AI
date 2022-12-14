package mysudoku;

import java.util.*;

public final class Utils {
    private static final Random rand = new Random();

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new HashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static int random(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }

    public static int random(int bound) {
        return rand.nextInt(bound);
    }
}
