package AotimaticDemo;

import java.util.concurrent.atomic.AtomicLong;

/*
* Automic利用了CAS原理，作为一条CPU指令，其是线程安全的。
* */
class Test {
    private static AtomicLong count = new AtomicLong(0);
    public void add10k() {
        for (int i = 0;i < 10000;i++) {
            count.getAndIncrement();
        }
    }
    public long getCount() {
        return count.get();
    }
}

public class TestAtomicDemo {
    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.add10k();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.add10k();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.getCount());
    }
}
