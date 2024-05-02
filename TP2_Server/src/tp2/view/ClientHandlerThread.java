package tp2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import tp2.controller.ManageAuthors;
import tp2.controller.ManageUsers;
import tp2.model.Author;
import tp2.model.User;

public class ClientHandlerThread extends Thread {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private User user;

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
            this.output.println("<servidor> <hello>;");
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }
        } while (!command.equals("<cliente> <hello>;"));

        this.output.println("<servidor> <ack>;");

        ManageUsers manageUsers = new ManageUsers();
        boolean login;
        do {
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }

            String[] commandParts = command.replace("<", "").replace(">", "").replace(";", "").replace(",", " ").split(" ");

            this.user = manageUsers.login(commandParts[2], commandParts[3]);
            login = this.user != null && this.user.getRoleId() == 3 && this.user.isActive();
            
            if (!login) {
                this.output.println("<servidor> <autenticar> <fail>;");
            }

        } while (!login);

        this.output.println("<servidor> <autenticar> <success>;");

        boolean exit;
        do {
            try {
                command = this.input.readLine();
                exit = command.equals("<cliente> <bye>;");
                if (exit) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                break;
            }

            switch (command) {
                case "<cliente> <info>;":
                    System.out.println(this.socket.getInetAddress().getHostAddress()
                            + ":" + this.socket.getPort() 
                            + " consultou os dados pessoais");
                    ManageAuthors manageAuthors = new ManageAuthors();

                    Author author = manageAuthors.getAuthor(this.user.getId());

                    if (author != null)
                        this.output.println("<servidor> <info> <"
                                + author.getUsername() + ","
                                + author.getPassword() + ","
                                + author.getName() + ","
                                + author.getEmail() + ","
                                + author.isActive() + ","
                                + author.getNif() + ","
                                + author.getPhone() + ","
                                + author.getAddress()
                                + ">;");
                    else
                        this.output.println("<servidor> <info> <fail>;");
                    
                    break;
                default:
                    throw new AssertionError();
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
}
