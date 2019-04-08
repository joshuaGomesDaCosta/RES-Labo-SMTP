package ClientSMTP;

import java.util.Random;
import java.util.logging.Logger;
import java.io.File;

public class MailSender {
    // partie statique -------------------
    private final static Logger LOG = Logger.getLogger(MailSender.class.getName());
    private final static MailSender instance;

    public static MailSender getInstance(){
        return instance;
    }

    //initialise l'instance unique de mailSender
    static {
        instance = new MailSender();
    }

    //variable membre -----------------
    private String[] mails = null;
    private String[] pranks = null;
    private Config config = null;

    private class Config{
        private int maxGroupSize;
        private int port;
        public Config(int maxGroupSize, int port){
            this.maxGroupSize = maxGroupSize;
            this.port = port;
        }
    }

    private MailSender(){}

    private MailSender(String[] mails, String[] pranks, File config = null){
        this.mails = mails;
        this.pranks = pranks;
    }

    /**
     * @brief               : génère le groupe de mails à utiliser (la première adresse est le sender)
     * @param GroupSize     : int le nombre d'adresses mails utilisées (en comptant l'expediteur)
     * @return              : String[] du tableau d'adresses utilisées.
     */
    private String[] generateGroup(int GroupSize){
        String[] group = new String[GroupSize];
        /**TODO*/
        return group;
    }

    /**
     * @brief   : lit le fichier contenant les pranks et les stockent dans le membre pranks
     */
    private void readPranks(){
        /**TODO*/
    }

    /**
     * @brief   : lit le fichier contenant les adresses mails et les stockent dans le membre mails
     */
    private void readMailPool(){
        /**TODO*/
    }

    /**
     * @brief           : configure le bot (port, serveur SMTP, ...)
     * @param config    : File du fichier de configuration
     */
    public void setConfig(File config){
        /**TODO*/
    }

    public void initialise(String configFile ,String pranks, String mailPool){
        /**TODO*/
    }

    /**
     * @brief               : demande au bot d'envoyer les mails
     * @param maxGroupSize  : int le nombre d'adresses mails utilisées (en comptant l'expediteur)
     */
    public void send( int maxGroupSize){
        Random rand = new Random();
        String[] group = generateGroup(rand.nextInt(maxGroupSize);
        String prank = pranks[rand.nextInt(pranks.length)];
        Mail mail;

        /** TODO*/
    }
}
