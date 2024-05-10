package tp2.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class that represents a thread to accept clients
 */
public class ServerThread extends Thread{
    private int port;
    private ServerSocket serverSocket;
    private boolean close;
    public ServerThread(int port){
        this.port = port;
        this.close = false;
    }
    
    /**
     * Accepts clients, create and start a thread to handle every client
     */
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
    
    /**
     * Close the server socket
     */
    public void close(){
        if(this.serverSocket != null)
            try {
                this.close = true;
                this.serverSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o servidor");
            }
    }
}
