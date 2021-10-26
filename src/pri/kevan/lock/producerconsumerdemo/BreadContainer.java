package pri.kevan.lock.producerconsumerdemo;

import java.util.LinkedList;

/**
 * 面包容器
 * @author qiwan.liu
 * @date 2021/10/26 15:27
 */
public class BreadContainer {

    private LinkedList<Bread> queue = new LinkedList<>();
    /**
     * 容量
     */
    public static final int CAPACITY = 10;

    /**
     * 放入面包
     * @param bread 面包
     */
    public synchronized void put(Bread bread) {
        // 这里不用if而是用while的原因是如果有多个生产者线程同时被唤醒时，一次性就put了多个面包，超过了指定容量，if条件就永远都是false了，就会一直生产，导致OOM
        while (queue.size() == CAPACITY) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列已满，等待消费面包");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 放入面包");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.add(bread);
        System.out.println(Thread.currentThread().getName() + " 生产一个面包后总共有：" + queue.size());
        // 面包生产成功后通知消费者线程
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " product a bread：" + bread.toString());
    }

    /**
     * 拿出面包
     * @return 面包
     */
    public synchronized Bread take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列为空，等待生产面包");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 拿出面包");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bread bread = queue.remove();
        System.out.println(Thread.currentThread().getName() + " 消费一个面包后还剩：" + queue.size());
        // 消费成功后通知生产者线程
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " consume a bread：" + bread.toString());
        return bread;
    }


}
