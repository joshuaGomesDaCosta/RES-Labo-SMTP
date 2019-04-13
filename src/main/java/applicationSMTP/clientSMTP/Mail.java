package applicationSMTP.clientSMTP;

import java.util.LinkedList;

public class Mail {
    private String from;
    private String[] to;
    private String[] cc;
    private String body;
    private String subject;

    // Constructeurs ------------------------------------------------------------
    public Mail(String from, String[] to, String[] cc, String subject,String body) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.body = body;
    }

    public Mail() {
    }

    // Getters ------------------------------------------------------------
    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }

    /*public String[] getCc() {
        return cc;
    }*/

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    // Setters ------------------------------------------------------------
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    /*public void setCc(String[] cc) {
        this.cc = cc;
    }*/

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
