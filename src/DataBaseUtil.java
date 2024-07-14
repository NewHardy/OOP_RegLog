import User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseUtil
{
    static int findUser(List<User> dataBase, String userName)
    {
        for (int i = 0; i <dataBase.size() ; i++)
        {
            if (dataBase.get(i).getUserName().equals(userName))
            {
                return i;
            }
        }
        return-1;
    }
    static String enterPaswd ()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("introduce your password");
        String a=scan.nextLine();
        System.out.println("introduce password again");
        String b= scan.nextLine();
        if (a.equals(b)) {
            return a;
        } else {
            System.out.println("Password incorrect");
            return enterPaswd();
        }
    }
    static int isLogged (ArrayList<User> dataBase)
    {
        for (int i = 0; i < dataBase.size(); i++)
        {
            if (dataBase.get(i).getRole().equals("Admin"))
            {
                return i ;
            }
        }
        return -1;
    }

}
