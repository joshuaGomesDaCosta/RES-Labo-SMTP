package ServeurTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements a multi-threaded TCP server. It is able to interact
 * with several clients at the time, as well as to continue listening for
 * connection requests.
 *
 * @author Olivier Liechti
 */
public class MultiThreadedServer {

    final static Logger LOG = Logger.getLogger(MultiThreadedServer.class.getName());

    int port;

    /**
     * Constructor
     *
     * @param port the port to listen on
     */
    public MultiThreadedServer(int port) {
        this.port = port;
    }

    /**
     * This method initiates the process. The server creates a socket and binds it
     * to the previously specified port. It then waits for clients in a infinite
     * loop. When a client arrives, the server will read its input line by line
     * and send back the data converted to uppercase. This will continue until the
     * client sends the "BYE" command.
     */
    public void serveClients() {
        LOG.info("Starting the Receptionist Worker on a new thread...");
        new Thread(new ReceptionistWorker()).start();
    }

    /**
     * This inner class implements the behavior of the "receptionist", whose
     * responsibility is to listen for incoming connection requests. As soon as a
     * new client has arrived, the receptionist delegates the processing to a
     * "servant" who will execute on its own thread.
     */
    private class ReceptionistWorker implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket;

            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
                return;
            }

            while (true) {
                LOG.log(Level.INFO, "Waiting (blocking) for a new client on port {0}", port);
                try {
                    Socket clientSocket = serverSocket.accept();
                    LOG.info("A new client has arrived. Starting a new thread and delegating work to a new servant...");
                    new Thread(new ServantWorker(clientSocket)).start();
                } catch (IOException ex) {
                    Logger.getLogger(MultiThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        /**
         * This inner class implements the behavior of the "servants", whose
         * responsibility is to take care of clients once they have connected. This
         * is where we implement the application protocol logic, i.e. where we read
         * data sent by the client and where we generate the responses.
         */
        private class ServantWorker implements Runnable {

            Socket clientSocket;
            BufferedReader in = null;
            PrintWriter out = null;

            public ServantWorker(Socket clientSocket) {
                try {
                    this.clientSocket = clientSocket;
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(clientSocket.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(MultiThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void run() {
                String line;
                boolean shouldRun = true;
                double result = 0;
                boolean byZero = false;

                out.println("Welcome to the Calculator Server.\nSubmit your questions in the following format: number1 symbol number2.\nThe permitted operations are the addition (+), substraction (-), multiplication (*) and dividing (/).");
                out.flush();
                try {
                    sendNotification("Type your calcul: ");
                    while ((shouldRun) && (line = in.readLine()) != null) {
                        if (line.equalsIgnoreCase("stop")) {
                            shouldRun = false;
                            sendNotification("Thank you for your time. Have a nice day ! :D");
                        }
                        String[] tokens = line.split(" ");
                        if(shouldRun) {
                            switch (tokens[1].toUpperCase()) {
                                case ("+"):
                                    result = Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[2]);
                                    break;
                                case ("-"):
                                    result = Double.parseDouble(tokens[0]) - Double.parseDouble(tokens[2]);
                                    break;
                                case ("*"):
                                    result = Double.parseDouble(tokens[0]) * Double.parseDouble(tokens[2]);
                                    break;
                                case ("/"):
                                    if (Double.parseDouble(tokens[2]) == 0) {
                                        sendNotification("Error: impossible to divide by zero");
                                        byZero = true;
                                        break;
                                    }
                                    result = Double.parseDouble(tokens[0]) / Double.parseDouble(tokens[2]);
                                    break;
                                default:
                                    sendNotification("Command invalid. Please type your calcul with the specific symbol (symbols: +, -, *, /) or STOP to close the connection");
                                    break;
                            }
                            if(!byZero) {
                                sendNotification("The result of the calcul " + tokens[0] + " " + tokens[1] + " " + tokens[2] + " is equal to: " + Double.toString(result));
                            }
                            sendNotification("Next calcul: ");
                        }
                    }

                    LOG.info("Cleaning up resources...");
                    clientSocket.close();
                    in.close();
                    out.close();

                } catch (IOException ex) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ex1) {
                            LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                        }
                    }
                    if (out != null) {
                        out.close();
                    }
                    if (clientSocket != null) {
                        try {
                            clientSocket.close();
                        } catch (IOException ex1) {
                            LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                        }
                    }
                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            public void sendNotification(String message) {
                out.println(message);
                out.flush();
            }
        }
    }
}
