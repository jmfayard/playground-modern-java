package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MultilineStringsJava13 {

    @Test
    void multiline() {
        var greeting = """
                Hello
                How are you?
                """.stripIndent().trim();
        assertThat(greeting).isEqualTo("Hello\nHow are you?");
    }

    @Test
    void strip() {
        var abc = "  hello   ";
        assertThat(abc.strip()).isEqualTo("hello");
        assertThat(abc.stripTrailing().stripLeading()).isEqualTo("hello");
        
    }
}
