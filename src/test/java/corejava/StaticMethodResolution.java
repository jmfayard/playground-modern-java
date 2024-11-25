package corejava;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StaticMethodResolution {
    @Test
    void staticMethodResolution() {
        A a = new B();
        assertThat(a.hello()).isEqualTo(A.hello());
    }
    static class A {
        static String hello() {
            return "hello";
        }
    }
    static class B extends A{
        static String hello() {
            return "bonjour";
        }
    }

}
