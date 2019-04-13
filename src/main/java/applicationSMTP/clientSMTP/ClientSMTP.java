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
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        try{
            line = read();
            if(line.startsWith("220")){
                write("EHLO pranks\r\n");
            }

            line = read();
            if(!line.startsWith("250")){
                throw new Exception("error SMTP : " + line);
            }

            while(!line.contains("250 Ok")) {
                line = read();
            }
            write("MAIL FROM:<" + mail.getFrom() + ">\r\n");
            line = read();

            if(!line.startsWith("250")){
                throw new Exception("error SMTP : " + line);
            } else {
                for(String to: mail.getTo() ){
                    write("RCPT TO:<" + to + ">\r\n");
                    line = read();
                    if(!line.startsWith("250")){
                        throw new Exception("error SMTP : " + line);
                    }
                }
            }

            write("DATA\r\n");
            line = read();

            if(!line.startsWith("354")){
                throw new Exception("error SMTP : " + line);
            } else {
                write("From: " + mail.getFrom() + "\r\n" + "To: ");
                for(String to: mail.getTo()){
                    write(to);
                    if(to != mail.getTo()[mail.getTo().length-1]){
                        write(", ");
                    }
                }
                write("\r\n" + "Subject: " + mail.getSubject() + "\r\n\r\n" + mail.getBody() + "\r\n.\r\n");
                line = read();
            }

            if(!line.startsWith("250")){
                throw new Exception("error SMTP : " + line);
            }
            else{
                write("quit\r\n");
                read();
            }

            reader.close();
            writer.close();
            socket.close();
        } catch(Exception e){
            LOG.info(e.toString());
            reader.close();
            writer.close();
            socket.close();
        }
    }

    private void write(String msg) throws Exception{
        writer.print(msg);
        //writer.print(msg);
        writer.flush();
    }

    private String read() throws Exception{
        String line = reader.readLine();
        LOG.info( line + "\r\n");
        return line;
    }
}

