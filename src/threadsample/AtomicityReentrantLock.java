package threadsample;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class AtomicityReentrantLock extends Thread {
	
		static volatile int i;
		ReentrantLock lock = new ReentrantLock();
	    boolean b;

	    public void run() {
	        while (true) {
	                if (b = !b) {
	                	
	                	 lock.lock();
	                     try {
	                	 	i++;
	                     }finally {
	                    	 lock.unlock();
	                     }
	                } else {
	                  
	                	lock.lock();
	                		i--;
	                	lock.unlock();	

	                }
	            
	        }
	    }

	    public static void main(String[] args) throws InterruptedException {
	        new Atomicity().start();
	        new Atomicity().start();

	        List<String> list = Collections.synchronizedList( new ArrayList<>());
	        OptionalDouble avg = IntStream.range(0, 100500).parallel().map( i -> 2*i).average();
	        System.out.println(avg);
	        
	        while (true){
	         
	            System.out.println(i);
	            Thread.sleep(500);
	        }
	        
	    }
}

