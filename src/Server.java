import java.util.ArrayList;
import java.util.Scanner;
public class Server {
    Server() {
        mainMenu();
    }

    static Scanner scan = new Scanner(System.in);
    private ArrayList<User> dataBase = new ArrayList<>();

    private String email() {
        System.out.println("introduce your email");
        String email = scan.nextLine();
        if (email.matches("\\S{2,}[@]\\S{2,}[.]\\S{2,3}")) {
            return email;
        } else {
            return email();
        }
    }

    private String birthDate() {
        System.out.println("enter your birth date\n must be in this format: xx/xx/xxxx");
        String birthdate = scan.nextLine();
        if (birthdate.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
            return birthdate;
        } else {
            return birthDate();
        }
    }

    private String phoneNumber() {
        System.out.println("introduce your phone number\n must be in this format: xxx-xxx-xxx");
        String phonenumber = scan.nextLine();
        if (phonenumber.matches("\\d{3}[-]\\d{3}[-]\\d{3}")) {
            return phonenumber;
        } else {
            return phoneNumber();
        }
    }

    private void registration(boolean loggedUser) {
        String userName;
        String password;
        boolean isAdmin = false;
        boolean isModerator = false;
        System.out.println("enter your user name");
        userName = scan.nextLine();
        if (DataBaseUtil.findUser(dataBase, userName) == -1) {
            password = DataBaseUtil.enterPaswd();
            String email = email();
            String birthdate = birthDate();
            String phoneNumber = phoneNumber();
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
            System.out.println("user is already registered");
            logIn(userName,loggedUser);
        }
    }

    private void registration(String userName) {
        String password;
        boolean isAdmin = false;
        boolean isModerator = false;
        password = DataBaseUtil.enterPaswd();
        String email = email();
        String birthdate = birthDate();
        String phoneNumber = phoneNumber();
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
    }

    private void logIn(boolean loggedUser) {
        String userName;
        String password;
        int userIndex;
        System.out.println("introduce your user name");
        userName = scan.nextLine();
        userIndex = DataBaseUtil.findUser(dataBase, userName);
        if (userIndex != -1) {
            System.out.println("introduce " + userName + "'s password");
            password = scan.nextLine();
            if (dataBase.get(userIndex).getPassword().equals(password)) {
                System.out.println("your welcome "+ userName);
                loggedUser=true;
            } else {
                System.out.println("password incorrect");
            }
        } else {
            System.out.println("user not found, want to register " + userName + " y/n");
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                registration(userName);
            } else {
                System.out.println("operation cancelled ");
            }
        }
    }

    private void logIn(String userName,boolean loggedUser) {
        String password;
        int userIndex;
        userIndex = DataBaseUtil.findUser(dataBase, userName);
        System.out.println("introduce " + userName + "'s password");
        password = scan.nextLine();
        if (dataBase.get(userIndex).getPassword().equals(password)) {
            System.out.println("your welcome "+ userName);
            loggedUser=true;
        } else {
            System.out.println("password incorrect");
        }
    }

    private void userList() {
        System.out.println("want to have Admin/Moderator access?  y/n");
        String choice = scan.nextLine();
        if (choice.equals("y")) {
            System.out.println("Admin or Moderator access? a/m");
            choice = scan.nextLine();
            if (choice.equals("a")) {
                System.out.println("introduce Admins user");
                String aUser = scan.nextLine();
                int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                if (aIndex != -1) {
                    if (dataBase.get(aIndex).isAdmin()) {
                        System.out.println("introduce Admins password");
                        String aPassword = scan.nextLine();
                        if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                            for (int i = 0; i < dataBase.size(); i++) {
                                System.out.print("/ User Name: " + dataBase.get(i).getUserName());
                                System.out.print("/ Password: " + dataBase.get(i).getPassword());
                                System.out.print("/ Email: " + dataBase.get(i).getEmail());
                                System.out.print("/ Birth Date: " + dataBase.get(i).getBirthDate());
                                System.out.print("/ Phone Number: " + dataBase.get(i).getPhoneNumber());
                                System.out.print("/ Moderator: " + dataBase.get(i).isModerator());
                                System.out.print("/ Admin: " + dataBase.get(i).isAdmin());
                            }
                        }
                    } else {
                        System.out.println("User isn't admin");
                    }
                } else {
                    System.out.println("user not found");
                }
            } else if (choice.equals("m")) {
                System.out.println("introduce Moderators user");
                String aUser = scan.nextLine();
                int aIndex = DataBaseUtil.findUser(dataBase, aUser);
                if (aIndex != -1) {
                    if (dataBase.get(aIndex).isModerator() || dataBase.get((aIndex)).isAdmin()) {
                        System.out.println("introduce Moderators password");
                        String aPassword = scan.nextLine();
                        if (aPassword.equals(dataBase.get(aIndex).getPassword())) {
                            for (int i = 0; i < dataBase.size(); i++) {
                                System.out.print("/ User Name: " + dataBase.get(i).getUserName());
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
                    System.out.println("user don't exists");
                }
            } else {
                System.out.println("input error");
            }
        } else if (choice.equals("n")) {
            for (int i = 0; i < dataBase.size(); i++) {
                System.out.print("/ User Name: " + dataBase.get(i).getUserName());
            }
        } else {
            System.out.println("INPUT ERROR");
        }
    }
    private void mainMenu()
    {
        boolean loggedUser=false;
        boolean exit = false;
        while (!exit)
        {
           if (loggedUser)
           {
               if (dataBase.get(userI).isAdmin())
               {
                   System.out.println("\n      ADMIN MENU");
                   System.out.println("//    1.REGISTRATION\n//    2.SEND E-MAIL\n//    3.SHOW INFO\n//    4.LOG OUT");
                   String check = scan.nextLine();
                   switch (check) {
                       case "1" -> registration(loggedUser);
                       case "2" -> logIn(loggedUser);
                       case "3" -> userList();
                       case "4" -> loggedUser = false;
                       default -> System.out.println("ERROR");
                   }
               }
               else
               {
                   System.out.println("\n      LOGGED MENU");
                   System.out.println("//    1.REGISTRATION\n//    2.SEND E-MAIL\n//    3.SHOW USERS\n//    4.LOG OUT");
                   String check = scan.nextLine();
                   switch (check) {
                       case "1" -> registration(loggedUser);
                       case "2" -> logIn(loggedUser);
                       case "3" -> userList();
                       case "4" -> loggedUser = false;
                       default -> System.out.println("ERROR");
                   }
               }
           }
           else
           {
               System.out.println("\n      MENU");
               System.out.println("//    1.REGISTRATION\n//    2.LOG IN\n//    3.SHOW USERS\n//    4.EXIT");
               String check = scan.nextLine();
               switch (check) {
                   case "1" -> registration(loggedUser);
                   case "2" -> logIn(loggedUser);
                   case "3" -> userList();
                   case "4" -> exit = false;
                   default -> System.out.println("ERROR");
               }
           }
        }
    }
    private void userListA(int userI)
    {
        for (int i = 0; i < dataBase.size(); i++) {
            System.out.print("/ User Name: " + dataBase.get(i).getUserName());
            System.out.print("/ Password: " + dataBase.get(i).getPassword());
            System.out.print("/ Email: " + dataBase.get(i).getEmail());
            System.out.print("/ Birth Date: " + dataBase.get(i).getBirthDate());
            System.out.print("/ Phone Number: " + dataBase.get(i).getPhoneNumber());
            System.out.print("/ Moderator: " + dataBase.get(i).isModerator());
            System.out.print("/ Admin: " + dataBase.get(i).isAdmin());
            System.out.println("----------------------------------------------");
        }
    }
}
