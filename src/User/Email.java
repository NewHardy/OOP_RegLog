package User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Email
{
    public Email(String sender, String receiver, String messageTxt) {
        this.sender = sender;
        this.reciver = receiver;
        this.emailTxt = messageTxt;
        this.read = false;
    }

    Scanner scan = new Scanner(System.in);
    private LinkedList<Email> mailBox= new LinkedList<>();
    private String emailTxt;
    private String sender;
    private String reciver;
    private boolean read;

    public String getSender() {
        return sender;
    }
    public String getReceiver() {
        return reciver;
    }
    public String getMessageTxt() {
        return emailTxt;
    }
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean newRead) {
        read = newRead;
    }

    public String toString() {
        return "Email{ \nsender:'" + sender+"\nreceiver:" + reciver+ "\nemailTxT='" + emailTxt +"\n isRead=" + reciver +"\n}";
    }
    int findUser(List<User> dataBase, String userName)
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
    public void sendEmail(String sender, ArrayList<User> dataBase)
    {
        System.out.println("who you want to email (userName)");
        reciver=scan.nextLine();
        if (findUser(dataBase,reciver)!=-1)
        {
            System.out.println("write the email");
            emailTxt=scan.nextLine();
            System.out.println("email sent");
            Email email = new Email(reciver,sender,emailTxt);
            mailBox.add(email);
        }
        else
        {
            System.out.println("user not found");
        }
    }
    public void emailServer(String userName)
    {
        System.out.println("YOUR MAILBOX");
        for (int i = 0; i < mailBox.size(); i++)
        {
            if (mailBox.get(i).reciver.equals(userName))
            {
               mailBox.toString();
            }
        }

    }
}