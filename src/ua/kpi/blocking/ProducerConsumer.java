package ua.kpi.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    public static int ITEMS_COUNT = 20;
    public static volatile boolean finish = false;

    static class Producer extends Thread{
        private  BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run(){
            try {
                for (int i = 0; i < ITEMS_COUNT; i++) {
                    queue.put(1);
                    System.out.println("Created");
                    Thread.sleep(500);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                finish = true;
                queue.clear();
            }
        }
    }

    static class Consumer extends Thread{
        private  BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run(){
            try{
//                for (int i = 0; i < ITEMS_COUNT; i++) {
//                    Integer item = queue.take();
//                    System.out.println("Item retrived = " + item);
//                    Thread.sleep(2000);
//                }
                while (!finish || !queue.isEmpty() ){
                    Integer item = null;
                    while(item == null && !finish) {
                        item = queue.poll();
                    }
                    System.out.println("Item retrived = " + item);
                    Thread.sleep(200);
                }


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        new Producer(queue).start();
        new Consumer(queue).start();
        new Consumer(queue).start();
    }
}
