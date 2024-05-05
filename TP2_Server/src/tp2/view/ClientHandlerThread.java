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
    private Author author;

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
        User user;
        do {
            try {
                command = this.input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                this.closeSocket();
                return;
            }

            String[] commandParts = command.replace("<", "").replace(">", "").replace(";", "").replace(",", " ").split(" ");

            user = manageUsers.login(commandParts[2], commandParts[3]);
            login = user != null && user.getRoleId() == 3 && user.isActive();

            if (!login) {
                this.output.println("<servidor> <autenticar> <fail>;");
            }

        } while (!login);

        this.output.println("<servidor> <autenticar> <success>;");

        ManageAuthors manageAuthors = new ManageAuthors();

        this.author = manageAuthors.getAuthor(user.getId());

        String[] commandParts;
        boolean exit;
        do {
            try {
                command = this.input.readLine();
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

            if (command.equals("<cliente> <info>;")) {
                if (this.author != null) {
                    String a = this.author.getUsername() + ","
                            + this.author.getPassword() + ","
                            + this.author.getName() + ","
                            + this.author.getEmail() + ","
                            + this.author.isActive() + ","
                            + this.author.getNif() + ","
                            + this.author.getPhone() + ","
                            + this.author.getAddress();
                    this.output.println("<servidor> <info> <"+ a +">;");
                    System.out.println(this.socket.getInetAddress().getHostAddress()
                        + ":" + this.socket.getPort()
                        + " consultou os dados pessoais ("+a+")");
                } else {
                    this.output.println("<servidor> <info> <fail>;");
                }

            } else if (command.matches("<cliente> <update> <([\\w.@\\s]+,){7}\\w+>;")) {
                commandParts = command.split("> |,");
                for (int i = 0; i < commandParts.length; i++) {
                    commandParts[i] = commandParts[i].replace("<", "").replace(">", "").replace(";", "");
                }
                Author a = new Author(this.author.getId(),
                        commandParts[4],
                        commandParts[2],
                        commandParts[3],
                        commandParts[5],
                        this.author.isActive(),
                        this.author.isDeleted(),
                        this.author.getRoleId(),
                        commandParts[7],
                        commandParts[8],
                        commandParts[9],
                        this.author.getActivityBeginDate(),
                        this.author.getLiteraryStyleId());
                boolean updated = manageAuthors.updateAuthor(a);
                if (updated) {
                    this.author = a;
                    this.output.println("<servidor> <update> <ok>;");
                    System.out.println(this.socket.getInetAddress().getHostAddress()
                        + ":" + this.socket.getPort()
                        + " atualizou os dados pessoais");
                } else {
                    this.output.println("<servidor> <update> <fail>;");
                }

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
