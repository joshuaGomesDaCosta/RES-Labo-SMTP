import ClientSMTP.*;
import java.io.FileReader;

public class Main {
    public int main(String[] args) {
        MailSender mailSender = MailSender.getInstance()
        mailSender.initialise("config.txt","Pranks.txt","MailPoolAdress.txt");

        mailSender.send(4);
        mailSender.send(8);
        mailSender.setConfig(new FileReader("config2.txt"));
        mailSender.send(6);
    }
}
