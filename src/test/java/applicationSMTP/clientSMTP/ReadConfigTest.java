package applicationSMTP.clientSMTP;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReadConfigTest {
    @Test
    public void itShouldReadConfigFile(){
        String[] config = {"localhost", "25", "5"};
        String[] configFile = new String[3];

        MailSender mailSender = MailSender.getInstance();
        mailSender.initialise(new File("./src/test/java/resources/ConfigTest.txt"), new File("./src/test/java/resources/MailPoolAdressTest.txt"), new File("./src/test/java/resources/PranksTest.txt"));
        configFile[0] = mailSender.getSmtpServerAddress();
        configFile[1] = Integer.toString(mailSender.getSmtpServerPort());
        configFile[2] = Integer.toString(mailSender.getSizeGroups());

        assertArrayEquals(config, configFile);
    }
}
