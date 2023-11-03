import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Server {
    static Scanner scan = new Scanner(System.in);                                   //TODO: reg/log logic , file
    ArrayList<User> dataBase = new ArrayList<>();

    private void registration(ArrayList<User> dataBase) {
        String userName;
        String password;
        String email;
        String birthdate;
        String phoneNumber;
        boolean isAdmin = false;
        boolean isModerator = false;
        System.out.println("enter your user name");
        userName = scan.nextLine();
        if (DataBaseUtil.findUser(dataBase, userName) == -1) {
            password = DataBaseUtil.enterPaswd();
            System.out.println("introduce your email");
            email = scan.nextLine();
            if (email.matches("\\S{2,}[@]\\S{2,}[.]\\S{2,3}")) {
                System.out.println("enter your birth date\n must be in this format: xx/xx/xxxx");
                birthdate = scan.nextLine();
                if (birthdate.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
                    System.out.println("introduce your phone number\n must be in this format: xxx-xxx-xxx");
                    phoneNumber = scan.nextLine();
                    if (phoneNumber.matches("\\d{3}[-]\\d{3}[-]\\d{3}")) {
                        if (!DataBaseUtil.findAdmin(dataBase)) {
                            System.out.println("do you want to register as Admin?  Y/N");
                            String answ = scan.nextLine();
                            if (answ.equals("y")) {
                                isAdmin = true;
                                System.out.println("User " + userName + " was registered as Admin");
                            } else if ("n".equals(answ)) {
                                System.out.println("User " + userName + " was registered");
                            } else {
                                System.out.println("input error");
                            }
                        } else {
                            if (!DataBaseUtil.findAdmin(dataBase)) {
                                System.out.println("do you want to register as Moderator?  Y/N");
                                String answ = scan.nextLine();
                                if (answ.equals("y")) {
                                    System.out.println("introduce Admins user");
                                    String aUser = scan.nextLine();
                                    int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                                    if (dataBase.get(aIndex).isAdmin()) {
                                        if (dataBase.get(aIndex).equals(aUser)) {
                                            System.out.println("introduce Admins password");
                                            String aPassword = scan.nextLine();
                                            if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                                                isModerator = true;
                                                System.out.println("User " + userName + " was registered as Moderator");
                                            }
                                        } else {
                                            System.out.println("introduced user isn't Admins user");
                                            System.out.println("User " + userName + " was registered");
                                        }
                                    } else {
                                        System.out.println("User isn't admin");
                                        System.out.println("User " + userName + " was registered");
                                    }
                                } else if ("n".equals(answ)) {
                                    System.out.println("User " + userName + " was registered");
                                } else {
                                    System.out.println("input error");
                                }
                            }
                        }
                        User user = new User(userName, password, isAdmin, isModerator, email, birthdate, phoneNumber);
                        dataBase.add(user);
                    } else {
                        System.out.println("phone number not valid");
                    }
                } else {
                    System.out.println("birthdate not valid");
                }
            } else {
                System.out.println("email not valid");
            }
        } else {
            System.out.println("user is already registered");
            logIn(dataBase, userName);
        }
    }

    private void registration(ArrayList<User> dataBase, String userName) {
        String password;
        String email;
        String birthdate;
        String phoneNumber;
        boolean isAdmin = false;
        boolean isModerator = false;
        String choice = scan.nextLine();
        if (choice.equals("y")) {
            password = DataBaseUtil.enterPaswd();
            System.out.println("introduce your email");
            email = scan.nextLine();
            if (email.matches("\\S{2,}[@]\\S{2,}[.]\\S{2,3}")) {
                System.out.println("enter your birth date\n must be in this format: xx/xx/xxxx");
                birthdate = scan.nextLine();
                if (birthdate.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
                    System.out.println("introduce your phone number\n must be in this format: xxx-xxx-xxx");
                    phoneNumber = scan.nextLine();
                    if (phoneNumber.matches("\\d{3}[-]\\d{3}[-]\\d{3}")) {
                        if (!DataBaseUtil.findAdmin(dataBase)) {
                            System.out.println("do you want to register as Admin?  Y/N");
                            String answ = scan.nextLine();
                            if (answ.equals("y")) {
                                isAdmin = true;
                                System.out.println("User " + userName + " was registered as Admin");
                            } else if ("n".equals(answ)) {
                                System.out.println("User " + userName + " was registered");
                            } else {
                                System.out.println("input error");
                            }
                        } else {
                            if (!DataBaseUtil.findAdmin(dataBase)) {
                                System.out.println("do you want to register as Moderator?  Y/N");
                                String answ = scan.nextLine();
                                if (answ.equals("y")) {
                                    System.out.println("introduce Admins user");
                                    String aUser = scan.nextLine();
                                    int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                                    if (dataBase.get(aIndex).isAdmin()) {
                                        if (dataBase.get(aIndex).equals(aUser)) {
                                            System.out.println("introduce Admins password");
                                            String aPassword = scan.nextLine();
                                            if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                                                isModerator = true;
                                                System.out.println("User " + userName + " was registered as Moderator");
                                            }
                                        } else {
                                            System.out.println("introduced user isn't Admins user");
                                            System.out.println("User " + userName + " was registered");
                                        }
                                    } else {
                                        System.out.println("User isn't admin");
                                        System.out.println("User " + userName + " was registered");
                                    }
                                } else if ("n".equals(answ)) {
                                    System.out.println("User " + userName + " was registered");
                                } else {
                                    System.out.println("input error");
                                }
                            }
                        }
                        User user = new User(userName, password, isAdmin, isModerator, email, birthdate, phoneNumber);
                        dataBase.add(user);
                    } else {
                        System.out.println("phone number not valid");
                    }
                } else {
                    System.out.println("birthdate not valid");
                }
            } else {
                System.out.println("email not valid");
            }
        }
    }

    private void logIn(ArrayList<User> dataBase) {
        String userName;
        String password;
        int userIndex;
        System.out.println("introduce your user name");
        userName = scan.nextLine();
        userIndex = DataBaseUtil.findUser(dataBase, userName);
        if (dataBase.get(userIndex).getUserName().equals(userName)) {
            System.out.println("introduce " + userName + "'s password");
            password = scan.nextLine();
            if (dataBase.get(userIndex).getPassword().equals(password)) {
                System.out.println("you are in");
            } else {
                System.out.println("password incorrect");
            }
        } else {
            System.out.println("user not found, want to register " + userName + " y/n");
            registration(dataBase, userName);
        }
    }

    private void logIn(ArrayList<User> dataBase, String userName) {
        String password;
        int userIndex;
        userIndex = DataBaseUtil.findUser(dataBase, userName);
        System.out.println("introduce " + userName + "'s password");
        password = scan.nextLine();
        if (dataBase.get(userIndex).getPassword().equals(password)) {
            System.out.println("you are in");
        } else {
            System.out.println("password incorrect");
        }
    }

    private void userList(ArrayList<User> dataBase) {
        System.out.println("want to have Admin/Moderator access?  y/n");
        String choice = scan.nextLine();
        if (choice.equals("y")) {
            System.out.println("Admin or Moderator access? a/m");
            choice = scan.nextLine();
            if (choice.equals("a")) {
                System.out.println("introduce Admins user");
                String aUser = scan.nextLine();
                int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                if (dataBase.get(aIndex).isAdmin()) {
                    if (dataBase.get(aIndex).equals(aUser)) {
                        System.out.println("introduce Admins password");
                        String aPassword = scan.nextLine();
                        if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                            for (int i = 0; i < dataBase.size(); i++) {
                                System.out.print("/ User Name: " + dataBase.get(i).getUserName());
                                System.out.print("/ Password: " + dataBase.get(i).getPassword());
                                System.out.print("/ Email: " + dataBase.get(i).getEmail());
                                System.out.print("/ Birth Date: " + dataBase.get(i).getBirthDate());
                                System.out.print("/ Phone Number: " + dataBase.get(i).getPhoneNumber());
                                System.out.println("/ Moderator: " + dataBase.get(i).isModerator());
                                System.out.println("/ Admin: " + dataBase.get(i).isAdmin());
                            }
                        }
                    } else {
                        System.out.println("introduced user isn't Admins user");
                    }
                } else {
                    System.out.println("User isn't admin");
                }
            } else if (choice.equals("m")) {
                System.out.println("introduce Moderators user");
                String aUser = scan.nextLine();
                int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                if (dataBase.get(aIndex).isModerator()) {
                    if (dataBase.get(aIndex).equals(aUser)) {
                        System.out.println("introduce Moderators password");
                        String aPassword = scan.nextLine();
                        if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                            for (int i = 0; i < dataBase.size(); i++) {
                                System.out.print("/ User Name: " + dataBase.get(i).getUserName());
                                System.out.print("/ Password: " + dataBase.get(i).getPassword());
                                System.out.print("/ Email: " + dataBase.get(i).getEmail());
                                System.out.print("/ Birth Date: " + dataBase.get(i).getBirthDate());
                                System.out.print("/ Phone Number: " + dataBase.get(i).getPhoneNumber());
                            }
                        } else {
                            System.out.println("introduced user isn't Admins user");
                        }
                    } else {
                        System.out.println("User isn't admin");
                    }
                } else {
                    System.out.println("INPUT ERROR");
                }
            } else if (choice.equals("n")) {
                for (int i = 0; i < dataBase.size(); i++) {
                    System.out.print("/ User Name: " + dataBase.get(i).getUserName());
                    System.out.print("/ Birth Date: " + dataBase.get(i).getBirthDate());
                }
            } else {
                System.out.println("INPUT ERROR");
            }
        }
    }
    private boolean shutDown(ArrayList<User> dataBase,boolean exit,File info)
    {
        try {
            FileWriter fw = new FileWriter(info);
            FileWriter cleaner = new FileWriter(info);
            String check;
            check = JOptionPane.showInputDialog(null, "sure you want to exit?  y/n");
            if (check.equals("y")) {
                check = JOptionPane.showInputDialog(null, "save information?  y/n");
                if (check.equals("y")) {
                    JOptionPane.showMessageDialog(null, "new information saved");
                    cleaner.write("");
                    cleaner.close();
                    for (int i = 0; i < dataBase.size(); i++) {
                        fw.write(dataBase.get(i).getUserName());
                        fw.write(dataBase.get(i).getPassword());
                        fw.write(dataBase.get(i).getEmail());
                        fw.write(dataBase.get(i).getBirthDate());
                        fw.write(dataBase.get(i).getPhoneNumber());
                        if (dataBase.get(i).isAdmin())
                        {
                            fw.write("true");
                        }
                        else
                        {
                            fw.write("false");
                        }
                        if (dataBase.get(i).isModerator())
                        {
                            fw.write("true");
                        }
                        else
                        {
                            fw.write("false");
                        }
                    }
                    fw.close();
                    exit = true;
                    return exit;
                } else {
                    exit = true;
                    return exit;
                }
            } else {
                return exit;
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
        return exit;
    }
    public void menu ()
    {
        boolean exit =false;
        File info=new File("database.txt");
        ArrayList<User> database=new ArrayList<>();
        while(!exit) {
            System.out.println("1.REGISTRATION\n2.LOG IN\n3.SHOW USERS\n4.EXIT");
            int check = scan.nextInt();
            switch (check) {
                case 1 -> registration(database);
                case 2 -> logIn(database);
                case 3 -> userList(database);
                case 4 -> shutDown(database, exit, info);
                default -> System.out.println("ERROR");
            }
        }
    }
}