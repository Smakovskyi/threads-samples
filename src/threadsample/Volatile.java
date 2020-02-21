/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Денис
 */
public class Volatile {
    static volatile boolean  end;
     
    public  static void main(String[] args) throws InterruptedException {
    	
    	new Thread( 
                () -> { 
                        System.out.println("started");
                          while( !end ){ 
                          } 
                          
                        System.out.println("finished"); 
                      }
        ).start();
        
        Thread.sleep(8);
        end = true;
        
    }
    
}
