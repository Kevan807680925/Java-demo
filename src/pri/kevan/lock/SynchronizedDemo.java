package pri.kevan.lock;

public class SynchronizedDemo {

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        new MyThread1(demo).start();
        new MyThread2(demo).start();
    }


    public synchronized void f1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I'm f1 method");
    }

    public synchronized void f2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I'm f2 method");
    }
}

class MyThread1 extends Thread {
    private SynchronizedDemo demo;

    public MyThread1(SynchronizedDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.f1();
    }
}

class MyThread2 extends Thread {
    private SynchronizedDemo demo;

    public MyThread2(SynchronizedDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.f2();
    }
}
