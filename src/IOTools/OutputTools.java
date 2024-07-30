package IOTools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import User.User;

public class OutputTools
{
    private static final String FILE_PATH="src/Storage/database.dat";
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
    public static void serializationBase(ArrayList<User> dataBase){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH) )){
            oos.writeObject(dataBase);
        }
        catch (Exception ex)
        {
            System.out.println("error, database is not saved");
        }
    }
}
