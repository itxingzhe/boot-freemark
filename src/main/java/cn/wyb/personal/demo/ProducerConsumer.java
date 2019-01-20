package cn.wyb.personal.demo;

import java.util.LinkedList;

public class ProducerConsumer<T> {

    private final LinkedList<T> linkedList = new LinkedList<>();
    private final long          MAX        = 10;
    private int                 count      = 0;

    public synchronized void put(T t) {
        while (linkedList.size() >= MAX) {
            try {
                System.out.println("库存满了，等会生产。");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        count++;
        this.notifyAll();
    }

    public synchronized T get() {
        while (linkedList.size() == 0) {
            try {
                System.out.println("没有货了，等会再取。");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = linkedList.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        ProducerConsumer<String> pc = new ProducerConsumer<>();

        for (int i = 0; i < 5; i++) {
            new Thread("consumer-" + i) {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(pc.linkedList);
                        System.out.println(Thread.currentThread().getName() + "get:" + pc.get());
                    }
                }
            }.start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread("producer-" + i) {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        pc.put(Thread.currentThread().getName() + "-" + j);
                    }
                }
            }.start();
        }
    }

}