package algo;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {
    @Test
    void findSumOfAllEvenNumbers() {
        var integers = List.of(2, 4, 5);
        var expected = 6;
        var actual = integers.stream()
                .filter(nb -> nb % 2 == 0)
                .reduce(0, Integer::sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findStringsWithLengthGreaterThanFive() {
        var sentence = "Tu no tienes la culpa mi amor que el mondo sea tan feo";
        var expected = List.of("tienes", "culpa", "mondo");

        var actual = Arrays.stream(sentence.split(" "))
                .filter(word -> word.length() >= 5)
                .toList();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void computeSquares() {
        var integers = List.of(2, 4, 5);
        var expected = List.of(4, 16, 25);
        var actual = integers.stream()
                .map(nb -> nb * nb)
                .toList();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findMax() {
        var integers = List.of(2, 14, 5);
        var expected = Optional.of(14);
        var actual = integers.stream()
                .max(Comparator.naturalOrder());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findMin() {
        var integers = List.of(2, 14, 5);
        var expected = Optional.of(2);
        Comparator<Integer> max = Comparator.naturalOrder();
        var actual = integers.stream()
                .max(max.reversed());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void concatanateAllTheStrings() {
        var strings = List.of("Alle", "Freunde", "werden", "Brüder");
        var expected = "Alle Freunde werden Brüder";
        var actualOne = strings.stream()
                .reduce("", (a, b) -> a + " " + b)
                .replaceFirst(" ", "");
        var actualTwo = strings.stream().collect(Collectors.joining(" "));
        var actualThree = String.join(" ", strings);

        assertThat(expected)
                .isEqualTo(actualOne)
                .isEqualTo(actualTwo)
                .isEqualTo(actualThree);

    }


    @Test
    void stringsUppercaseTrimAndSorted() {
        var strings = List.of("ok", "brüder werden ", "  alle ", "why  ");
        var expected = List.of("ALLE", "BRÜDER WERDEN", "OK", "WHY");
        var actual = strings.stream()
                .map(String::toUpperCase)
                .map(String::trim)
                .sorted()
                .toList();

        assertThat(actual)
                .isEqualTo(expected);

    }

    @Test
    void averageOfDoubles() {
        var doubles = List.of(2.4, 3.1, 3.7);
        var expected = 3.06;
        var actual = doubles.stream()
                .collect(Collectors.averagingDouble(nb -> nb));

        assertThat(actual).isCloseTo(expected, Offset.offset(0.01));
    }


    @Test
    void removeDuplicateWords() {
        var strings = "les poules du couvent couvent";
        var expected = List.of("les", "poules", "du", "couvent");
        var actual = Arrays.stream(strings.split(" "))
                .distinct()
                .toList();

        assertThat(actual)
                .isEqualTo(expected);

    }

    @Test
    void checkAllIntsGreaterThanTwo() {
        var integers = List.of(2, 4, 5);
        var expected = true;
        var actual = integers.stream()
                .allMatch(nb -> nb >= 2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkForX() {
        var strings = "Ich benutze X.com natürlicht nicht";
        var expected = Optional.of("X.com");
        var actual = Arrays.stream(strings.split(" "))
                .filter(s -> s.toUpperCase().charAt(0) == 'X')
                .findFirst();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findTheLongestString() {
        var strings = "Ich benutze X.com natürlicht nicht";
        var expected = Optional.of("natürlicht");
        var actual = Arrays.stream(strings.split(" "))
                .max(Comparator.comparingInt(String::length));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void removeNullValues() {
        var nullableInts = Stream.of(1, 3, null, 2, null);
        var expected = List.of(1, 3, 2);
        var actual = nullableInts
                .filter(Objects::nonNull)
                .toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findSecondSmallestElement() {
        var integers = List.of(5, 2, 4, 5);
        var expected = Optional.of(4);
        var sorted = integers.stream()
                .sorted()
                .toList();
        var actual = (sorted.size() >= 2) ? Optional.of(sorted.get(1)) : Optional.empty();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findIntersectinoOfTwoLists() {
        var first = List.of(5, 2, 4, 5);
        var second = Set.of(0, 5, 4, 8);
        var actual = List.of(4, 5);
        List<Integer> intersection = first.stream()
                .sorted()
                .distinct()
                .filter(second::contains)
                .toList();

        assertThat(intersection).isEqualTo(actual);
    }


    @Test void findStringsContainingA() {
        var multiList = List.of(
                List.of("Maybe", "I", "didn't", "treat", "you"),
                List.of("quite", "as", "good", "as", "I", "should", "have")
        );
        var expected = List.of("maybe", "treat", "as", "as", "have");

        var found = multiList.stream()
                .flatMap(Collection::stream)
                .map(String::toLowerCase)
                .filter(s -> s.contains("a"))
                .toList();

        assertThat(found).isEqualTo(expected);
    }

    static record Employee(String name, String department, int salary) {

    }


    @Test
    void groupByDepartmentAndFindMySalary() {
        var alice = new Employee("Alice", "Tech", 42000);
        var bob = new Employee("Bob", "Tech", 50000);
        var charlie = new Employee("Charlie", "Sales", 60000);
        var employees = List.of(alice, bob, charlie);

        var deparments = employees.stream()
                .map(employee -> employee.department)
                .sorted()
                .distinct()
                .toList();
        assertThat(deparments).isEqualTo(List.of("Sales", "Tech"));

        var maxSalary = deparments.stream()
                .map(dep -> employees.stream()
                        .filter(e -> Objects.equals(e.department, dep))
                        .max(Comparator.comparingInt(e -> e.salary)).orElseThrow())
                .toList();
        assertThat(maxSalary).isEqualTo(List.of(charlie, bob));
    }
}
