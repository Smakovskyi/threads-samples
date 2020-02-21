/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sds
 */
public class ThreadPriority extends Thread {
    public void run(){
        while(!isInterrupted()){
            System.out.println("1");
         //   Thread.yield();
        }
    }
    
    
    public static void main(String args[]){
        final Thread thrd =  new ThreadPriority();
        thrd.setPriority(MIN_PRIORITY);
        
        thrd.start();
        
        new Thread(){
            @Override
            public void run(){
        //        setPriority(MAX_PRIORITY);
                for(int i=0; i<20000; i++){
                    System.out.println("2");
           //         Thread.yield();
                }
                thrd.interrupt();
            }
        }.start();
        try {
            thrd.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPriority.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("main is finished");
    }
}
