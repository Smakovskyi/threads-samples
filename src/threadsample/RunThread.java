/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package threadsample;

import java.util.concurrent.Callable;


/**
 *
 * @author Admin
 */
class MyRunThread extends Thread{

    volatile private int iterations;

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public MyRunThread(int iterations) {
        this.iterations = iterations;
    }


    public void run(){
                for(int i=0; i< iterations; i++){
                    System.out.println("Hello from MyThread " +  getName());
                    System.out.println("Hello from MyThread " + Thread.currentThread().getName());
                    
                    
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ex){
                        return;
                    }
                }
                
            }
}



public class RunThread extends Thread {

	@Override
	public void run() {
		System.out.println("Hello from main thread - RunThread "+Thread.currentThread().getName());
		
	}

	public static void main(String args[]) throws InterruptedException{
        MyRunThread myThread = new MyRunThread(5);
        myThread.setName("SUPER THREAD");
        myThread.setIterations(10);
        myThread.run();
        myThread.start();
        
        
        myThread.join();
    }

}
