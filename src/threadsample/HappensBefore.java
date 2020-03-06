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
public class HappensBefore {
    static boolean  end;
     
    public  static void main(String[] args) throws InterruptedException {

        new Thread(){
            public void run(){
                System.out.println(getClass().getName());
            }
        }.start();


    	new Thread( 
                () -> { 
                        System.out.println("started");

                          while( true ){
                              synchronized (HappensBefore.class){
                                  if(end){
                                      break;
                                  }
                              }
                          } 
                          
                        System.out.println("finished"); 
                      }
        ).start();
        
        Thread.sleep(8);
        synchronized (HappensBefore.class) {
            end = true;
        }
        
    }
    
}
