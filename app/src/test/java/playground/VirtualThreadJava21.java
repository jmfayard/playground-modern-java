package playground;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/// [JEP 444: Virtual Threads](https://openjdk.org/jeps/444)
public class VirtualThreadJava21 {

    /// See [JEP 467: Markdown Documentation Comments](https://openjdk.org/jeps/467)
    public void javaDocInMarkdown() {

    }

    public static void main(String[] args) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.rangeClosed(1, 10_000).forEach(i -> {
                executor.submit(() -> {
                    System.out.println(i);
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }
}
