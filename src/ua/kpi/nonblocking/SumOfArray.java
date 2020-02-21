package ua.kpi.nonblocking;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SumOfArray {
	static int sum = 0;
	
	public static void main(String args[]) {
		int array[] = new int[100500];
		Arrays.fill(array, 1);
		
		IntStream.range(0, array.length)
				 .parallel()
				 .forEach( i -> { sum += array[i]; } );
		System.out.println(sum);
		AtomicInteger sumAtomic = new AtomicInteger(0);
		IntStream.range(0, array.length)
				 .parallel()
				 .forEach( i -> {
					 int value = sumAtomic.get();
					 while( !sumAtomic.compareAndSet(value, value + array[i]) ) {
						 value = sumAtomic.get();
					 }
				 } );
		System.out.println(sumAtomic.get());
	}

}
