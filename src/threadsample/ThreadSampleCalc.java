/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.Arrays;

/**
 *
 * @author KIRIL
 */
public class ThreadSampleCalc {

    private static final int[] array = new int[100500000];
    private static final int cores = 11;

    /**
     * @param args the command line arguments /**
     */
    public static void main(String[] args) throws InterruptedException {

        Arrays.fill(array, 2);

        CalculationThread[] calcThreadArray = new CalculationThread[cores];
        Thread[] threads = new Thread[cores];

        int step = array.length / cores;

        for (int i = 0; i < cores; i++) {
            calcThreadArray[i] = new CalculationThread(array, step * i, step * i + step);
        }

        calcThreadArray[calcThreadArray.length - 1].setEnd(array.length);

        for (int i = 0; i < calcThreadArray.length; i++) {
            threads[i] = new Thread(calcThreadArray[i]);
        }

        for (int i = 0; i < calcThreadArray.length; i++) {
            threads[i].start(); 
        }
        for (int i = 0; i < calcThreadArray.length; i++) {
           threads[i].join();
        }

        int summ = 0;
        
        for (CalculationThread calcThreadArray1 : calcThreadArray) {
            summ += calcThreadArray1/*[0]*/.getResult();
        }

        System.out.println(summ);
    }

}
