package modernjava;

@FunctionalInterface
interface CollectionPrinter<T> {
    String map(T mapper);

    default boolean supportsUtf8() {
        return true;
    }
}

public class DefaultMethods {
    CollectionPrinter<Integer> mapper = (nb -> "Got " + (nb * 3));
}
