package tp1.view;

import tp1.controller.ManageReviewers;
import tp1.controller.ManageUsers;
import tp1.model.Reviewer;

public class ReviewerViews {

    public void showMenu() {
        int option;

        do {
            option = InputReader.readInt("**** MENU DE REVISOR ****\n"
                    + "1. Listar Pedidos De Revisão\n"
                    + "2. Rever uma obra\n"
                    + "3. Perfil\n"
                    + "0. Voltar\n\n"
                    + "Escolha: ", 0, 3);
            System.out.println();

            switch (option) {
                case 1:
                    System.out.println("Funcionalidade não implementada\n");
                    //showReviewRequestsMenu();
                    break;
                case 2:
                    System.out.println("Funcionalidade não implementada\n");
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

    private void showReviewRequestsMenu() {

        String menu = "**** LISTAR PEDIDOS DE REVISÃO ****\n"
                + "1. Listar Por Data De Criação (Recente)\n"
                + "2. Listar Por Data De Criação (Antigo)\n"
                + "3. Listar Por Título (Ascendente)\n"
                + "4. Listar Por Título (Descendente)\n"
                + "5. Pesquisar Por Data De Criação\n"
                + "6. Pesquisar Por Título\n"
                + "7. Pesquisar Por Estado\n"
                + "0. Voltar\n\n"
                + "\n\nEscolha: ";

        int input = InputReader.readInt(menu, 0, 7);
        System.out.println();

        switch (input) {
            case 1:
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
                break;
            default:
                System.out.println("\nOpção inválida, tente novamente\n");
        }
    }

    private void showProfileMenu(){
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
                    System.out.println("Funcionalidade não implementada\n");
                    //this.showDeleteProfileMenu();
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

        ManageReviewers manageReviewers = new ManageReviewers();

        Reviewer reviewer = manageReviewers.getReviewer(Main.getLoggedUser().getId());

        if (reviewer == null) {
            System.out.println("Não foi possivel encontrar o revisor\n");
            return;
        }
        ManageUsers manageUsers = new ManageUsers();
        String name = InputReader.readString("Nome: "),
                username = InputReader.readString("Nome de utilizador: ");
        if (!reviewer.getUsername().equals(username) && manageUsers.existsUsername(username)) {
            return;
        }
        String password = InputReader.readString("Palavra passe: "),
                email = InputReader.readString("Email: ", "\nEmail inválido, tente novamente\n", "[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}");
        if (!reviewer.getEmail().equals(email) && manageUsers.existsEmail(email)) {
            return;
        }
        String nif = InputReader.readString("NIF: ", "\nNIF inválido, tente novamente\n", "\\d{9}");
        if (!reviewer.getNif().equals(nif) && manageUsers.existsNIF(nif)) {
            return;
        }
        String phone = InputReader.readString("Telemóvel: ", "\nTelemóvel inválido, tente novamente\n", "[239]\\d{8}"),
                address = InputReader.readString("Morada: ");
        String graduation = InputReader.readString("Formação Académica: "),
                specialization = InputReader.readString("Área de especialização: ");
        if (manageReviewers.updateReviewer(new Reviewer(Main.getLoggedUser().getId(),
                name,
                username,
                password,
                email,
                true,
                false,
                2,
                nif,
                phone,
                address,
                graduation,
                specialization))) {
            System.out.println("\nAtualizado com sucesso\n");
        } else {
            System.out.println("\nErro ao atualizar\n");
        }
    }
    
    private void showDeleteProfileMenu(){
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
