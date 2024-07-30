package IOTools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import User.User;


public class InputTools
{
    private static final String FILE_PATH="src/Storage/database.dat";
    public static List<User> fileToList()
    {
        File file = new File("database.txt");
        List<User> list = new ArrayList<>();
        try(Scanner scan = new Scanner(file))
        {
            while (scan.hasNext()){
                String userName=scan.next();
                String passWord= scan.next();
                list.add(new User(userName,passWord));
            }
        }
        catch (FileNotFoundException o)
        {
            o.printStackTrace();
        }
        if (list.size()>0)
        {
            list.get(0).setRole("Admin");
        }
        return list;
    }
    public static ArrayList<User> deserializationBase(){

        ArrayList<User> dataBase;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            dataBase = (ArrayList<User>) ois.readObject();
        }
        catch (Exception ex){
            System.out.println("Error");
            dataBase = new ArrayList<>();
        }
        return dataBase;
    }
}
