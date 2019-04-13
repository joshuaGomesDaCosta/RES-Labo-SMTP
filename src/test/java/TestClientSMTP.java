import applicationSMTP.clientSMTP.MailSender;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestClientSMTP {
    @Test
    public void itShouldWork(){
        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("./src/test/java/resources/ConfigTest.txt"), new File("./src/test/java/resources/MailPoolAdressTest.txt"), new File("./src/test/java/resources/PranksTest.txt"));

        mailSender.send();
    }
}
