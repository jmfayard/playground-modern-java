package algo;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MajorityElementTest {

    static <T> T majorityElement(List<T> elements) {
        var map = new HashMap<T, Integer>();
        elements.forEach(e -> map.put(e, 1 + map.getOrDefault(e, -1)));
        Map.Entry<T, Integer> maxEntry = map.entrySet().stream().max(
                Comparator.comparing(Map.Entry::getValue)
        ).orElseThrow();

        return maxEntry.getKey();
    }

    @Test
    void testMajorityElement() {
        var exemple = List.of(2, 2, 1, 1, 2, 2);
        assertThat(majorityElement(exemple)).isEqualTo(2);
    }
}

