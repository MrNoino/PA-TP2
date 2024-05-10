package tp2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tp2.controller.ManageAuthors;
import tp2.controller.ManageBooks;
import tp2.controller.ManageReviews;
import tp2.controller.ManageUsers;
import tp2.model.Author;
import tp2.model.Book;
import tp2.model.Review;

/**
 * A class that wraps all the features available for the client
 */
public class Menu {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    /**
     * Class construtor that assigns the socket and the output and input streams
     * @param socket socket that is connected to the client
     * @param output output stream to send data
     * @param input input stream to receive data
     */
    public Menu(Socket socket, PrintWriter output, BufferedReader input) {
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    /**
     * Get and send the personal info of the client
     */
    public void getPersonalInfo() {
        String a;
        if (ClientHandlerThread.getAuthor() != null) {
            a = "<servidor> <info> <"
                    + ClientHandlerThread.getAuthor().getUsername() + ","
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
        try {
            a = this.input.readLine();
            this.log(a);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        a = "<servidor> <ack>;";
        this.output.println(a);
        this.log(a);
    }

    /**
     * Update the personal info of the client
     * @param command command received by the client
     */
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
        try {
            command = this.input.readLine();
            this.log(command);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        command = "<servidor> <ack>;";
        this.output.println(command);
        this.log(command);
    }

    /**
     * Insert a book given by the client
     * @param command command received by the client
     */
    public void insertBook(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageBooks manageBooks = new ManageBooks();
        if (manageBooks.existsTitle(commandParts[3]) || manageBooks.existsIsbn(commandParts[8])) {
            command = "<servidor> <inserir> <obra> <fail>;";
            this.output.println(command);
            this.log(command);
        } else {
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
        try {
            command = this.input.readLine();
            this.log(command);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        command = "<servidor> <ack>;";
        this.output.println(command);
        this.log(command);
    }

    /**
     * Get and sends all book of the client
     */
    public void getBooks() {
        ManageBooks manageBooks = new ManageBooks();
        ArrayList<Book> books = manageBooks.getBooksByAuthor(ClientHandlerThread.getAuthor().getId());
        String b = "";
        if (books == null || books.isEmpty()) {
            b = "<servidor> <listar> <obra> <fail>;";
            this.output.println(b);
            this.log(b);
        } else {
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
        try {
            b = this.input.readLine();
            this.log(b);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        b = "<servidor> <ack>; ";
        this.output.println(b);
        this.log(b);
    }

    /**
     * Get a book by title and send it to the client
     * @param command command received by the client
     */
    public void getBookByTitle(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageBooks manageBooks = new ManageBooks();
        Book book = manageBooks.getBookByTitle(ClientHandlerThread.getAuthor().getId(), commandParts[3]);

        if (book == null) {
            command = "<servidor> <pesquisa> <obra> <fail>;";
            this.output.println(command);
            this.log(command);
        } else {
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
        try {
            command = this.input.readLine();
            this.log(command);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        command = "<servidor> <ack>;";
        this.output.println(command);
        this.log(command);
    }

    /**
     * Get a review by serial number and send it to the client
     * @param command command received by the client
     */
    public void getReviewBySerialNumber(String command) {
        String[] commandParts = this.splitCommand(command);
        ManageReviews manageReviews = new ManageReviews();

        Review review = manageReviews.getReviewBySerialNumber(commandParts[3]);

        if (review == null) {
            command = "<servidor> <pesquisa> <revisao> <fail>;";
            this.output.println(command);
            this.log(command);
        } else {

            command = "<servidor> <pesquisa> <revisao> <"
                    + review.getManagerId() + ","
                    + review.getReviewerId() + ","
                    + review.getCompletionDate() + ","
                    + review.getElapsedTime() + ","
                    + review.getObservations() + ","
                    + review.getCost() + ","
                    + review.getStatus() + ">;";
            this.output.println(command);
            this.log(command);
        }
        try {
            command = this.input.readLine();
            this.log(command);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        command = "<servidor> <ack>;";
        this.output.println(command);
        this.log(command);
    }

    /**
     * Get all reviews and send it to the client
     */
    public void getReviews() {
        ManageReviews manageReviews = new ManageReviews();
        ArrayList<Review> reviews = manageReviews.getReviews(ClientHandlerThread.getAuthor().getId());
        String r = "";

        if (reviews == null || reviews.isEmpty()) {
            r = "<servidor> <listar> <revisao> <fail>;";
            this.output.println(r);
            this.log(r);
        } else {
            for (Review review : reviews) {
                r += "{"
                        + review.getManagerId() + ","
                        + review.getReviewerId() + ","
                        + review.getCompletionDate() + ","
                        + review.getElapsedTime() + ","
                        + review.getObservations() + ","
                        + review.getCost() + ","
                        + review.getStatus()
                        + "},";
            }
            r = r.substring(0, r.length() - 1);
            r = "<servidor> <listar> <revisao> <" + r + ">;";
            this.output.println(r);
            this.log(r);
        }
        try {
            r = this.input.readLine();
            this.log(r);
        } catch (IOException e) {
            this.log("Erro ao obter a mensagem do cliente");
            return;
        }
        r = "<servidor> <ack>;";
        this.output.println(r);
        this.log(r);
    }

    /**
     * Split the command received from the client
     * @param command the command to be split
     * @return the split command
     */
    private String[] splitCommand(String command) {
        String[] commandParts = command.split("> |,");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].replace("<", "")
                    .replace(">", "")
                    .replace(";", "");
        }
        return commandParts;
    }

    /**
     * Print the commands and errors from the client by also printing the IP and port of the client
     * @param content content to be appended on the message
     */
    private void log(String content) {
        System.out.println(this.socket.getInetAddress().getHostAddress()
                + ":" + this.socket.getPort()
                + "# " + content);
    }
}
