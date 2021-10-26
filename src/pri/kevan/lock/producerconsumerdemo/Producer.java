package pri.kevan.lock.producerconsumerdemo;

/**
 * 面包生产者
 * @author qiwan.liu
 * @date 2021/10/26 15:51
 */
public class Producer implements Runnable {

    private final BreadContainer container;

    public Producer(BreadContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " 开始生产面包");
            container.put(new Bread());
        }
    }
}
