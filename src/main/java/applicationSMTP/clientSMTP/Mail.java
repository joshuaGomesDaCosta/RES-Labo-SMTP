package applicationSMTP.clientSMTP;

import java.util.LinkedList;

public class Mail {
    private String from;
    private LinkedList<String> to;
    private LinkedList<String> cc;
    private String body;

    // Constructeurs ------------------------------------------------------------
    public Mail(String from, LinkedList<String> to, LinkedList<String> cc, String body) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.body = body;
    }

    public Mail() {
    }

    // Getters ------------------------------------------------------------
    public String getFrom() {
        return from;
    }

    public LinkedList<String> getTo() {
        return to;
    }

    public LinkedList<String> getCc() {
        return cc;
    }

    public String getBody() {
        return body;
    }

    // Setters ------------------------------------------------------------
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(LinkedList<String> to) {
        this.to = to;
    }

    public void setCc(LinkedList<String> cc) {
        this.cc = cc;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
