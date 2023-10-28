import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{
    static Scanner scan=new Scanner(System.in);
    ArrayList<User> dataBase=new ArrayList<>();
    private void registration(ArrayList<User> dataBase)
    {
        String logIn ;
        int check;

        if (DataBaseUtil.findUser (dataBase,"a")==-1)
        {
            logIn = JOptionPane.showInputDialog("introduzca su log In");
            int coinc = DataBaseUtil.findUser(dataBase, logIn);
            if (coinc >= 0) {

                String choice = JOptionPane.showInputDialog("user is already registered\ndo you want to log in\n1.yes\n2.no");
                if (choice.equals("1")) {
                    logIn(dataBase, logIn);
                } else if (choice.equals("2")) {
                    JOptionPane.showMessageDialog(null,"operation cancelled");
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR");
                }
            } else {
                dataBase.add(logIn);
                dataBase.add(DataBaseUtil.enterPaswd());

                String chose =JOptionPane.showInputDialog("want to register as Admin y/n");
                if (chose.equals("y")) {
                    dataBase.add("a");
                } else {
                    dataBase.add("x");
                }
                JOptionPane.showMessageDialog(null,"user " + logIn + " registred");
            }
        }
        else {
            logIn = JOptionPane.showInputDialog("introduzca su log In");
            int coinc = DataBaseUtil.findUser(dataBase, logIn);
            if (coinc >= 0) {

                String choice = JOptionPane.showInputDialog("user is already registered\ndo you want to log in\n1.yes\n2.no");
                if (choice.equals("1")) {
                    logIn(dataBase, logIn);
                } else if (choice.equals("2")) {
                    JOptionPane.showMessageDialog(null, "operation cancelled");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR");
                }
            } else {
                dataBase.add(logIn);
                dataBase.add(DataBaseUtil.enterPaswd());

                String chose = JOptionPane.showInputDialog("want to register as mod y/n");
                if (chose.equals("y"))
                {
                    String adminU = JOptionPane.showInputDialog("introduce el usuario de ADMIN");
                    int cell=DataBaseUtil.findUser(dataBase,adminU);
                    if (cell==-1) {
                        JOptionPane.showMessageDialog(null,"user dont exists");
                        dataBase.add("x");
                    }
                    else {
                        check=DataBaseUtil.findUser(dataBase,adminU);
                        if (!dataBase.get(check+2).equals("a"))
                        {
                            JOptionPane.showMessageDialog(null, "user isnt Admin");
                            dataBase.add("x");
                        }
                        else
                        {
                            String adminP = JOptionPane.showInputDialog("introduce la contrasena de "+adminU);

                            if (dataBase.get(check+1).equals(adminP))
                            {
                                dataBase.add("m");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"la contrasena no es correcta");
                                dataBase.add("x");
                            }

                        }
                    }


                } else {
                    dataBase.add("x");
                }
                JOptionPane.showMessageDialog(null, "user " + logIn + " registred");
            }
        }
    }
    public static void registration(ArrayList<User> dataBase, String logIn) {
        dataBase.add(logIn);
        dataBase.add(DataBaseUtil.enterPaswd());
        String chose = JOptionPane.showInputDialog("want to register as mod y/n");

        if (chose.equals("y")) {
            dataBase.add("m");
        } else {
            dataBase.add("x");
        }
        JOptionPane.showMessageDialog(null,"user " + logIn + " registred");

    }
}
