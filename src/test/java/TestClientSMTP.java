
import applicationSMTP.clientSMTP.ClientSMTP;
//import java
import org.junit.jupiter.api.Test;

public class TestClientSMTP {
    @Test
    public void itShouldWork(){
        ClientSMTP clientSMTP = new ClientSMTP("adresse",5);

    }
}
