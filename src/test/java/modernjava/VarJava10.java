package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class VarJava10 {

    @Test
    void typeInference() {
        var map = Map.of("FR", "France", "DE", "Germany");
        var name = "Konrad";
        var list = List.of(2, 4, 6);
        assertThat(name).isInstanceOf(String.class);
        assertThat(list).isInstanceOf(List.class);
    }
}
