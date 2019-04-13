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
        mailSender.initialise(new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/Config.txt"), new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/MailPoolAdress.txt"), new File("C:/Users/dasil/RES_labo/SMTP/RES-Labo-SMTP/src/resources/Pranks.txt"));
        configFile[0] = mailSender.getSmtpServerAddress();
        configFile[1] = Integer.toString(mailSender.getSmtpServerPort());
        configFile[2] = Integer.toString(mailSender.getSizeGroups());

        assertArrayEquals(config, configFile);
    }
}
