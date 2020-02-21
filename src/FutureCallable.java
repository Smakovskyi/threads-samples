
import java.util.concurrent.ExecutorService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sds
 */
public class FutureCallable {
    public static void main(String[] args) throws Exception {
        int nThreads = 10; 
        
    	ExecutorService ex = Executors.newFixedThreadPool(nThreads );
                
    	List<Future<Long>> results = new ArrayList<>();
    	int array[] = new int [100500];
    	Arrays.fill(array, 1);
        for (int i = 0; i < 10; i++) {
        	 final int index = i;
              Future<Long> future = ex.submit(
            		  new Callable<Long>() {

						@Override
						public Long call() throws Exception {
							long sum = 0L; 
	            			for(int ii=index*array.length/10;ii<(index+1)*array.length/10;ii++ ) {
	            				sum+=array[ii];
	            			
	            			}
							return sum;
						}
					}
            	);
            results.add(future);
           
        }
        
        for(Future<Long> future: results){
         
        	System.out.println(future.get());
        	//System.out.println();	
        	
        }
        ex.shutdown();
    }
    private static class Factorial extends Thread {
     
        private static long calcFactorial (int n) throws InterruptedException {
            long result = 1L;
            for (int i = 2; i <= n; i++) {
                result *= i;
                Thread.sleep(10);
            }
            return result;
        }
    }
}
