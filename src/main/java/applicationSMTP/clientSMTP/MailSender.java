package applicationSMTP.clientSMTP;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ClientSMTP clientSMTP;
    private int smtpServerPort;
    private String smtpServerAddress;
    private int sizeGroups;

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
        ArrayList<String> pranks = new ArrayList();
        try {
            Reader rd = new InputStreamReader(new FileInputStream(pranksFile), "UTF-8");
            BufferedReader bfRd = new BufferedReader(rd);

            String line;
            while ((line = bfRd.readLine()) != null) {
                pranks.add(line);
            }
            bfRd.close();
            rd.close();
        } catch(IOException e){
            System.out.printf(e.toString());
        }
        this.pranks = pranks.toArray(new String[0]);
    }

    /**
     * @brief           : lit le fichier contenant les adresses mails et les stockent dans le membre mails
     * @param mailsFile : File du fichier de configuration
     */
    private void readMailPool(File mailsFile){
        /**TODO*/
        ArrayList<String> mails = new ArrayList();
        try {
            Reader rd = new InputStreamReader(new FileInputStream(mailsFile), "UTF-8");
            BufferedReader bfRd = new BufferedReader(rd);

            String line;
            while ((line = bfRd.readLine()) != null) {
                mails.add(line);
            }
            bfRd.close();
            rd.close();
        } catch(IOException e){
            System.out.printf(e.toString());
        }
        this.mails =  mails.toArray(new String[0]);
    }

    /**
     * @brief            : configure le bot (port, serveur SMTP, ...)
     * @param configFile : File du fichier de configuration
     */
    private void readConfig(File configFile){
        /**TODO*/
        ArrayList<String> mails = new ArrayList();
        try {
            Reader rd = new InputStreamReader(new FileInputStream(configFile), "UTF-8");
            BufferedReader bfRd = new BufferedReader(rd);

            String line;
            while ((line = bfRd.readLine()) != null) {
                mails.add(line);
            }
            bfRd.close();
            rd.close();
        } catch(IOException e){
            System.out.printf(e.toString());
        }
        String[] config = mails.toArray(new String[0]);
        for (String param:config){
            param = param.substring(param.lastIndexOf("=") + 1);
        }
        this.smtpServerAddress = config[0];
        this.smtpServerPort = Integer.parseInt(config[1]);
        this.smtpServerPort = Integer.parseInt(config[2]);
    }

    //méthode publiques  -----------------

    public void initialise(File configFile, File mailsFile, File pranksFile){
        readConfig(configFile);
        readMailPool(mailsFile);
        readPranks(pranksFile);
    }

    /**TODO faut-il que nbPrank et maxGroupSize soit défini dans la config
     * @brief               : demande au bot d'envoyer les mails
     * @param nbPrank  : int le nombre de blagues à envoyer
     * @param maxGroupSize  : int le nombre d'adresses mails utilisées (sans compter l'expediteur) par blague
     */
    public void send( int nbPrank, int maxGroupSize){
        Random rand = new Random();
        Mail mail = new Mail();
        try {
            for (int i = 0; i < nbPrank; i++) {
                //initialise le mail à envoyer
                mail.setFrom(mails[rand.nextInt(mails.length)]);
                mail.setTo(generateGroup(rand.nextInt(maxGroupSize)));
                mail.setSubject("Prank");
                mail.setBody(pranks[rand.nextInt(pranks.length)]);

                clientSMTP.sendMail(mail);
            }
        }
        catch(Exception e){
            LOG.log(Level.SEVERE, null, e);
        }
    }

    public String[] getPranks(){
        return pranks;
    }

    public String getSmtpServerAddress (){return smtpServerAddress;}

    public int getSmtpServerPort(){ return smtpServerPort;}

    public int getSizeGroups() {return sizeGroups;}
}
