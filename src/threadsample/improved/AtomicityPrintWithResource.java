/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample.improved;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sds
 */
class Incrementor extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            AtomicityPrintWithResource.resource.execute(
                    () -> AtomicityPrintWithResource.value++
            );
        }
    }
}

public class AtomicityPrintWithResource {

    static volatile int value;
    static Resource resource = new Resource();

    public static void main(String args[]) throws InterruptedException {
        new Incrementor().start();
        while (value < 10000000) {
            if (value % 2 == 0) {
                resource.execute(() -> {
                    if (value % 2 == 0) {
                        System.out.println(/*"value="+*/value);
                    }
                });
            } else {
                Thread.yield();
            }
        }
    }

}
