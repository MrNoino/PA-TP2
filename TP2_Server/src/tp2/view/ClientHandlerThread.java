package tp2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp2.controller.ManageUsers;
import tp2.model.User;

public class ClientHandlerThread extends Thread{
    
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    
    public ClientHandlerThread(Socket socket){
        this.socket = socket;
    }
    
    public void run(){
        try {
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Erro ao criar as streams de entrada e saída");
            this.closeSocket();
            return;
        }
        String command = null;
        do{
            this.output.println("<login> <hello>;");
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }
        }while(!command.equals("<login> <hello>;"));
        
        this.output.println("<login> <ack>;");
        
        ManageUsers manageUsers = new ManageUsers();
        User user;
        
        do{
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }

            String[] commandParts = command.replace("<", "").replace(">","").replace(";", "").replace(",", " ").split(" ");
                
            user = manageUsers.login(commandParts[2], commandParts[3]);
            
            if(user == null || user.getRoleId() != 3)
                this.output.println("<login> <autenticar> <fail>;");
            
            
        }while(user == null || user.getRoleId() != 3);
        
        this.output.println("<login> <autenticar> <success>;");
        
        boolean exit;
        do{
            try {
                command = this.input.readLine();
                exit = command.equals("<login> <bye>;");
                System.out.println(command);
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }
        }while(!exit);
        System.out.println("\n" + this.socket.getInetAddress().getHostAddress() 
                                + ":" 
                                + this.socket.getPort() 
                                + " desconectou-se\n");
    }
    
    private void closeSocket(){
        if(this.output != null){
            try{
                this.output.close();
            } catch(Exception e){
                System.out.println("Erro ao fechar a stream de saída");
            }
        }
        if(this.input != null){
            try {
                this.input.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar a stream de entrada");
            }
        }
        if(this.socket != null){
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o socket");
            }
        }
    }
}
