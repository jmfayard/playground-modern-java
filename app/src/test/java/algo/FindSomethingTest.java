package algo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FindSomethingTest {
    @Test
    void findByValue() {
        Map<String, Integer> input = Map.of("fr", 42, "en", 12, "de", 42, "it", 1);
        var search = 42;
        var expected = Set.of("fr", "de");
        var actual = input.entrySet().stream()
                .filter(e -> e.getValue() == search)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findDuplicateTest() {
        var inputs = List.of("ABC", "ABCA", "BCACB", "ABCAXB");
        var expected = List.of(Set.of(), Set.of('A'), Set.of('C', 'B'), Set.of('A', 'B'));
        for (int i = 0; i < inputs.size(); i++) {
            assertThat(findDuplicate(inputs.get(i)))
                    .describedAs("For input="+inputs.get(i))
                    .isEqualTo(expected.get(i));
        }
    }

    private Set<Character> findDuplicate(@NotNull String input) {
        var alreadyFound = new HashSet<Character>();
        var duplicates = new HashSet<Character>();
        input.chars().forEach(c -> {
            if (alreadyFound.contains((char) c)) {
                duplicates.add((char) c);
            } else {
                alreadyFound.add((char) c);
            }
        });
        System.out.println(alreadyFound);
        return duplicates;
    }


    @Test
    void isPalindromeTest() {
        var inputs = List.of("AABBAA", "KAYAK", "HELLO");
        var expected = List.of(true, true, false);
        for (int i = 0; i < inputs.size(); i++) {
            assertThat(isPalindrome(inputs.get(i)))
                    .describedAs("For input="+inputs.get(i))
                    .isEqualTo(expected.get(i));
        }
    }

    boolean isPalindrome(String input) {
        var chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    void orderingOfParenthesisTest() {
        var inputs = List.of("(AB)", "A(AB(C))", "A{aa}{b}", "(AA(", "(()");
        var expected = List.of(true, true, true, false, false);
        for (int i = 0; i < inputs.size(); i++) {
            assertThat(orderingOfParenthesis(inputs.get(i)))
                    .describedAs("For input="+inputs.get(i))
                    .isEqualTo(expected.get(i));
        }
    }

    boolean orderingOfParenthesis(String input) {
        var stack = new Stack<Character>();
        var chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            var c = chars[i];
            switch (c) {
                case '(', '{' -> stack.push(c);
                case ')', '}' -> {
                    var expected = c == ')' ? '(' : '{';
                    if (stack.empty() || stack.pop() != expected) return false;
                }
            }
        }
        return stack.empty();
    }

    record Fibo(Integer previous, Integer current) {
        static Fibo seed = new Fibo(1, 1);
        Fibo next() {
            return new Fibo(current, previous + current);
        }
    }

    Stream<Integer> fibonacci() {
        return Stream.iterate(Fibo.seed, Fibo::next)
                .map(fibo -> fibo.previous);
    }

    @Test
    void fiboTest() {
        var expected = List.of(1, 1, 2, 3, 5, 8, 13);
        var actual = fibonacci().limit(7).toList();
        assertThat(actual).isEqualTo(expected);
    }
}
