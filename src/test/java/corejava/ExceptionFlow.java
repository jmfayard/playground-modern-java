package corejava;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExceptionFlow {
    static class MyCheckedException extends Exception {
        public MyCheckedException() {
            super("MyCheckedException has been thrown");
        }
    }

    static class MyUnCheckedException extends RuntimeException {
        public MyUnCheckedException() {
            super("MyUnCheckedException has been thrown");
        }
    }

    @Test
    void everythingExtendsThrowable() {
        var input = List.of(new RuntimeException(), new MyCheckedException(), new MyUnCheckedException());
        var expected = List.of(true, true, true);
        var actual = input.stream().map(e -> e instanceof Throwable).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void unchecked() {
        assertThrows(MyUnCheckedException.class, () -> {
            throw new MyUnCheckedException();
        });
    }


    @Test
    void checked() throws MyCheckedException {
        assertThrows(MyCheckedException.class, () -> {
            throw new MyCheckedException();
        });
    }

    @Test
    void testFinally() {
        var i = 0;
        try {
            i = 42;
            throw new MyUnCheckedException();
        } catch (Exception e) {
            i = 20;
        } finally {
            assertThat(i).isEqualTo(20);
        }
        // uncomenting the line below gives a compilation failure
        //assertThat(i).isEqualTo(42);
    }

    @Test
    void threads() {
        var collection = List.of(1, 2, 3);
        var multiThreadCollection = ImmutableList.copyOf(collection);
        Runnable command = () -> {
            System.out.println("start");
            multiThreadCollection.forEach(System.out::println);
            System.out.println("done");
        };
        var thread = new Thread(command);
        assertThat(thread.getState()).isEqualTo(Thread.State.NEW);
        thread.start();
        assertThat(thread.getState()).isEqualTo(Thread.State.RUNNABLE);

        Executors.newFixedThreadPool(3).execute(command);
        Executors.newCachedThreadPool().execute(command);
    }

    @Test
    void locks() {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        System.out.println("this very important block is locked to a single thread a time");
        lock.unlock();
        lock.unlock();
    }

}
