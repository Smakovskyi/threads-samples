/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author sds
 */
public class Atomicity extends Thread {
    static volatile int i;
    static Object lock = new Object();
    boolean b;

    public void run() {
        while (true) {
                if (b = !b) {
                	
               	 synchronized(lock) {	
                        i++;
               	 }
                } else {
                  
                 synchronized(lock){
                		i--;
                  }  	
                }
            
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Atomicity().start();
        new Atomicity().start();


        while (true){
            System.out.println(i);
            Thread.sleep(500);
        }
    }
}