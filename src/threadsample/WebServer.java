/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class WebServer {
    public static void main(String[] args) throws IOException {
    	int intVal =5;
        ServerSocket socket = new ServerSocket(81);
        while(true){
            final Socket connection = socket.accept();
            new Thread(){
                public void run(){
                    try {
                        new Handler().process(connection);
                    } catch (IOException ex) {
                        try {
                            connection.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }.start();
            
            
        }
    }
    
    static class Handler{
        public void process(Socket connection) throws IOException{
            connection.getOutputStream().write( "Hello world".getBytes() );
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            connection.close();
        }
    }
}
