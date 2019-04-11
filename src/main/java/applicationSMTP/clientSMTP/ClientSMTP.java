package applicationSMTP.clientSMTP;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSMTP {
    private static final Logger LOG = Logger.getLogger(ClientSMTP.class.getSimpleName());

    private final int port;
    private final String adress;

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    //constructeur
    public ClientSMTP( String adress, int port){
        this.adress = adress;
        this.port = port;
    }

    public void sendMail( Mail mail) throws Exception{
        String line;

        socket = new Socket(adress,port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        LOG.info(reader.readLine() + "\n");
        writer.print("HELO");
        line = reader.readLine();
        LOG.info( line+ "\n");

        if(!line.startsWith("250")){
            throw new Exception("error SMTP : " + line);
        }

        while(line.startsWith("250")) {
            line = reader.readLine();
            LOG.info(line + "\n");
        }
        write("MAIL FROM: " + mail.getFrom());

        for(String to: mail.getTo() ){
            write("RCPT TO:" + to);
        }

    }

    private boolean write(String msg) throws Exception{
        writer.print(msg);
        String line = reader.readLine();
        LOG.info( line + "\n");

        return line.startsWith("250");
    }
}

