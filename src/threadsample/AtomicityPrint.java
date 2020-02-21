/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author sds
 */
class Incrementor extends Thread {
    @Override
    public void run(){
        for( int i = 0; i<10000000; i++){
          synchronized(AtomicityPrint.lockForValue){
                           AtomicityPrint.value ++;
          }
        
        }
    }
}
public class AtomicityPrint {
    
    static volatile int value;
    //static volatile AtomicInteger value = new AtomicInteger();
    static volatile Object lockForValue = new Object();
   // static ReentrantLock re = new ReentrantLock();
    
    public static void main( String args[]) throws InterruptedException{
     //   lock.notify();
        new Incrementor().start();
       // new Incrementor().start();
        while (value<10000000){
            //int temp ; 
         if( value%2==0){
           synchronized( lockForValue ){
//            
//           // re.lock();
             if(value%2==0){
                 System.out.println(/*"value="+*/value);
             }
             
           }
          
         }else{
            Thread.yield();
         }
        }
    }

}
