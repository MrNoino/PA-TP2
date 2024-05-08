package tp2.client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private PrintWriter output;
    private BufferedReader input;

    public Menu(PrintWriter output, BufferedReader input) {
        this.output = output;
        this.input = input;
    }

    public void getPersonalData() {
        String command;
        this.output.println("<cliente> <info>;");
        try {
            command = this.input.readLine();
            if (command.equals("<cliente> <info> <fail>;")) {
                System.out.println("Erro ao obter os dados pessoais\n");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
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
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (command.equals("<servidor> <update> <ok>;")) {
            System.out.println("\nAtualizado com sucesso\n");
        } else {
            System.out.println("\nNão atualizado\n");
        }
    }

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
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (command.equals("<servidor> <inserir> <obra> <ok>;")) {
            System.out.println("\nInserido com sucesso\n");
        } else {
            System.out.println("\nNão inserido\n");
        }
    }

    public void getBookByTitle() {
        String title = InputReader.readString("Título da obra a pesquisar: ");

        this.output.println("<cliente> <pesquisa> <obra> <"+ title +">;");
        String command;
        try {
            command = this.input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (command.equals("<servidor> <pesquisa> <obra> <fail>;")) {
            System.out.println("\nNenhuma obra encontrada\n");
        } else if (command.matches("<servidor> <pesquisa> <obra> <([\\w\\W\\s]+,){7}[\\w\\W\\s]+>;")) {
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
    }

    public void getBooks() {
        this.output.println("<cliente> <listar> <obra>;");
        String command;
        try {
            command = this.input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
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
    }

    private String[] splitBasicCommand(String command) {
        String[] commandParts = command.split("> |,");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].replace("<", "")
                    .replace(">", "")
                    .replace(";", "");
        }
        return commandParts;
    }

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
