package tp2.view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tp2.controller.ManageAuthors;
import tp2.controller.ManageBooks;
import tp2.controller.ManageUsers;
import tp2.model.Author;
import tp2.model.Book;

public class Menu {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public Menu(Socket socket, PrintWriter output, BufferedReader input) {
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    public void getPersonalInfo() {
        if (ClientHandlerThread.getAuthor() != null) {
            String a = "<servidor> <info> <" +
                    ClientHandlerThread.getAuthor().getUsername() + ","
                    + ClientHandlerThread.getAuthor().getPassword() + ","
                    + ClientHandlerThread.getAuthor().getName() + ","
                    + ClientHandlerThread.getAuthor().getEmail() + ","
                    + ClientHandlerThread.getAuthor().isActive() + ","
                    + ClientHandlerThread.getAuthor().getNif() + ","
                    + ClientHandlerThread.getAuthor().getPhone() + ","
                    + ClientHandlerThread.getAuthor().getAddress() + ">;";
            this.output.println(a);
            this.log(a);
        } else {
            this.output.println("");
        }
    }

    public void updatePersonalInfo(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageUsers manageUsers = new ManageUsers();
        if ((manageUsers.existsUsername(commandParts[2]) && !ClientHandlerThread.getAuthor().getUsername().equals(commandParts[2]))
                || (manageUsers.existsEmail(commandParts[5]) && !ClientHandlerThread.getAuthor().getEmail().equals(commandParts[5]))
                || (manageUsers.existsNIF(commandParts[7])) && !ClientHandlerThread.getAuthor().getNif().equals(commandParts[7])) {
            this.output.println("<servidor> <inserir> <obra> <fail>;");
            return;
        }
        Author a = new Author(ClientHandlerThread.getAuthor().getId(),
                commandParts[4],
                commandParts[2],
                commandParts[3],
                commandParts[5],
                ClientHandlerThread.getAuthor().isActive(),
                ClientHandlerThread.getAuthor().isDeleted(),
                ClientHandlerThread.getAuthor().getRoleId(),
                commandParts[7],
                commandParts[8],
                commandParts[9],
                ClientHandlerThread.getAuthor().getActivityBeginDate(),
                ClientHandlerThread.getAuthor().getLiteraryStyleId());
        ManageAuthors manageAuthors = new ManageAuthors();
        boolean updated = manageAuthors.updateAuthor(a);
        if (updated) {
            ClientHandlerThread.setAuthor(a);
            command = "<servidor> <update> <ok>;";
            this.output.println(command);
            this.log(command);
        } else {
            this.output.println("");
        }
    }

    public void insertBook(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageBooks manageBooks = new ManageBooks();
        if (manageBooks.existsTitle(commandParts[3]) || manageBooks.existsIsbn(commandParts[8])) {
            this.output.println("<servidor> <inserir> <obra> <fail>;");
            return;
        }
        boolean inserted = manageBooks.insertBook(new Book(-1,
                commandParts[3],
                null,
                Integer.parseInt(commandParts[6]),
                Integer.parseInt(commandParts[7]),
                commandParts[8],
                commandParts[9],
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                null,
                Integer.parseInt(commandParts[4]),
                commandParts[5],
                ClientHandlerThread.getAuthor().getId()));
        if (inserted) {
            command = "<servidor> <inserir> <obra> <ok>;";
            this.output.println(command);
            this.log(command);
        } else {
            this.output.println("");
        }
    }

    public void getBooks() {
        ManageBooks manageBooks = new ManageBooks();
        ArrayList<Book> books = manageBooks.getBooksByAuthor(ClientHandlerThread.getAuthor().getId());
        String b = "";
        if (books.isEmpty()) {
            b = "<servidor> <listar> <obra> <fail>;";
            this.output.println(b);
            this.log(b);
            return;
        }
        for (Book book : books) {
            b += "{"
                    + book.getTitle() + ","
                    + book.getLiteraryStyleId() + ","
                    + book.getPublicationType() + ","
                    + book.getPages() + ","
                    + book.getWords() + ","
                    + book.getIsbn() + ","
                    + book.getEdition() + ","
                    + book.getSubmissionDate()
                    + "},";
        }
        b = b.substring(0, b.length() - 1);
        b = "<servidor> <listar> <obra> <" + b + ">;";
        this.output.println(b);
        this.log(b);
    }

    public void getBookByTitle(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageBooks manageBooks = new ManageBooks();
        Book book = manageBooks.getBookByTitle(ClientHandlerThread.getAuthor().getId(), commandParts[3]);

        if(book == null){
            command = "<servidor> <pesquisa> <obra> <fail>;";
            this.output.println(command);
            this.log(command);
            return;
        }
        command = "<servidor> <pesquisa> <obra> <"
                + book.getTitle() + ","
                + book.getTitle() + ","
                + book.getLiteraryStyleId() + ","
                + book.getPublicationType() + ","
                + book.getPages() + ","
                + book.getWords() + ","
                + book.getIsbn() + ","
                + book.getEdition() + ","
                + book.getSubmissionDate() + ">;";
        this.output.println(command);
        this.log(command);
    }

    private String[] splitCommand(String command) {
        String[] commandParts = command.split("> |,");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].replace("<", "")
                    .replace(">", "")
                    .replace(";", "");
        }
        return commandParts;
    }
    
    private void log(String content){
        System.out.println(this.socket.getInetAddress().getHostAddress()
                + ":" + this.socket.getPort()
                + "# " + content);
    }
}
