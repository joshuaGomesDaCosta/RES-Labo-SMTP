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
        mailSender.initialise(new File("./src/test/java/resources/ConfigTest.txt"), new File("./src/test/java/resources/MailPoolAdressTest.txt"), new File("./src/test/java/resources/PranksTest.txt"));
        pranks2 = mailSender.getPranks();

        assertArrayEquals(pranks, pranks2);
    }
}