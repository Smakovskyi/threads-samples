package threadsample;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParallelStream {

	public static void main(String[] args) {
		int array[] = new int[100500];
		Arrays.fill(array, 1);
		int result = IntStream.of(array).parallel().map(x->x*2+1).sum();
		System.out.println(result);
	}

}
