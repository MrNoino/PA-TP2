package tp1.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tp1.controller.ManageAuthors;
import tp1.controller.ManageBooks;
import tp1.controller.ManageLiteraryStyles;
import tp1.controller.ManageReviews;
import tp1.controller.ManageUsers;
import tp1.model.Author;
import tp1.model.Book;
import tp1.model.LiteraryStyle;
import tp1.model.Review;

public class AuthorViews {

    public void showMenu() {

        int option;

        do {
            option = InputReader.readInt("**** MENU DE AUTOR ****\n"
                    + "1. Obras\n"
                    + "2. Pedidos de Revisão\n"
                    + "3. Perfil\n"
                    + "0. Terminar Sessão\n\n"
                    + "Escolha: ", 0, 3);
            System.out.println();

            switch (option) {
                case 1:
                    showBooksMenu();
                    break;
                case 2:
                    showReviewRequestsMenu();
                    break;
                case 3:
                    showProfileMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente\n");
            }
        } while (option != 0);

        Main.logout();
    }

    private void showBooksMenu() {
        int option;

        do {
            option = InputReader.readInt("**** OBRAS ****\n"
                    + "1. Listar Obras\n"
                    + "2. Inserir Obra\n"
                    + "3. Atualizar Obra\n"
                    + "0. Voltar\n\n"
                    + "Escolha: ", 0, 3);
            System.out.println();
            ManageBooks manageBooks = new ManageBooks();
            String title, subtitle, isbn, edition, msg, publicationType;
            int pages, words, literaryStyleId;
            ManageLiteraryStyles manageLiteracyStyles = new ManageLiteraryStyles();
            ArrayList<LiteraryStyle> literaryStyles;
            switch (option) {
                case 1:
                    showListBooksMenu();
                    break;
                case 2:

                    title = InputReader.readString("Título: ");
                    if (manageBooks.existsTitle(title)) {
                        continue;
                    }

                    subtitle = InputReader.readString("Subtítulo: ");

                    pages = InputReader.readInt("Número de páginas: ");
                    words = InputReader.readInt("Número de palavras: ");

                    isbn = InputReader.readString("ISBN: ");
                    if (manageBooks.existsIsbn(isbn)) {
                        continue;
                    }

                    edition = InputReader.readString("Edição: ");

                    literaryStyles = manageLiteracyStyles.getLiteraryStyles();
                    if (literaryStyles == null) {
                        System.out.println("\nEstilos literários inixestentes\n");
                        return;
                    }
                    msg = "Estilos Literários\n";
                    for (int i = 0; i < literaryStyles.size(); i++) {
                        msg += (i + 1) + ". " + literaryStyles.get(i).getLiteraryStyle() + "\n";
                    }
                    msg += "Escolha: ";

                    literaryStyleId = literaryStyles.get(InputReader.readInt(msg, 1, literaryStyles.size()) - 1).getId();
                    publicationType = InputReader.readString("Tipo de publicação: ");

                    if (manageBooks.insertBook(new Book(-1,
                            title,
                            subtitle,
                            pages,
                            words,
                            isbn,
                            edition,
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            literaryStyleId,
                            publicationType,
                            Main.getLoggedUser().getId()))) {
                        System.out.println("\nInserido com sucesso\n");
                    } else {
                        System.out.println("\nNão inserido\n");
                    }
                    break;
                case 3:
                    long id = InputReader.readLong("ID da obra: ");
                    Book book = manageBooks.getBookById(Main.getLoggedUser().getId(), id);
                    title = InputReader.readString("Título: ");
                    if (!book.getTitle().equals(title) && manageBooks.existsTitle(title)) {
                        continue;
                    }

                    subtitle = InputReader.readString("Subtítulo: ");

                    pages = InputReader.readInt("Número de páginas: ");
                    words = InputReader.readInt("Número de palavras: ");

                    isbn = InputReader.readString("ISBN: ");
                    if (!book.getIsbn().equals(isbn) && manageBooks.existsIsbn(isbn)) {
                        continue;
                    }

                    edition = InputReader.readString("Edição: ");

                    literaryStyles = manageLiteracyStyles.getLiteraryStyles();
                    if (literaryStyles == null) {
                        System.out.println("\nEstilos literários inixestentes\n");
                        return;
                    }
                    msg = "Estilos Literários\n";
                    for (int i = 0; i < literaryStyles.size(); i++) {
                        msg += (i + 1) + ". " + literaryStyles.get(i).getLiteraryStyle() + "\n";
                    }
                    msg += "Escolha: ";

                    literaryStyleId = literaryStyles.get(InputReader.readInt(msg, 1, literaryStyles.size()) - 1).getId();
                    publicationType = InputReader.readString("Tipo de publicação: ");

                    if (manageBooks.updateBook(new Book(id,
                            title,
                            subtitle,
                            pages,
                            words,
                            isbn,
                            edition,
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            literaryStyleId,
                            publicationType,
                            Main.getLoggedUser().getId()))) {
                        System.out.println("\nAtualizado com sucesso\n");
                    } else {
                        System.out.println("\nNão atualizado\n");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
            }

        } while (option != 0);
    }

    private void showListBooksMenu() {
        int option;

        do {
            option = InputReader.readInt("**** OBRAS ****\n"
                    + "1. Listar Por Data De Submissão (Recente)\n"
                    + "2. Listar Por Data De Submissão (Antigo)\n"
                    + "3. Listar Por Título (Ascendente)\n"
                    + "4. Listar Por Título (Descendente)\n"
                    + "5. Pesquisar Por Data De Submissão\n"
                    + "6. Pesquisar Por ISBN\n"
                    + "0. Voltar\n\n"
                    + "Escolha:  ", 0, 6);
            System.out.println();

            ManageBooks manageBooks = new ManageBooks();
            ArrayList<Book> books = null;
            switch (option) {
                case 1:
                    this.showListBooksPaginatedMenu("submission_date", "DESC");
                    break;
                case 2:
                    this.showListBooksPaginatedMenu("submission_date", "ASC");
                    break;
                case 3:
                    this.showListBooksPaginatedMenu("title", "ASC");
                    break;
                case 4:
                    this.showListBooksPaginatedMenu("title", "DESC");
                    break;
                case 5:
                    books = manageBooks.getBooksBySubmissionDate(Main.getLoggedUser().getId(),
                            InputReader.readDate("Data de submisssão a pesquisar: ", "dd-mm-yyyy"));
                    break;

                case 6:
                    books = manageBooks.getBooksByIsbn(Main.getLoggedUser().getId(), InputReader.readString("ISBN a pesquisar: "));
                    break;
                case 0:
                    continue;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
            }

            if (option >= 5 && option <= 6) {
                if (books.isEmpty()) {
                    System.out.println("\nNenhuma obra encontrada\n");
                    continue;
                }

                System.out.println("| ID | Título | Subtítulo | Nº Páginas | Nº Palavras | ISBN | Edição | Data de Submissão |"
                        + " Data de aprovação | Id estilo literário | Tipo de publicação | Id author |");
                for (Book book : books) {
                    System.out.println("| " + book.getId() + " | "
                            + book.getTitle() + " | "
                            + book.getSubtitle() + " | "
                            + book.getPages() + " | "
                            + book.getWords() + " | "
                            + book.getIsbn() + " | "
                            + book.getEdition() + " | "
                            + book.getSubmissionDate() + " | "
                            + book.getApprovalDate() + " | "
                            + book.getLiteraryStyleId() + " | "
                            + book.getPublicationType() + " | "
                            + book.getAuthorId() + " | ");
                }
                System.out.println();
            }

        } while (option != 0);
    }

    private void showListBooksPaginatedMenu(String orderField, String sortOrder) {
        int page = 1;
        int option;
        do {
            System.out.println("\tPágina " + page + "\n");
            ArrayList<Book> books = new ManageBooks().getBooks(Main.getLoggedUser().getId(), orderField, sortOrder, page);
            if (books.isEmpty()) {
                System.out.println("Nenhuma obra encontrada\n");
            } else {

                System.out.println("| ID | Título | Subtítulo | Nº Páginas | Nº Palavras | ISBN | Edição | Data de Submissão |"
                        + " Data de aprovação | Id estilo literário | Tipo de publicação | Id author |");
                for (Book book : books) {
                    System.out.println("| " + book.getId() + " | "
                            + book.getTitle() + " | "
                            + book.getSubtitle() + " | "
                            + book.getPages() + " | "
                            + book.getWords() + " | "
                            + book.getIsbn() + " | "
                            + book.getEdition() + " | "
                            + book.getSubmissionDate() + " | "
                            + book.getApprovalDate() + " | "
                            + book.getLiteraryStyleId() + " | "
                            + book.getPublicationType() + " | "
                            + book.getAuthorId() + " | ");
                }
                System.out.println();
            }
            option = InputReader.readInt("1. Próxima Página\n2. Página anterior\n0. Voltar\n\nEscolha: ", 0, 2);
            System.out.println();
            switch (option) {
                case 1:
                    if (!books.isEmpty()) {
                        page++;
                    }
                    break;
                case 2:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente\n");
            }
        } while (option != 0);
    }

    private void showReviewRequestsMenu() {
        int option;

        do {
            option = InputReader.readInt("**** PEDIDOS DE REVISÃO ****\n"
                    + "1. Listar Pedidos de Revisão\n"
                    + "2. Solicitar uma Revisão\n"
                    + "0. Voltar\n\n"
                    + "Escolha: ", 0, 2);
            System.out.println();

            switch (option) {
                case 1:
                    showReviewRequestsListMenu();
                    break;
                case 2:

                    ManageBooks manageBooks = new ManageBooks();
                    ArrayList<Book> books = manageBooks.getBooksByAuthor(Main.getLoggedUser().getId());

                    if (books.isEmpty()) {
                        System.out.println("Não existem obras em seu nome\n");
                        continue;
                    }

                    String message = "Obras\n";

                    for (int i = 0; i < books.size(); i++) {
                        message += (i + 1) + ". " + books.get(i).getTitle() + "\n";
                    }

                    message += "\nEscolha: ";

                    Long bookId = books.get(InputReader.readInt(message, 1, books.size()) - 1).getId();
                    System.out.println();

                    ManageReviews manageReviews = new ManageReviews();
                    boolean success = manageReviews.insertReview(bookId, Main.getLoggedUser().getId());

                    if (success) {
                        System.out.println("Pedido revisão adicionado com sucesso\n");
                    } else {
                        System.out.println("Ocorreu um erro ao pedir revisão\n");
                    }

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
            }
        } while (option != 0);
    }

    private void showReviewRequestsListMenu() {

        int option;

        do {
            option = InputReader.readInt("**** LISTAR PEDIDOS DE REVISÃO ****\n"
                    + "1. Listar Por Data De Criação (Recente)\n"
                    + "2. Listar Por Data De Criação (Antigo)\n"
                    + "3. Listar Por Número De Série (Ascendente)\n"
                    + "4. Listar Por Número De Série (Descendente)\n"
                    + "5. Pesquisar Por Data De Criação\n"
                    + "6. Pesquisar Por Título\n"
                    + "7. Pesquisar Por Estado\n"
                    + "0. Voltar\n\n"
                    + "Escolha: ", 0, 7);
            
            System.out.println();

            ManageReviews manageReviews = new ManageReviews();
            long userId = Main.getLoggedUser().getId();

            switch (option) {
                case 1:
                    this.showReviewRequestsListPaginatedMenu("submission_date_desc");
                    break;
                case 2:
                    this.showReviewRequestsListPaginatedMenu( "submission_date");
                    break;
                case 3:
                    this.showReviewRequestsListPaginatedMenu( "serial_number_desc");
                    break;
                case 4:
                    this.showReviewRequestsListPaginatedMenu( "serial_number");
                    break;
                case 5:
                    this.showReviewRequestsSearchedMenu("date");
                    break;
                case 6:
                    this.showReviewRequestsSearchedMenu("title");
                    break;
                case 7:
                    this.showReviewRequestsSearchedMenu("status");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
            }
        } while (option != 0);
    }

    private void showReviewRequestsListPaginatedMenu(String sortType) {
        int page = 1;
        int option;
        do {

            ArrayList<Review> reviews = new ManageReviews().getReviewsByAuthor(Main.getLoggedUser().getId(), sortType, page);
            System.out.println("\tPágina " + page + "\n");

            if(reviews.isEmpty()){
                System.out.println("Nenhuma revisão encontrada\n");
            }else{
                System.out.println("| Data de Submissão | Número de Série | Título | Estado |\n");
                for(Review review : reviews){
                    System.out.println("| " + review.getSubmissionDate()
                    + " | " + review.getSerialNumber()
                    + " | " + review.getBook().getTitle()
                    + " | " + review.getStatus() + " |");
                }
                System.out.println();
            }
            
            option = InputReader.readInt("1. Próxima Página\n2. Página anterior\n0. Voltar\n\nEscolha: ", 0, 2);
            System.out.println();
            switch (option) {
                case 1:
                    if (!reviews.isEmpty()) {
                        page++;
                    }
                    break;
                case 2:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente\n");
            }
        } while (option != 0);
    }
    
    private void showReviewRequestsSearchedMenu(String searchField){
        ArrayList<Review> reviews = new ArrayList<Review>();
        switch (searchField.toLowerCase()) {
            case "date":
                reviews = new ManageReviews().getReviewsByDate(Main.getLoggedUser().getId(), InputReader.readDate("Data de Submissão a pesquisar: ", "dd-MM-yyyy"));
                break;
            case "title":
                reviews = new ManageReviews().getReviewsByTitle(Main.getLoggedUser().getId(), InputReader.readString("Título a pesquisar: "));
                break;
            case "status":
                reviews = new ManageReviews().getReviewsByStatus(Main.getLoggedUser().getId(), InputReader.readString("Estado a pesquisar: "));
                break;
            default:
                throw new AssertionError();
        }
        if(reviews.isEmpty()){
            System.out.println("Nenhuma revisão encontrada\n");
            return;
        }
        System.out.println("| Data de Submissão | Número de Série | Título | Estado |\n");
        for(Review review : reviews){
            System.out.println("| " + review.getSubmissionDate()
            + " | " + review.getSerialNumber()
            + " | " + review.getBook().getTitle()
            + " | " + review.getStatus() + " |");
        }
        System.out.println();
    }

    private void showProfileMenu() {
        int option;
        do {
            option = InputReader.readInt("**** PERFIL ****\n"
                    + "1. Atualizar\n"
                    + "2. Eliminar\n"
                    + "0. Voltar\n\n"
                    + "Escolha: ", 0, 2);
            System.out.println();

            switch (option) {
                case 1:
                    this.showUpdateProfileMenu();
                    break;
                case 2:
                    //this.showDeleteProfileMenu();
                    System.out.println("Funcionalidade não implementada\n");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente\n");
            }
        } while (option != 0);
    }

    private void showUpdateProfileMenu() {

        System.out.println("Atualizar Perfil\n");

        ManageAuthors manageAuthors = new ManageAuthors();
        Author author = manageAuthors.getAuthor(Main.getLoggedUser().getId());

        if (author == null) {
            System.out.println("Não foi possivel encontrar o revisor\n");
            return;
        }
        ManageUsers manageUsers = new ManageUsers();
        String name = InputReader.readString("Nome: "),
                username = InputReader.readString("Nome de utilizador: ");
        if (!author.getUsername().equals(username) && manageUsers.existsUsername(username)) {
            return;
        }
        String password = InputReader.readString("Palavra passe: "),
                email = InputReader.readString("Email: ", "\nEmail inválido, tente novamente\n", "[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}");
        if (!author.getEmail().equals(email) && manageUsers.existsEmail(email)) {
            return;
        }
        String nif = InputReader.readString("NIF: ", "\nNIF inválido, tente novamente\n", "\\d{9}");
        if (!author.getNif().equals(nif) && manageUsers.existsNIF(nif)) {
            return;
        }
        String phone = InputReader.readString("Telemóvel: ", "\nTelemóvel inválido, tente novamente\n", "[239]\\d{8}"),
                address = InputReader.readString("Morada: ");

        ManageLiteraryStyles manageLiteracyStyles = new ManageLiteraryStyles();
        ArrayList<LiteraryStyle> literaryStyles = manageLiteracyStyles.getLiteraryStyles();
        if (literaryStyles == null) {
            System.out.println("\nEstilos literários inixestentes.\n");
            return;
        }
        String msg = "Estilos Literários\n";
        for (int i = 0; i < literaryStyles.size(); i++) {
            msg += (i + 1) + ". " + literaryStyles.get(i).getLiteraryStyle() + "\n";
        }
        msg += "Escolha: ";

        int literaryStyleId = literaryStyles.get(InputReader.readInt(msg, 1, literaryStyles.size()) - 1).getId();
        if (manageAuthors.updateAuthor(new Author(Main.getLoggedUser().getId(),
                name,
                username,
                password,
                email,
                true,
                false,
                3,
                nif,
                phone,
                address,
                null,
                literaryStyleId))) {
            System.out.println("\nAtualizado com sucesso\n");
        } else {
            System.out.println("\nErro ao atualizar\n");
        }
    }

    private void showDeleteProfileMenu() {
        System.out.println("Eliminar Perfil\n");

        int option = InputReader.readInt("Deseja mesmo eliminar o perfil?\n1. Sim\n0. Não\n\nEscolha: ", 0, 1);

        switch (option) {
            case 1:
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpção inválida\n");
        }
    }
}
