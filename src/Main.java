import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        ExecutorService exServ = Executors.newFixedThreadPool(3);
        exServ.execute(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 1_000_000; i += 2) {
                    sum += i;
                }
                System.out.println("Сумма чисел, которые делятся на 2: " + sum);
            }
        });
        exServ.execute(new Runnable() {
            @Override
            public void run() {
                long sum = 0;
                for (int i = 0; i < 1_000_000; i += 7) {
                    sum += i;
                }
                System.out.println("Сумма чисел, которые делятся на 7: " + sum);
            }
        });
        exServ.execute(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                int size = 1000;
                int upperBound = 1000;
                int[] array = new int[size];
                Random random = new Random();
                IntStream.range(0, size)
                        .forEach(index -> array[index] = random.nextInt(upperBound));
                for (int i : array) {
                    if (i % 2 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Сумма четных чисел: " + sum);

            }
        });
        exServ.shutdown();
    }
}