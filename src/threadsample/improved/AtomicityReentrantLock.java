package threadsample.improved;
import threadsample.Atomicity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class AtomicityReentrantLock extends Thread {
	
		static volatile int i;



	    public void run() {
			boolean b=false;
			final LockDecorator lock = new LockDecorator();
			while (true) {
	                if (b = !b) {

	                     try(LockDecorator l = lock.lock()) {
	                	 	i++;
	                     } catch (Exception e) {
							 e.printStackTrace();
						 }
					} else {

						try(LockDecorator l = lock.lock()) {
							i--;
						} catch (Exception e) {
							e.printStackTrace();
						}

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

