package modernjava;

import org.junit.jupiter.api.Test;

import java.util.List;

/// See [[Shape]]
public class SealedClassesTest {
    @Test
    void useSealedClasses() {
        List<Shape> shapes = List.of(new Circle(1.2), new Rectangle(2, 4), new Triangle(3.0, 4.0));
        shapes.forEach(System.out::println);
    }
}


