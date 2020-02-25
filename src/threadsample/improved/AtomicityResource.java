package threadsample.improved;
import threadsample.Atomicity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class AtomicityResource extends Thread {

		static volatile int i;
		static Resource resource = new Resource();


	    public void run() {
			boolean b=false;
			final LockDecorator lock = new LockDecorator();
			while (true) {
	                if (b = !b) {
	                	resource.execute( () -> i++);

					} else {
	                	resource.execute(()->i--);

	                }
	            
	        }
	    }

	    public static void main(String[] args) throws InterruptedException {
	        new AtomicityResource().start();
	        new AtomicityResource().start();

	        while (true){
	         
	            System.out.println(i);
	            Thread.sleep(500);
	        }
	        
	    }
}

