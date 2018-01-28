package com.choice.shiro.realms;

/**
 * Created by jack on 2017-12-19.
 */
public class NotifyTest {
    private  String[] flag = {"true"};

    class NotifyThread extends Thread{
        public NotifyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            try {
                System.out.println("begin sleep");
                sleep(3000);//推迟3秒钟通知sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (flag) {
                flag [0]= "false";
                flag.notifyAll();
                System.out.println("赋值flag");
            }
        }
    };

    class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            synchronized (flag){
                while (!flag[0].equals("false")) {
                    System.out.println(getName() + " begin waiting!");
                    long waitTime = System.currentTimeMillis();
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitTime = System.currentTimeMillis() - waitTime;
                    System.out.println("wait time :"+waitTime);
                }
            }
            System.out.println(getName() + " end waiting!");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread Run!");
        NotifyTest test = new NotifyTest();
        NotifyThread notifyThread =test.new NotifyThread("notify01");
        WaitThread waitThread01 = test.new WaitThread("waiter01");
        WaitThread waitThread02 = test.new WaitThread("waiter02");
        WaitThread waitThread03 = test.new WaitThread("waiter03");
        WaitThread waitThread04 = test.new WaitThread("waiter04");
        WaitThread waitThread05 = test.new WaitThread("waiter05");
        notifyThread.start();
        waitThread01.start();
        waitThread03.start();
        waitThread04.start();
        waitThread05.start();
        waitThread02.start();

    }
}
