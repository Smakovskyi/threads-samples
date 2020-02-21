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
class MySimpleThread extends Thread{

    public MySimpleThread(Printer printer) {
        this.printer = printer;
    }
    
    Printer printer;
    @Override
    public void run(){
        synchronized(System.out){
            for( int i = 0; i< 10000; i+=2){
               // System.out.println(i);
                synchronized(printer){
                printer.print(i);
                }
                Thread.yield();
           // try {
           //     Thread.sleep(100);
           // } catch (InterruptedException ex) {
           //     return ;
           // }
                if( this.isInterrupted() ){
                    return;
                }
            }
        }
    }
}

class Printer{
    synchronized public void print(int i){
        System.out.println(i);
    }
}

public class SipmleThreads {
    public static void main(String args[]) throws InterruptedException{
        final Printer printer = new Printer();
        Thread th = new MySimpleThread(printer);
        
        
        final Thread my = new Thread( new Runnable(){

            @Override
            public void run() {
              synchronized (printer){
                for( int i = 1; i< 10000; i+=2){
                    synchronized(System.out){
                    System.out.println(i);
                    }
                     Thread.yield();
 //                    if( my.isInterrupted() ){
 //                       return;
 //                       }
                }
              }
            }
        });
       // th.setPriority(Thread.MIN_PRIORITY);
       // th.setDaemon(true);
       // my.setDaemon(true);
        th.start();
       // my.setPriority(Thread.MAX_PRIORITY);
        my.start();
        
        
        my.join();
        th.join();
        System.out.println("thread are finished!");
        th.interrupt();
         
    }
}
