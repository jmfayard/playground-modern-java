package modernjava;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RecordJava14 {
    record Employee (String name, int age, String department) {
    }

    @Test
    void createRecord() {
        var patrik = new Employee("Patrick", 36, "Marketing");
        assertThat(patrik.toString()).isEqualTo("Employee[name=Patrick, age=36, department=Marketing]");
        assertThat(patrik.name()).isEqualTo("Patrick");

        var clone = new Employee("Patrick", 36, "Marketing");
        assertThat(clone).isEqualTo(patrik);
    }

}
