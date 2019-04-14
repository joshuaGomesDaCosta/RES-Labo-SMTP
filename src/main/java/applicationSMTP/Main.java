package applicationSMTP;

import applicationSMTP.clientSMTP.*;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("../src/resources/Config.txt"), new File("../src/resources/MailPoolAddress.txt"), new File("../src/resources/Pranks.txt"));

        mailSender.send();
    }
}
