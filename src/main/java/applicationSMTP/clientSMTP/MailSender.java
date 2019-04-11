package applicationSMTP.clientSMTP;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class MailSender {
    // partie statique -------------------
    private final static Logger LOG = Logger.getLogger(MailSender.class.getName());
    private static MailSender instance = null;

    static{
        instance = new MailSender();
    }

    public static MailSender getInstance(){
        return instance;
    }

    //variable membre -----------------
    private String[] mails = null;
    private String[] pranks = null;
    private int port;
    /**TODO a part le port y-a-t'il d'autre config?
    private Config config = null;

    private class Config{
        private int port;
        public Config( int port){
            this.port = port;
        }
    }
    */
    //méthode privée -----------------
    private MailSender(){};

    /**
     * @brief               : génère le groupe de mails à utiliser (la première adresse est le sender)
     * @param GroupSize     : int le nombre d'adresses mails utilisées (en comptant l'expediteur)
     * @return              : String[] du tableau d'adresses utilisées.
     */
    private String[] generateGroup(int GroupSize) throws Exception{
        String mail = new String();
        Random rand = new Random();
        boolean isExist;
        String[] group = new String[GroupSize];
        if(GroupSize > mails.length ) {
            throw new Exception("Group size too big");
        }
        /**TODO pas optimale*/
        Collections.shuffle(Arrays.asList(mails));
        for(int i = 0; i < GroupSize; i++) {
            group[i] = mails[i];
        }
        return group;
    }

    /**
     * @brief            : lit le fichier contenant les pranks et les stockent dans le membre pranks
     * @param pranksFile : File du fichier de configuration
     */
    private void readPranks(File pranksFile){
        /**TODO*/
    }

    /**
     * @brief           : lit le fichier contenant les adresses mails et les stockent dans le membre mails
     * @param mailsFile : File du fichier de configuration
     */
    private void readMailPool(File mailsFile){
        /**TODO*/
    }

    /**
     * @brief            : configure le bot (port, serveur SMTP, ...)
     * @param configFile : File du fichier de configuration
     */
    private void readConfig(File configFile){
        /**TODO*/
    }

    //méthode publiques  -----------------

    public void initialise(File configFile, File mailsFile, File pranksFile){
        this.port = port;
        this.mails = mails;
        this.pranks = pranks;
    }

    /**
     * @brief               : demande au bot d'envoyer les mails
     * @param nbPrank  : int le nombre de blagues à envoyer
     * @param maxGroupSize  : int le nombre d'adresses mails utilisées (en comptant l'expediteur) par blague
     */
    public void send( int nbPrank, int maxGroupSize){
        Random rand = new Random();
        String[] group;
        String prank;
        Mail mail;
        try {
            for (int i = 0; i < nbPrank; i++) {
                group = generateGroup(rand.nextInt(maxGroupSize));
                prank = pranks[rand.nextInt(pranks.length)];
                /** TODO*/
            }
        }
        catch(Exception e){
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
