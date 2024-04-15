package tp1.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to read input from user's keyboard
 */
public class InputReader {
    
    private static Scanner scanner;
    private static final String INVALID_MESSAGE = "\nOpção inválida, tente novamente\n";
    
    /**
     * Class construtor that initialize the scanner
     */
    public static void openScanner(){
        scanner = new Scanner(System.in);
    }
    
    /**
     * Close the scanner
     */
    public static void closeScanner(){
        scanner.close();
        scanner = null;
    }
    
    /**
     * Always reading until an integer is given by the user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @return the integer given by the user's keyboard
     */
    public static int readInt(String msg){
        int number = 0;
        boolean validNumber;
        
        do{
            try{
                System.out.print(msg);
                number = scanner.nextInt();
                validNumber = true;
            }catch(InputMismatchException e){
                validNumber = false;
                System.out.println("\nEntrada de tipo inválido, tente novamente\n");
                
            }finally{
                scanner.nextLine();
            }
        }while(!validNumber);

        return number;
    }
    
    /**
     * Always reading until an integer is given by the user's keyboard and respect the interval given
     * @param msg message to be displayed before reading the user's keyboard
     * @param invalidInputMsg message to be display if the interval is not respected
     * @param min minimum value the integer is accepted
     * @param max maximum value the integer is accepted
     * @return the integer given by the user's keyboard
     */
    public static int readInt(String msg, String invalidInputMsg, int min, int max){
        int number = 0;
        boolean validNumber;
        
        do{
            try{
                System.out.print(msg);
                number = scanner.nextInt();
                validNumber = number >= min && number <= max;
                
                if(!validNumber)
                    System.out.println(invalidInputMsg);
            }catch(InputMismatchException e){
                validNumber = false;
                System.out.println("\nEntrada de tipo inválido, tente novamente\n");
                
            }finally{
                scanner.nextLine();
            }
        }while(!validNumber);

        return number;
    }
    
    /**
     * Always reading until an integer is given by the user's keyboard and respect the interval given
     * @param msg message to be displayed before reading the user's keyboard
     * @param min minimum value the integer is accepted
     * @param max maximum value the integer is accepted
     * @return the integer given by the user's keyboard
     */
    public static int readInt(String msg, int min, int max){
        return InputReader.readInt(msg, InputReader.INVALID_MESSAGE, min, max);
    }
    
    /**
     * Always reading until an long is given by the user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @return the long given by the user's keyboard
     */
    public static long readLong(String msg){
        long number = 0;
        boolean validNumber;
        
        do{  
            try{
                System.out.print(msg);
                number = scanner.nextLong();
                validNumber = true;
            }catch(InputMismatchException e){
                validNumber = false;
                System.out.println("\nEntrada de tipo inválido, tente novamente\n");
            }finally{
                scanner.nextLine();
            }
        }while(!validNumber);

        return number; 
    }
    
    /**
     * Always reading until an float is given by the user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @return the float given by the user's keyboard
     */
    public static float readFloat(String msg){
        float number = 0;
        boolean validNumber;
        
        do{
            
            try{
                System.out.print(msg);
                number = scanner.nextFloat();
                validNumber = true;
            }catch(InputMismatchException e){
                validNumber = false;
                System.out.println("\nEntrada de tipo inválido, tente novamente\n");
                
            }finally{
                scanner.nextLine();
            }
        }while(!validNumber);

        return number;
    }
    
    /**
     * Reads a string from user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @return the string given by the user's keyboard
     */
    public static String readString(String msg){
        System.out.print(msg);
        return scanner.nextLine();
    }
    
    /**
     * Always reading until a string matching the expression is given by the user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @param invalidInputMsg message to be displayed if the string doesn't match the expression
     * @param expression regular expression that string has to match
     * @return the string given by the user's keyboard
     */
    public static String readString(String msg, String invalidInputMsg, String expression){
        String str = "";
        boolean validStr = false;
        
        do{
            System.out.print(msg);
            str = scanner.nextLine();
            
            validStr = str.matches(expression);
            
            if(!validStr)
                System.out.println(invalidInputMsg);
        }while(!validStr);

        return str;
    }
    
    /**
     * Always reading until a valid date is given by the user's keyboard
     * @param msg message to be displayed before reading the user's keyboard
     * @param datePattern pattern to be mathec with the given date
     * @return a string with the date given by the user's keyboard
     */
    public static String readDate(String msg, String datePattern){
        String date;
        boolean validDate;
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
        do {   
            date = InputReader.readString(msg);
            date = date.replace("/", "-");
            try {
                dateFormat.parse(date);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("\nData inválida, tente novamente\n");
                validDate = false;
            }
        } while (!validDate);
        
        return date;
    }
}
