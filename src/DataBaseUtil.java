import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseUtil
{
    static int findUser(ArrayList<User> dataBase,String userName)
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
    static boolean findAdmin(ArrayList<User> dataBase)
    {
        for (User user : dataBase)
        {
            if (user.isAdmin())
            {
                return true;
            }
        }
        return false;
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

}
