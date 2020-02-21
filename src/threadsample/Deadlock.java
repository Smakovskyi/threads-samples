/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

/**
 *
 * @author Денис
 */
public class Deadlock {
    public static void main(String[] args) {
        Object firstFork = new Object();
        Object secondFork  = new Object();
        new Thread(){
            public void run(){
                while(true){
                    synchronized( firstFork ){
                        synchronized (secondFork ){
                            System.out.println("first is eating");
                        }
                    }
                }
            }
        }.start();
        new Thread(){
            public void run(){
                while(true){
                    synchronized( secondFork ){
                        synchronized ( firstFork ){
                            System.out.println("second is eating");
                        }
                    }
                }
            }
        }.start();
    }
    
}
