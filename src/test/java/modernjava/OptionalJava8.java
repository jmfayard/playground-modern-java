package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalJava8 {

    @Test
    public void optionals() {
        Optional<Integer> optional = Optional.of(7);
        optional.map(integer -> "Found number " + integer)
                .ifPresentOrElse(
                        s -> assertThat(s).isEqualTo("Found number 7"),
                        () -> fail("Was expecting an optional, got null")
                );
        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.isEmpty()).isFalse();
    }
}
