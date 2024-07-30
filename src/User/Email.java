package User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Email
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
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("From: ").append(sender).append(" To: ").append(reciver).append("  ///  ").append(emailTxt).append("  /// read status: ").append(isRead());
        return sb.toString();
    }
}