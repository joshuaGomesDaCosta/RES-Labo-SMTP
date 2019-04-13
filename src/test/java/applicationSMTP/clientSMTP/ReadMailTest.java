package applicationSMTP.clientSMTP;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReadMailTest {
    @Test
    public void itShouldReadPranksFile(){
        String[] mails = {"test@gmail.com", "pranks@outlook.com", "noreply@yahoo.fr"};
        String[] mailsFile;

        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("./src/test/java/resources/ConfigTest.txt"), new File("./src/test/java/resources/MailPoolAdressTest.txt"), new File("./src/test/java/resources/PranksTest.txt"));
        mailsFile = mailSender.getMails();

        assertArrayEquals(mails, mailsFile);
    }
}
