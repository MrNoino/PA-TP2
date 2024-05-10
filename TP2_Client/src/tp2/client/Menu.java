package tp2.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

/**
 * A class that wraps all the features about the client 
 */
public class Menu {

    private PrintWriter output;
    private BufferedReader input;

    /**
     * Class constructor that assigns the input and output streams
     * @param output stream to send data
     * @param input stream to receive data
     */
    public Menu(PrintWriter output, BufferedReader input) {
        this.output = output;
        this.input = input;
    }

    /**
     * Get personal data from the server and display it on console
     */
    public void getPersonalData() {
        String command;
        this.output.println("<cliente> <info>;");
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }
        if (command.equals("<cliente> <info> <fail>;")) {
            System.out.println("Erro ao obter os dados pessoais\n");
        }else{
            String[] commandParts = this.splitBasicCommand(command);

            System.out.println("Dados Pessoais\n\n"
                + "Nome: " + commandParts[4] + "\n"
                + "Nome de utilizador: " + commandParts[2] + "\n"
                + "Email: " + commandParts[5] + "\n"
                + "Estado: " + (Boolean.parseBoolean(commandParts[6]) ? "Ativo" : "Inativo") + "\n"
                + "NIF: " + commandParts[7] + "\n"
                + "Telefone: " + commandParts[8] + "\n"
                + "Morada: " + commandParts[9] + "\n");
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }

    /**
     * Update personal data on the server
     */
    public void updatePersonalData() {
        String username = InputReader.readString("Nome de utilizador: "),
                password = InputReader.readString("Palavra Passe: "),
                name = InputReader.readString("Nome: "),
                email = InputReader.readString("Email: ", "\nEmail inválido, tente novamente\n", "[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}"),
                nif = InputReader.readString("NIF: ", "\nNIF inválido, tente novamente\n", "\\d{9}"),
                phone = InputReader.readString("Telemóvel: ", "\nTelemóvel inválido, tente novamente\n", "[239]\\d{8}"),
                address = InputReader.readString("Morada: ");

        output.println("<cliente> <update> <"
                + username + ","
                + password + ","
                + name + ","
                + email + ","
                + "true,"
                + nif + ","
                + phone + ","
                + address + ">;"
        );
        String command;
        try {
            command = input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }
        if (command.equals("<servidor> <update> <ok>;")) {
            System.out.println("\nAtualizado com sucesso\n");
        } else {
            System.out.println("\nNão atualizado\n");
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }

    /**
     * Insert a book on the server
     */
    public void insertBook() {
        String title = InputReader.readString("Título da obra: ");
        int literaryStyleId = InputReader.readInt("ID do estilo literário: ");
        String type = InputReader.readString("Tipo de publicação: ");
        int pages = InputReader.readInt("Número de páginas: "),
                words = InputReader.readInt("Número de palavras: ");
        String isbn = InputReader.readString("ISBN: "),
                edition = InputReader.readString("Edição: ");

        this.output.println("<cliente> <inserir> <obra> <"
                + title + ","
                + literaryStyleId + ","
                + type + ","
                + pages + ","
                + words + ","
                + isbn + ","
                + edition + ">;");

        String command;
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }
        if (command.equals("<servidor> <inserir> <obra> <ok>;")) {
            System.out.println("\nInserido com sucesso\n");
        } else {
            System.out.println("\nNão inserido\n");
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }

    /**
     * Get a book by searching it by title from the server and display it on console
     */
    public void getBookByTitle() {
        String title = InputReader.readString("Título da obra a pesquisar: ");

        this.output.println("<cliente> <pesquisa> <obra> <"+ title +">;");
        String command;
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }
        if (command.equals("<servidor> <pesquisa> <obra> <fail>;")) {
            System.out.println("\nNenhuma obra encontrada\n");
        }else{
            String[] commandParts = this.splitBasicCommand(command);

            System.out.println("\nTitulo: " + commandParts[3] + "\n"
                    + "ID do estilo literário: " + commandParts[4] + "\n"
                    + "Tipo de publicação: " + commandParts[5] + "\n"
                    + "Número de páginas: " + commandParts[6] + "\n"
                    + "Número de palavras: " + commandParts[7] + "\n"
                    + "ISBN: " + commandParts[8] + "\n"
                    + "Edição: " + commandParts[9] + "\n"
                    + "Data de submissão: " + commandParts[10] + "\n");
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }

    /**
     * Get all books related to the author from the server and display it on console
     */
    public void getBooks() {
        this.output.println("<cliente> <listar> <obra>;");
        String command;
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }

        if (command.equals("<servidor> <pesquisa> <obra> <fail>;")) {
            System.out.println("\nNenhuma obra encontrada\n");
        } else {
            String[] commandParts = this.splitListsCommand(command);
   
            System.out.println("| Título | ID do estilo literário | Tipo de Publicação | Nº de páginas | Nº de palavras | ISBN | Edição | Data de Submissão |");
            for(int i = 3; i < commandParts.length; i++){
                String[] book = commandParts[i].split(",");
                System.out.println("| " + book[0]
                        + " | " + book[1] 
                        + " | " + book[2]
                        + " | " + book[3]
                        + " | " + book[4]
                        + " | " + book[5]
                        + " | " + book[6]
                        + " | " + book[7] + " |");   
            }
            System.out.println();
        }
        
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }
    
    /**
     * Get a review by searching it by the serial number from the server and display it on console
     */
    public void getReviewBySerialNumber(){
        long serialNumber = InputReader.readLong("Número de série a pesquisar: ");
        
        this.output.println("<cliente> <pesquisa> <revisao> <"+ serialNumber +">;");
        String command;
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }

        if (command.equals("<servidor> <pesquisa> <revisao> <fail>;")) {
            System.out.println("\nNenhuma revisão encontrada\n");
        } else {
            String[] commandParts = this.splitBasicCommand(command);

            System.out.println("\nID Gestor: " + commandParts[3] + "\n"
                    + "ID Revisor: " + commandParts[4] + "\n"
                    + "Data de realização: " + commandParts[5] + "\n"
                    + "Tempo decorrido: " + commandParts[6] + "\n"
                    + "Observações: " + commandParts[7] + "\n"
                    + "Custo: " + commandParts[8] + "\n"
                    + "Estado: " + commandParts[9] + "\n");
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }
    
    /**
     * Get all reviews of the author from the server and display it on console
     */
    public void getReviews() {
        this.output.println("<cliente> <listar> <revisao>;");
        String command;
        try {
            command = this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
            return;
        }

        if (command.equals("<servidor> <listar> <revisao> <fail>;")) {
            System.out.println("\nNenhuma revisao encontrada\n");
        } else {
            String[] commandParts = this.splitListsCommand(command);
   
            System.out.println("| ID Gestor | ID Revisor | Data de realização | Tempo Decorrido | Observações | Custo | Estado |");
            for(int i = 3; i < commandParts.length; i++){
                String[] review = commandParts[i].split(",");
                System.out.println("| " + review[0]
                        + " | " + review[1] 
                        + " | " + review[2]
                        + " | " + review[3]
                        + " | " + review[4]
                        + " | " + review[5]
                        + " | " + review[6] + " |");   
            }
            System.out.println();
        }
        this.output.println("<cliente> <ack>; ");
        try {
            this.input.readLine();
        } catch(SocketException e){
            System.out.println("Conexão com o servidor perdida\n");
        } catch (IOException e) {
            System.out.println("Erro ao ler a mensagem do servidor\n");
        }
    }

    
     
    /**
     * Split the command that doesn't have a list of items received from the server
     * @param command the command to be split
     * @return the split command
     */
    private String[] splitBasicCommand(String command) {
        String[] commandParts = command.split("> |,");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].replace("<", "")
                    .replace(">", "")
                    .replace(";", "");
        }
        return commandParts;
    }

    /**
     * Split the command that does have a list of items received from the server
     * @param command the command to be split
     * @return the split command
     */
    private String[] splitListsCommand(String command) {
        String[] commandParts = command.split("> |},");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].replace("<", "")
                    .replace(">", "")
                    .replace(";", "")
                    .replace("{", "")
                    .replace("}", "");
        }
        return commandParts;
    }
}
