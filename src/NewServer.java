import IOTools.InputTools;
import IOTools.OutputTools;
import User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewServer {
    private User loggedUser;
    private List<User> dataBase = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    {dataBase=InputTools.fileToList();}

    public void startMenu()
    {

        if (loggedUser==null)
        {
            System.out.println("MAIN MENU\n1.Registration\n2.LogIn\n3.User Edit\n4.EXIT");
            String str = scan.nextLine();
            switch (str) {
                case "1" -> registration();
                case "2" -> logIn();
                case "3" -> userServer();
                case "4" -> serverExit();
                default -> {
                    inputError();
                    startMenu();
                }
            }
        }
        else
        {
            switch (loggedUser.getRole())
            {
                case"Admin"-> adminMenu();
                case"Mod"-> System.out.println("work in progress");
                case"User"->startMenu();
            }
        }
    }

    private void adminMenu() {
        System.out.println("ADMIN MENU\n1.Register User\n2.Admin Menu\n3.LOG OUT");
        String str = scan.nextLine();
        switch (str) {
            case "1" -> registration();
            case "2" -> adminServer();
            case "3" -> logOut();
            default -> {
                inputError();
                adminMenu();
            }
        }
    }

    private void inputError() {
        System.out.println("You have miss written");
    }

    private void serverExit() {
        System.out.println("Server is shutting down");
        OutputTools.listToFile(dataBase);
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
            logIn(userName,userIndex);
        } else {
            System.out.println("User.User not found, want to register that user? y/n");
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                registration(userName);
            } else {
                System.out.println("Returning to menu");
                startMenu();
            }
        }
    }

    private void logIn(String userName, int userIndex) {
        passEnt(userName, userIndex);
        loggedUser = dataBase.get(userIndex);
        startMenu();
    }

    private void registration() {
        System.out.println("Registration");
        System.out.println("Username of user you want to register:");
        String userName = scan.nextLine();
        int userIndex = DataBaseUtil.findUser(dataBase, userName);
        if (userIndex == -1) {
            registration(userName);
        } else {
            System.out.printf("That username is in use, want to log in with %s ? y/n", userName);
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                logIn(userName,userIndex);
            } else {
                System.out.println("Returning to menu");
                startMenu();
            }
        }
    }

    private void registration(String userName) {
        String role="";
        String password = DataBaseUtil.enterPaswd();
        String email = email();
        String birthdate = birthDate();
        String phoneNumber = phoneNumber();
        adminReg(userName,role);
        User user = new User(userName, password);
        dataBase.add(user);
        startMenu();
    }

    private void adminReg(String userName, String role) {
        System.out.println("Do you want to register as Admin or Mod?  A/M");
        String answ = scan.nextLine();
        if ("a".equals(answ))
        {
            role="Admin";
        }
        else if ("m".equals(answ))
        {
            role="Mod";
        }
        else
        {
            role="User";
        }
    }
    private void passEnt(String userName, int userIndex) {
        System.out.printf("Introduce %s's password\n", userName);
        String password = scan.nextLine();
        while (!dataBase.get(userIndex).getPassword().equals(password)) {
            inputError();
            System.out.printf("Introduce %s's password\n", userName);
            password = scan.nextLine();
        }
        System.out.printf("You are logged in %s\n", userName);
    }
    private void logOut()
    {
        loggedUser=null;
        startMenu();
    }
    private void adminServer()
    {
        System.out.println("ADMIN SERVER\n1.UserList\n2.Change Role\n3.Ban User\n4.Exit");
        String str = scan.nextLine();
        switch (str) {
            case "1" -> userList();
            case "2" -> difRole();
            case "3" -> banUser();
            case "4" -> adminMenu();
            default -> {
                inputError();
                adminServer();
            }
        }
    }
    private void userList()
    {
        for (int i = 0; i < dataBase.size(); i++) {
            System.out.print("/ User.User Name: " + dataBase.get(i).getUserName());
            System.out.print("/ Password: " + dataBase.get(i).getPassword());
            System.out.print("/ Role: " + dataBase.get(i).getRole());
            System.out.println("----------------------------------------------");
        }
    }
    private void difRole()
    {
        System.out.println("Introduce username of the user you want to change role:");
        String username = scan.nextLine();
        System.out.println("introduce your password");
        String pasw=scan.nextLine();
        int userIndex=DataBaseUtil.findUser(dataBase,username);
        if (!pasw.equals(dataBase.get(userIndex).getPassword()))
        {
            System.out.println("password incorrect");
        }
        else
        {
            System.out.println("What role do you want to give");
            System.out.println("ROLES\n1.ADMIN\n2.MOD\n3.DEFAULT");
            String str = scan.nextLine();
            switch (str) {
                case "1" ->
                {
                    dataBase.get(userIndex).setRole("Admin");
                }
                case "2" ->
                {
                    dataBase.get(userIndex).setRole("Mod");
                }
                case "3" ->
                {
                    dataBase.get(userIndex).setRole(null);
                }
                default -> inputError();
            }
        }
    }
    private void banUser()
    {
        System.out.println("what is the username of the user ypu want to ban?");
        String username=scan.nextLine();
        int userIndex=DataBaseUtil.findUser(dataBase,username);
        dataBase.get(userIndex).setRole("BAN");
        System.out.println("user succesfully banned");
    }
    private void userServer()
    {
        System.out.println("introduce your user to change any aspect");
        String username =scan.nextLine();
        System.out.println("What aspect do you want to change");
        int userIndex=DataBaseUtil.findUser(dataBase,username);
        System.out.println("ROLES\n1.EMAIL\n2.PHONE NUMBER\n3.BIRTH DATE\n4.EXIT");
        String str = scan.nextLine();
        switch (str) {
            case "1" ->
            {
                System.out.println("what is your new email?");
                String email=scan.nextLine();
                dataBase.get(userIndex).setEmail(email);
            }
            case "2" ->
            {
                System.out.println("what is your new phone number?");
                String phoneNumber=scan.nextLine();
                dataBase.get(userIndex).setPhoneNumber(phoneNumber);
            }
            case"3" ->
            {
                System.out.println("what is your new birth date?");
                String birthDate=scan.nextLine();
                dataBase.get(userIndex).setBirthDate(birthDate);
            }
            case "4" ->
            {
                startMenu();
            }
            default -> inputError();
        }
    }
}