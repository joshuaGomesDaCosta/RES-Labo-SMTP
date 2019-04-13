package applicationSMTP;

import applicationSMTP.clientSMTP.*;
import java.io.File;


public class Main {
    public void main(String[] args) {
        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/Config.txt"), new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/Pranks.txt"));

        mailSender.send(1, 4);
        mailSender.send(3, 8);
    }
}
