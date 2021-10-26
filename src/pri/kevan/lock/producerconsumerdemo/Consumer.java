package pri.kevan.lock.producerconsumerdemo;

/**
 * 面包消费者
 * @author qiwan.liu
 * @date 2021/10/26 15:53
 */
public class Consumer implements Runnable {

    private final BreadContainer container;

    public Consumer(BreadContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            // 消费面包
            System.out.println(Thread.currentThread().getName() + " 开始消费面包");
            container.take();
        }
    }
}
