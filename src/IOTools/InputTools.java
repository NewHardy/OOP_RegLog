package IOTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import User.User;

public class InputTools
{
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
}
