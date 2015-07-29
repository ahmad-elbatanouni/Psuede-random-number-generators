package Randomization.Tests;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadA {

    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.join();
        b.start();
        synchronized(b) {
            System.out.println("waiting");
            try {
                b.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadA.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(b.tot);
        }
    }
}


class ThreadB extends Thread {
    int tot;
    public void run() {
        synchronized(this) {
            for (int i = 0; i < 10; i++) {
                tot += i;
            }
            notify();
        }
    }
}