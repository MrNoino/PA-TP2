package tp2.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{
    private int port;
    private ServerSocket serverSocket;
    private boolean close;
    public ServerThread(int port){
        this.port = port;
        this.close = false;
    }
    
    @Override
    public void run(){
        this.serverSocket = null; 
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor");
            return;
        }
        
        System.out.println("À espera de clientes...");
        while(!this.close){
            
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            
            System.out.println(socket.getInetAddress().getHostAddress() 
                                + ":" 
                                + socket.getPort() 
                                + " conectou-se");
            
            new ClientHandlerThread(socket).start();
            
        }
    }
    
    public void close(){
        if(this.serverSocket != null)
            try {
                this.close = true;
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
