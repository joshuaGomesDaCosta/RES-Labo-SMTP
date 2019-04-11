package applicationSMTP.clientSMTP;
import clientSMTP.MailSender;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReadPranksTest {
    @Test
    public void itShouldReadPranks(){
        String[] pranks = {"pranks1", "pranks2", "pranks3"};
        String[] pranks2 = new String[];

        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/Pranks.txt"), new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"));
        pranks2 = mailSender.getPranks();

        assertArrayEquals(pranks, pranks2.toArray(new String[0]));
    }
}