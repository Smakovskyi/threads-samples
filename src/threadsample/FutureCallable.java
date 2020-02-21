package threadsample;

//package concurency.samples.collections;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Tetiana_Romaniv on 10/6/2016.
 */
public class FutureCallable {
	

	
    public static void main(String[] args) throws Exception {

    	int nThreads = 10; 
    	ExecutorService ex = Executors.newFixedThreadPool(nThreads);
    	
    	List<Future<Long>> results = new ArrayList<>();
    	int arr[] = new int [100500];
        for (int i = 0; i < 10; i++) {
            final int start = arr.length *i /10;
            final int end = arr.length*(i+1)/10;
            Arrays.fill(arr, 1);
            
            Future<Long> future = ex.submit( 
            	
            		() -> {
            				Long res = 0L;
            				for(int ii= start; ii< end; ii++) {
            					res+=arr[ii];
            				}
            				return res;
            			}
            		);
            	
            		
            results.add(future);
           
        }
        
        for(Future<Long> future: results){
        	Long result = future.get();
        	System.out.println("Результат:" + result);
        	
        	
        }
        ex.shutdown();
        
    }
    
}



