package applicationSMTP.clientSMTP;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReadPranksTest {
    @Test
    public void itShouldReadPranksFile(){
        String[] pranks = {"pranks1", "pranks2", "pranks3"};
        String[] pranks2;

        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP2/RES-Labo-SMTP/src/resources/Pranks.txt"));
        pranks2 = mailSender.getPranks();

        assertArrayEquals(pranks, pranks2);
    }
}