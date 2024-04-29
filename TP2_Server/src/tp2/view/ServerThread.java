package tp2.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private int port;
    public ServerThread(int port){
        this.port = port;
    }
    
    @Override
    public void run(){
        ServerSocket serversocket = null; 
        Socket socket = null;
        try {
            serversocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor");
            return;
        }
        
        System.out.println("À espera de clientes...");
        while(true){
            
            try {
                socket = serversocket.accept();
            } catch (IOException e) {
                System.out.println("Erro ao aceitar o cliente");
                continue;
            }
            
            System.out.println("\n" + socket.getInetAddress().getHostAddress() 
                                + ":" 
                                + socket.getPort() 
                                + " conectou-se\n");
            
            new ClientHandlerThread(socket).start();
            
        }
    }
}
