package applicationSMTP;

import applicationSMTP.clientSMTP.*;
import java.io.File;


public class Main {
    public void main(String[] args) {
        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise("127.0.0.1", 2525, new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/Pranks.txt"));

        mailSender.send(1, 4);
        mailSender.send(3, 8);
    }
}
