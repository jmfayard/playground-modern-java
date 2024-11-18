package modernjava;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class SwitchJava12 {

    private static Stream<Arguments> countries() {
        return Stream.of("DE", "FR")
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("countries")
    void newSwitchReturnsAValue(String country) {
        var countryName = switch (country) {
            case "DE" -> "Germany";
            case "FR" -> "France";
            default -> throw new IllegalStateException("Unexpected value: " + country);
        };
        assertThat(countryName).isIn("France", "Germany");
    }
}
