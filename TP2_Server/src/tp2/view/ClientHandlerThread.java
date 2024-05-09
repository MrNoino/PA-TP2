package tp2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import tp2.controller.ManageAuthors;
import tp2.controller.ManageUsers;
import tp2.model.Author;
import tp2.model.User;

public class ClientHandlerThread extends Thread {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private static Author author;

    public ClientHandlerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Erro ao criar as streams de entrada e saída");
            this.closeSocket();
            return;
        }
        String command = null;
        do {
            command = "<servidor> <hello>;";
            this.output.println(command);
            this.log(command);
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }
        } while (!command.equals("<cliente> <hello>;"));
        this.log(command);
        command = "<servidor> <ack>;";
        this.output.println(command);
        this.log(command);

        ManageUsers manageUsers = new ManageUsers();
        boolean login;
        User user;
        do {
            try {
                command = this.input.readLine();
                this.log(command);
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }

            String[] commandParts = command.replace("<", "").replace(">", "").replace(";", "").replace(",", " ").split(" ");

            user = manageUsers.login(commandParts[2], commandParts[3]);
            login = user != null && user.getRoleId() == 3 && user.isActive();

            if (!login) {
                command = "<servidor> <autenticar> <fail>;";
                this.output.println(command);
                this.log(command);
            }

        } while (!login);
        command = "<servidor> <autenticar> <success>;";
        this.output.println(command);
        this.log(command);

        ManageAuthors manageAuthors = new ManageAuthors();

        author = manageAuthors.getAuthor(user.getId());

        Menu menu = new Menu(this.socket, this.output, this.input);
        boolean exit;
        do {
            try {
                command = this.input.readLine();
                this.log(command);
                exit = command.equals("<cliente> <bye>;");
                if (exit) {
                    continue;
                }
            } catch (SocketException e) {
                this.closeSocket();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                break;
            }
            
            // Numero de serie \\d+

            if (command.equals("<cliente> <info>;")) {
                menu.getPersonalInfo();
                //se o comando contém "<cliente> <update> " mais "<" mais 7 palavras incluindo números, pontos e @ mais uma vírgula mais uma palavra seguida de ">;"
            } else if (command.matches("<cliente> <update> <([\\w.@\\s]+,){7}\\w+>;")) {
                menu.updatePersonalInfo(command);
            } else if (command.matches("<cliente> <inserir> <obra> <([\\w\\W\\s]+,){6}[\\w\\W\\s]+>;")) {
                menu.insertBook(command);
            }else if(command.matches("<cliente> <pesquisa> <obra> <[\\w\\W\\s]+>;")){
                menu.getBookByTitle(command);
            }else if(command.equals("<cliente> <listar> <obra>;")){
                menu.getBooks();
            } else if (command.matches("<cliente> <pesquisa> <revisao> <\\d+>;")){
                menu.getReviewBySerialNumber(command);
            } else if (command.equals("<cliente> <listar> <revisao>;")){
                menu.getReviews();
            }
        } while (!exit);

        System.out.println(this.socket.getInetAddress().getHostAddress()
                + ":"
                + this.socket.getPort()
                + " desconectou-se");
    }

    private void closeSocket() {
        if (this.output != null) {
            try {
                this.output.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar a stream de saída");
            }
        }
        if (this.input != null) {
            try {
                this.input.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar a stream de entrada");
            }
        }
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o socket");
            }
        }
    }
    
    public static Author getAuthor(){
        return ClientHandlerThread.author;
    }
    
    public static void setAuthor(Author author){
        ClientHandlerThread.author = author;
    }
    
    private void log(String content){
        System.out.println(this.socket.getInetAddress().getHostAddress()
                + ":" + this.socket.getPort()
                + "# " + content);
    }
}
