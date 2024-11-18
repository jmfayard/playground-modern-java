package modernjava;

import static org.assertj.core.api.Assertions.*;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Lombok {

    @Test
    // https://projectlombok.org/features/val
    void valExample() {
        val map = new HashMap<String, Integer>();
        // COMPILE ERROR: Cannot assign a value to final variable 'map'
        //map = new HashMap<>();
        map.put("FR", 33);
        assertThat(map).containsEntry("FR", 33);
    }

    @Test
    void cleanup() throws IOException {
        val file = new File("src/test/resources/groceries.txt");
        @Cleanup InputStream in = new FileInputStream(file);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
        }
    }

    // https://projectlombok.org/features/GetterSetter
    // https://projectlombok.org/features/ToString
    @Getter @Setter @ToString
    static class GetterSetterExample {
        String name;
        Integer age;
    }

    @Test
    void getterSetters() {
        val example = new GetterSetterExample();
        example.setAge(42);
        example.setName("Patrick");

        assertThat(example.getAge()).isEqualTo(42);

        assertThat(example.toString()).isEqualTo("Lombok.GetterSetterExample(name=Patrick, age=42)");
    }

    // https://projectlombok.org/features/Data
    @Data
    @AllArgsConstructor
    public class DataExample {
        private final @NonNull String name;
        @Setter(AccessLevel.PACKAGE)
        private int age;
        private double score;
    }

    @Test
    void dataExample() {
        var example = new DataExample("Patrick", 42, 4.0);
        assertThat(example.getAge()).isEqualTo(42);
        assertThat(example.toString()).isEqualTo("Lombok.DataExample(name=Patrick, age=42, score=4.0)");
        assertThat(example).isEqualTo(new DataExample("Patrick", 42, 4.0));
    }


    // https://projectlombok.org/features/Builder
    @Builder
    public static class BuilderExample {
        @Builder.Default private long created = System.currentTimeMillis();
        private String name;
        private int age;
        @Singular private Set<String> occupations;
    }

    @Test
    void builderExample() {
        val example = BuilderExample.builder()
                .created(System.currentTimeMillis())
                .occupation("programmer")
                .occupation("designer")
                .age(42)
                .build();
        assertThat(example.occupations).containsExactlyInAnyOrder("programmer", "designer");
    }

    // https://projectlombok.org/features/With
    @AllArgsConstructor
    static public class WithExample {
        @With(AccessLevel.PROTECTED)
        @NonNull
        private final String name;
        @With
        private final int age;
    }

    @Test
    void withExample() {
        val example = new WithExample("Patrick", 42);
        val copy = example.withAge(24);
        assertThat(copy.age).isEqualTo(24);
    }

}
