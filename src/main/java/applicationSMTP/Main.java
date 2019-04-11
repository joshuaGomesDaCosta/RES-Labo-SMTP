package applicationSMTP;

import applicationSMTP.clientSMTP.*;
import java.io.File;


public class Main {
    public void main(String[] args) {
        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("config.txt"), new File("Pranks.txt"), new File("MailPoolAdress.txt"));

        mailSender.send(1, 4);
        mailSender.send(3, 8);
    }
}
