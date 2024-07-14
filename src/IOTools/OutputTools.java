package IOTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import User.User;

public class OutputTools
{
    public static void listToFile(List<User>list)
    {
       File file= new File("database.txt");
       try(FileWriter fw= new FileWriter("database.txt"))
       {
           StringBuilder sb = new StringBuilder();
           for (User user : list)
           {
               sb.append(user.getUserName()).append("\n").append(user.getPassword()).append("\n");
           }
           fw.write(sb.toString());
       }
       catch(IOException exception)
       {
           exception.printStackTrace();
       }
    }
}
