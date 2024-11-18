package katas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Merge Arrays
 * https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MergeArrayTest {
    public List<Integer> mergeSortedArrays(List<Integer> array1, List<Integer> array2) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(true) {
            if (i >= array1.size()) {
                result.addAll(array2.subList(j, array2.size()));
                return result;
            }
            if (j >= array2.size()) {
                result.addAll(array1.subList(i, array1.size()));
                return result;
            }
            if (array1.get(i) < array2.get(j)) {
                result.add(array1.get(i));
                i++;
            } else {
                result.add(array2.get(j));
                j++;
            }
        }
    }

    @Test void mergeArrays() {
        var array1 = List.of(1, 1, 3, 6, 6);
        var array2 = List.of(0, 2, 2, 5);
        var expected = List.of(0, 1, 1, 2, 2, 3, 5, 6, 6);
        assertThat(mergeSortedArrays(array1, array2)).isEqualTo(expected);
    }

    @Test void mergeEmptyArrays() {
        var array = List.of(0, 2, 2, 5);
        assertThat(mergeSortedArrays(array, new ArrayList<>())).isEqualTo(array);
        assertThat(mergeSortedArrays(new ArrayList<>(), array)).isEqualTo(array);
    }

}
