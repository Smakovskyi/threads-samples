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
    //static volatile ReentrantLock lock = new ReentrantLock();
    static Object lock = new Object();
    boolean b;

    public void run() {
        while (true) {
                if (b = !b) {
                	
               	 synchronized(lock) {	
                        i++;
               	 }
//                    }finally{
//                    	lock.unlock();
//                    }
                } else {
                  
//                     try{
//                    	 lock.lock();
                 synchronized(lock){
                		i--;
                  }  	
//                     }finally{
//                    	lock.unlock(); 
//                     }
                  //}
                }
            
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Atomicity().start();
        new Atomicity().start();
       // new Atomicity().start();

        while (true){
         
        	int val;
//        	synchronized (lock) {
//        		val = i;
//			}
            System.out.println(i);
            Thread.sleep(500);
        }
    }
}