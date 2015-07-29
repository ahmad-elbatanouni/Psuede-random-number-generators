package Randomization.Tests;

public class Joint implements Runnable {

    public void run() {
        
        for (int x = 1; x <= 10; x++) {
            System.out.println("this is thread " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws Exception {
        Joint j1 = new Joint();
        Thread t1 = new Thread(j1, "1");
        Thread t2 = new Thread(j1, "2");
        Thread t3 = new Thread(j1, "3");
        t1.start();
        t2.start(); 
        t3.start();
        
    }
}
