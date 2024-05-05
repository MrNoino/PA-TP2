package tp2.client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Menu {
    private PrintWriter output;
    private BufferedReader input;
    
    public Menu(PrintWriter output, BufferedReader input){
        this.output = output;
        this.input = input;
    }
    
    public void getPersonalData(){
        String command;
        String[] commandParts;
        this.output.println("<cliente> <info>;");
        try {
            command = this.input.readLine();
            if (command.equals("<cliente> <info> <fail>;")) {
                System.out.println("Erro ao obter os dados pessoais\n");
            }

            commandParts = command.split("> |,");
            for (int i = 0; i < commandParts.length; i++) {
                commandParts[i] = commandParts[i].replace("<", "")
                        .replace(">", "")
                        .replace(";", "");
            }

            System.out.println("Dados Pessoais\n\n"
                    + "Nome: " + commandParts[4] + "\n"
                    + "Nome de utilizador: " + commandParts[2] + "\n"
                    + "Palavra Passe: " + commandParts[3] + "\n"
                    + "Email: " + commandParts[5] + "\n"
                    + "Estado: " + (Boolean.parseBoolean(commandParts[6]) ? "Ativo" : "Inativo") + "\n"
                    + "NIF: " + commandParts[7] + "\n"
                    + "Telefone: " + commandParts[8] + "\n"
                    + "Morada: " + commandParts[9] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    
    public void updatePersonalData(){
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
        if(command.equals("<servidor> <update> <ok>;")){
            System.out.println("\nAtualizado com sucesso\n");
        }else{
            System.out.println("\nNão atualizado\n");
        }
    }
}
