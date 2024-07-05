import java.util.ArrayList;
import java.util.Scanner;

public class NewServer {
    private User loggedUser;
    private ArrayList<User> dataBase = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public void startMenu()                 //TODO: add load database function
    {
        System.out.println("MAIN MENU\n1.Registration\n2.LogIn\n3.EXIT");
        String str = scan.nextLine();
        switch (str) {
            case "1" -> registration();
            case "2" -> logIn();
            case "3" -> serverExit();
            default -> {
                inputError();
                startMenu();
            }
        }
    }

    private void adminMenu() {
        System.out.println("MAIN MENU\n1.Register User\n2.Register Mod\n3.LOG OUT");
        String str = scan.nextLine();
        switch (str) {
            case "1" -> registration();
            case "2" -> registerMod();
            case "3" -> logOut();
            default -> {
                inputError();
                startMenu();
            }
        }
    }

    private void inputError() {
        System.out.println("You have miss written");
    }

    private void serverExit() {
        System.out.println("Server is shutting down");              //TODO: add saving database function
    }

    private String email() {
        System.out.println("Introduce your email");
        String email = scan.nextLine();
        if (email.matches("\\S{2,}[@]\\S{2,}[.]\\S{2,3}")) {
            return email;
        } else {
            return email();
        }
    }

    private String birthDate() {
        System.out.println("Enter your birth date\n Must be in this format: xx/xx/xxxx");
        String birthdate = scan.nextLine();
        if (birthdate.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
            return birthdate;
        } else {
            return birthDate();
        }
    }

    private String phoneNumber() {
        System.out.println("Introduce your phone number\n Must be in this format: xxx-xxx-xxx");
        String phonenumber = scan.nextLine();
        if (phonenumber.matches("\\d{3}[-]\\d{3}[-]\\d{3}")) {
            return phonenumber;
        } else {
            return phoneNumber();
        }
    }

    private void logIn() {
        System.out.println("LogIn Menu");
        System.out.println("Introduce your username");
        String userName = scan.nextLine();
        int userIndex = DataBaseUtil.findUser(dataBase, userName);
        if (userIndex != -1) {
            passEnt(userName, userIndex);
        } else {
            System.out.println("User not found, want to register that user? y/n");
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                registration(userName);
            } else {
                System.out.println("Returning to menu");
                startMenu();
            }
        }
    }

    private void logIn(String userName) {
        int userIndex = DataBaseUtil.findUser(dataBase, userName);
        passEnt(userName, userIndex);
        loggedUser = dataBase.get(userIndex);
    }

    private void registration() {
        System.out.println("Registration");
        System.out.println("Username of user you want to register:");
        String userName = scan.nextLine();
        if (DataBaseUtil.findUser(dataBase, userName) == -1) {
            String password = DataBaseUtil.enterPaswd();
            String email = email();
            String birthdate = birthDate();
            String phoneNumber = phoneNumber();
            boolean isAdmin = false;
            boolean isModerator = false;
            adminReg(userName, isAdmin);
            User user = new User(userName, password, isAdmin, false, email, birthdate, phoneNumber);
            dataBase.add(user);
        } else {
            System.out.printf("That username is in use, want to log in with %s ? y/n", userName);
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                logIn(userName);
            } else {
                System.out.println("Returning to menu");
                startMenu();
            }
        }
    }

    private void registration(String userName) {
        boolean isAdmin = false;
        String password = DataBaseUtil.enterPaswd();
        String email = email();
        String birthdate = birthDate();
        String phoneNumber = phoneNumber();
        adminReg(userName, isAdmin);
        User user = new User(userName, password, isAdmin, false, email, birthdate, phoneNumber);
        dataBase.add(user);
    }

    private void adminReg(String userName, boolean isAdmin) {
        System.out.println("Do you want to register as Admin?  Y/N");
        String answ = scan.nextLine();
        while (!answ.equals("y") || !answ.equals("n")) {
            System.out.println("Should be y or n");
            answ = scan.nextLine();
        }
        if ("n".equals(answ)) {
            System.out.printf("User %s was registered as normal user", userName);
        } else {
            isAdmin = true;
            System.out.printf("User %s was registered as Admin", userName);
        }
    }

    private void modReg(String userName, boolean isModerator) {
        System.out.println("Do you want to register as Moderator?  Y/N");
        String choice = scan.nextLine();
        if (choice.equals("y")) {
            System.out.println("Introduce Admins user");
            String aUser = scan.nextLine();
            int aIndex = DataBaseUtil.findUser(dataBase, aUser);
            if (dataBase.get(aIndex).isAdmin()) {
                System.out.println("Introduce Admins password");
                String aPassword = scan.nextLine();
                while (!aPassword.equals(dataBase.get(aIndex).getPassword())) {
                    inputError();
                    System.out.println("Introduce Admins password");
                    aPassword = scan.nextLine();
                }
                isModerator = true;
                System.out.printf("User %s was registered as Moderator", userName);
            } else {
                System.out.println("User introduced isn't admins");
                System.out.printf("User %s was registered as conventional user", userName);
            }
        } else if ("n".equals(choice)) {
            System.out.printf("User %s was registered as conventional user", userName);
        } else {
            inputError();
            System.out.printf("User %s was registered as conventional user", userName);
        }
    }

    private void passEnt(String userName, int userIndex) {
        System.out.printf("Introduce %s's password", userName);
        String password = scan.nextLine();
        while (!dataBase.get(userIndex).getPassword().equals(password)) {
            inputError();
            System.out.printf("Introduce %s's password", userName);
            password = scan.nextLine();
        }
        System.out.printf("You are logged in %s", userName);
    }

    private void registerMod() {

        System.out.println("Registration");
        System.out.println("Username of user you want to register as MOD:");
        String userName = scan.nextLine();
        if (DataBaseUtil.findUser(dataBase, userName) == -1) {
            String password = DataBaseUtil.enterPaswd();
            String email = email();
            String birthdate = birthDate();
            String phoneNumber = phoneNumber();
            boolean isAdmin = false;
            boolean isModerator = false;
            modReg(userName, isAdmin);
            User user = new User(userName, password, false, isModerator, email, birthdate, phoneNumber);
            dataBase.add(user);
        } else {
            System.out.printf("That username is in use, want to log in with %s ? y/n", userName);
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                logIn(userName);
            } else {
                System.out.println("Returning to menu");
                startMenu();
            }
        }

    }
    private void logOut()
    {

    }
}