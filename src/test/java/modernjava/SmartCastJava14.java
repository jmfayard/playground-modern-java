package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SmartCastJava14 {

    Object obj = new String("I'm a string");

    @Test
    void smartCast() {
        if (obj instanceof String mystring) {
            assertThat(((String) obj).length()).isEqualTo(12);
            assertThat(mystring.length()).isEqualTo(12);
        }
    }
}
