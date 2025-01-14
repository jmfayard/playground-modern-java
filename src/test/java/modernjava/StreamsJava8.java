package modernjava;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.signum;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamsJava8 {

    @Test
    void map() {
        List<Integer> squares = Stream.of(1, 2, 4)
                .map(integer -> integer * integer)
                .toList();
        assertThat(squares).containsExactly(1, 4, 16);
    }

    @Test
    void joining() {
        Stream<String> words = Stream.of("un", "deux", "trois");
        String joined = words
                .collect(Collectors.joining("', '", "['", "']"));
        assertThat(joined).isEqualTo("['un', 'deux', 'trois']");
    }

    @Test
    void sortedComparing() {

        var oldest1 = persons().sorted(Comparator.comparing(Person::age))
                .findFirst().get();
        var oldest2 = persons().sorted(Comparator.comparingInt(Person::age))
                .findFirst().get();
        var oldest3 = persons()
                .sorted((a, b) -> signum(a.age-b.age))
                .findFirst().get();
        assertThat(oldest1.name).isEqualTo("MC");
        assertThat(oldest2.name).isEqualTo("MC");
        assertThat(oldest3.name).isEqualTo("MC");
    }

    record Person(int age, String name) {

    }
    Stream<Person> persons() {
        return Stream.of(
                new Person(42, "JM"),
                new Person(40, "patrick"),
                new Person(35, "MC")
        );
    }
}
