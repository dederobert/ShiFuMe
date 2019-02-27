package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Classe utilisé par le client
 */
public class Client extends Common {

    private final boolean runningOnServerSide;
    /**
     * Addresse du serveur
     */
    private InetAddress ownerAddress;
    private boolean running = true;

    /**
     * Créer le client
     * @param context Le context de l'application
     * @param groupOwnerAddress L'addresse du serveur
     * @param runningOnServerSide
     */
    public Client(Context context, InetAddress groupOwnerAddress, boolean runningOnServerSide) {
        super(context);
        this.ownerAddress = groupOwnerAddress;
        this.runningOnServerSide = runningOnServerSide;
        Toast.makeText(context, "Je suis le client", Toast.LENGTH_LONG).show();
    }

    @Override
    public void run() {
        super.run();
        try {
            Log.d("[Client]", "Connection ...");
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(getOwnerAddress(), 8888));
            this.setPrintWriter(new PrintWriter(socket.getOutputStream(), true));
            this.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            Log.d("[Client]", "Connecté !");

            Log.d("[Client]", "Envoie message!");
            //Write Hello
            if (runningOnServerSide)
                this.getPrintWriter().println(MessageHeader.HELLO_SERVERSIDE);
            else
                this.getPrintWriter().println(MessageHeader.HELLO);

            while (running) {

                this.getPrintWriter().println(MessageHeader.PLAYER_CHOICE + "[choice: ]"); //Envoie notre choix
                String response = this.getBufferedReader().readLine(); //Lecture des infos du serveur

                if (MessageHeader.END.checkResponse(response)) running = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InetAddress getOwnerAddress() {
        return ownerAddress;
    }
}
