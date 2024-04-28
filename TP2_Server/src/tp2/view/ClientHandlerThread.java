package tp2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                System.out.println(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(!command.equals("<login> <hello>;"));
        
        this.output.println("<login> <ack>;");

        try {
            command = this.input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        String[] commandParts = command.replace("<", "").replace(">","").replace(";", "").split(" ");
        for(String s: commandParts)
            System.out.println(s);
            
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
