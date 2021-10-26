package pri.kevan.lock.producerconsumerdemo;

/**
 * @author qiwan.liu
 * @date 2021/10/26 15:54
 */
public class Test {
    public static void main(String[] args) {
        BreadContainer container = new BreadContainer();

        Producer producer = new Producer(container);

        Consumer consumer = new Consumer(container);

        for (int i = 1; i <= 10; i++) {
            new Thread(producer, "producer" + i).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(consumer, "consumer" + i).start();
        }

    }
}
