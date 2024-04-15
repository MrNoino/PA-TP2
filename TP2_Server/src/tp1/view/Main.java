package tp1.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tp1.controller.ManageLogs;
import tp1.model.Log;
import tp1.model.User;

/**
 * Class that represents the main program
 */
public class Main {

    private static User loggedUser;

    /**
     * Main function to run the program
     *
     * @param args arguments received via console
     */
    public static void main(String[] args) {

        long startProrgamMilis = System.currentTimeMillis();

        InputReader.openScanner();
        
        File file  = new File("system_execution.txt");

        long executionQuantity = 0;
        
        if(file.exists()){
            FileReader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println("\nFicheiro não encontrado\n");
            }
            BufferedReader input = new BufferedReader( reader );
            try { 
                input.readLine();
                executionQuantity = Long.parseLong(input.readLine());
            } catch (IOException e) {
                System.out.println("\nErro ao ler os valores da data de última execução e número de execuções\n");
            } finally{
                try {
                    input.close();
                    reader.close();
                } catch (IOException e) {
                    System.out.println("\nErro ao fechar a stream de entrada\n");
                }
            }
        }
        
        FileWriter writer = null;
        try {
            writer = new FileWriter( file );
        } catch (IOException e) {
            System.out.println("\nErro ao criar a stream de saída\n");
        }
        BufferedWriter output = new BufferedWriter( writer );
        try {
            output.write(new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "\n" + (executionQuantity+1));
        } catch (IOException e) {
            System.out.println("\nErro ao gravar o ficheiro com data da última execução e número de execuções\n");
        } finally{
            try {
                output.close();
                writer.close();
            } catch (IOException e) {
                System.out.println("\nErro ao fehcar a stream de saída\n");
            }
        }

        MainViews mainViews = new MainViews();
        mainViews.showNoManagerMenu();
        mainViews.showMainMenu();

        InputReader.closeScanner();

        long endProgramMilis = System.currentTimeMillis();
        Main.printExecutionTime(startProrgamMilis, endProgramMilis);

    }

    /**
     * get the user logged in
     *
     * @return the user logged in
     */
    public static User getLoggedUser() {
        return Main.loggedUser;
    }

    /**
     * Assigns the user that logged in, inserts a log and prints a welcome message
     *
     * @param loggedUser the user logged in
     */
    public static void login(User loggedUser) {
        Main.loggedUser = loggedUser;
        new ManageLogs().insertLog(new Log(Main.loggedUser.getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        Main.loggedUser.getUsername() + " Iniciou Sessão"));
        System.out.println("\nBem vindo " + Main.loggedUser.getUsername() + "\n");
    }

    /**
     * Logout by inserting a log, print a goodbye message and assigns user as null
     */
    public static void logout() {
        if (Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                new SimpleDateFormat("yyyy-mm-dd").format(new Date()),
                Main.getLoggedUser().getUsername() + " Terminou Sessão"));
            System.out.println("Adeus " + Main.getLoggedUser().getUsername() + "\n");
        }
        Main.loggedUser = null;
    }

    /**
     * Prints the execution time of the program
     *
     * @param startTime the time in miliseconds that the program had when
     * started
     * @param endTime the time in miliseconds that the program had when finished
     */
    public static void printExecutionTime(long startTime, long endTime) {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("EEEE';' yyyy-MM-dd HH:mm:ss");

        System.out.println("Início do processo: " + datetimeFormat.format(new Date(startTime)));
        System.out.println("Fim do processo: " + datetimeFormat.format(new Date(endTime)));
        long miliseconds = endTime - startTime, seconds = miliseconds / 1000, minutes = seconds / 60, hours = minutes / 60;
        System.out.println("Tempo de execução: " + miliseconds + " Milissegundos (" + seconds + " Segundos; " + minutes + " Minutos; " + hours + " Horas)");
    }

}
