package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsJava9 {
    @Test
    void listof() {
        List<Integer> integers = List.of(2, 4, 6);
        assertThat(integers).containsExactly(2, 4, 6);
    }

    @Test
    void setOf() {
        Set<Integer> integers = Set.of(2, 6);
        assertThat(integers).containsExactlyInAnyOrder(2, 6);
    }

    @Test
    void mapof() {
        Map<String, String> countries = Map.of("FR", "France", "DE", "Germany");
        assertThat(countries).containsEntry("FR", "France");
        assertThat(countries).containsEntry("DE", "Germany");
    }
}
