/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sds
 */
class Saver{
    private volatile int value;
    public volatile boolean firstDone;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}

class ThreadGenerator extends Thread {
   Saver saver; 
    
    public ThreadGenerator(Saver saver){
        this.saver = saver;
    }
    
    @Override
    public void run(){
        Random r = new Random();
        while(!saver.firstDone){
                Thread.yield();
            }
        
        synchronized(saver){
        for( int i=0; i< 10; i++){
                int side = r.nextInt(10);
           
                
                System.out.println("Side = "+ side);
                saver.setValue(side);
                saver.notify();

                try {
                    saver.wait();
                } catch (InterruptedException ex) {
                    return ;
                }
            }
        }
    }
}



class ThreadCalculator extends Thread{
    Saver saver;

    public ThreadCalculator(Saver saver) {
        this.saver = saver;
    }
    
    public void run(){//saver.firstDone=true;
        
        synchronized(saver){
            saver.firstDone=true;
            
            for(int i=0; i<10;i++){
            
                
                try {
                    
                    saver.wait();
                } catch (InterruptedException ex) {
                    return ;
                }
                int side = saver.getValue();
                System.out.println("Square = "+ side*side);
                saver.notify();
            }
        }
    
    }
    
}


public class SampleWaitNotify {
    public static void main(String args[]) throws InterruptedException{
       // System.out.println("TAK");
        Saver s = new Saver();
        ThreadGenerator generator = new ThreadGenerator(s);
        ThreadCalculator calculator = new ThreadCalculator(s);
        calculator.start();
        generator.start();
       
        
    }
    
}
