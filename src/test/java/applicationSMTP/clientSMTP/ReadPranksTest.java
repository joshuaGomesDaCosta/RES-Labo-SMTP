package applicationSMTP.clientSMTP;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReadPranksTest {
    @Test
    public void itShouldReadPranks(){
        String[] pranks = {"pranks1", "pranks2", "pranks3"};
        String[] pranks2 = new String[0];

        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise("test", 2525, new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/Pranks.txt"));
        pranks2 = mailSender.getPranks();

        assertArrayEquals(pranks, pranks2);
    }
}