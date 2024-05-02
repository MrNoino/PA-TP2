package tp2.client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        System.out.print("\tCliente");
        try {
            System.out.println(" - " + InetAddress.getLocalHost().getHostAddress() + "\n");
        } catch (UnknownHostException e) {
            System.out.println("\nNão foi possivel obter o endereço deesta máquina\n");
        }
        InputReader.openScanner();
        InetAddress serverAddress = null;
        int serverPort;
        boolean validAddress;
        do {
            try {
                serverAddress = InetAddress.getByName("localhost"/*InputReader.readString("Insira o endereço do servidor: ")*/);
                validAddress = true;
            } catch (UnknownHostException e) {
                System.out.println("\nEndereço inválido, tente novamente\n");
                validAddress = false;
            }
        } while (!validAddress);
        serverPort = 4000 /*InputReader.readInt("Insira o porto do servidor: ", "\nPorto fora do intervalo permitido (1024-65535), tente novamente\n",1024, 65535)*/;
        System.out.println();
        Socket socket = null;
        try {
            socket = new Socket(serverAddress, serverPort);
        } catch (ConnectException e) {
            System.out.println("Conexão recusada");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao conectar");
            return;
        }
        PrintWriter output;
        BufferedReader input;
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
        } catch (IOException e) {
            System.out.println("Erro ao criar as streams de entrada e saída");
            e.printStackTrace();
            return;
        }
        String command;
        try {
            command = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        output.println("<cliente> <hello>;");

        try {
            command = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Conexão estabelecida\n");

        boolean loggedIn;

        do {
            String username = InputReader.readString("Nome de utilizador: ");
            String password = InputReader.readString("Palavra Passe: ");
            System.out.println();

            output.println("<cliente> <autenticar> <" + username + "," + password + ">;");

            try {
                command = input.readLine();
                loggedIn = command.equals("<servidor> <autenticar> <success>;");
                if (!loggedIn) {
                    System.out.println("Não autenticado, tente novamente\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        } while (!loggedIn);

        System.out.println("Autenticado com sucesso\n");
        String[] commandParts;
        int option;
        Menu menu = new Menu(output, input);
        do {
            option = InputReader.readInt("**** MENU ****\n"
                    + "1. Consultar dados pessoais\n"
                    + "2. Alterar dados pessoais\n"
                    + "3. Inserir nova obra\n"
                    + "4. Pesquisar obra\n"
                    + "5. Pesquisar revisão\n"
                    + "6. Listar todas as obras\n"
                    + "7. Listar todas as revisões\n"
                    + "0. Sair\n\nEscolha: ", "\nOpção inválida, tente novamente\n", 0, 7);
            System.out.println();
            switch (option) {
                case 1:
                    menu.getPersonalData();
                    break;

                case 2:
                    
                    break;
                case 3:
                    
                    break;
                    
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;  
                case 0:
                    output.println("<login> <bye>;");
                    break;

                default:
                    System.out.println("\nOpção inválida, tente novamente\n");
                    break;
            }
        } while (option != 0);

        InputReader.closeScanner();
    }
}
