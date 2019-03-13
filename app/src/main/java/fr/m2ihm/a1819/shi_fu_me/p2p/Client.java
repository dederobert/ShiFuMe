package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.ClientCallBack;

/**
 * Classe utilisé par le client
 */
public class Client extends Common {

    private final boolean runningOnServerSide;
    @NonNull
    private ClientCallBack clientCallBack;
    /**
     * Addresse du serveur
     */
    @NonNull
    private InetAddress ownerAddress;
    private boolean running = true;
    @NonNull
    private Choice ownChoice = Choice.UNSET;
    @NonNull
    private Choice opponentChoice = Choice.UNSET;

    private final Object lockOwnChoice = new Object();
    private final Object lockOppChoice = new Object();

    /**
     * Créer le client
     * @param context Le context de l'application
     * @param groupOwnerAddress L'addresse du serveur
     * @param runningOnServerSide Vraie si le client est lancé côté server
     */
    public Client(@NonNull Context context, @NonNull InetAddress groupOwnerAddress, boolean runningOnServerSide, @NonNull ClientCallBack clientCallBack) {
        super(context);
        this.ownerAddress = groupOwnerAddress;
        this.runningOnServerSide = runningOnServerSide;
        this.clientCallBack = clientCallBack;
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
                this.getPrintWriter().println(MessageHeader.HELLO_CLIENTSIDE);

            while (running) {
                synchronized (lockOwnChoice) {
                    while (getOwnChoice().equals(Choice.UNSET)) lockOwnChoice.wait();
                    this.getPrintWriter().println(MessageHeader.SND_PLAYER_CHOICE + ":" + getOwnChoice()); //Envoie notre choix
                }

                String response = this.getBufferedReader().readLine(); //Lecture des infos du serveur

                if (MessageHeader.END.checkResponse(response)) { running = false; continue; }
                if (MessageHeader.RCV_PLAYER_CHOICE.checkResponse(response)) {
                    this.setOpponentChoice(Choice.valueOf(MessageHeader.RCV_PLAYER_CHOICE.extractInfo(response)));
                    clientCallBack.onReceiveOpponentChoice(this.getOpponentChoice());
                    resetChoice();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetChoice() {
        setOpponentChoice(Choice.UNSET);
        setOwnChoice(Choice.UNSET);
    }

    @NonNull
    private InetAddress getOwnerAddress() {
        return ownerAddress;
    }

    @NonNull
    public Choice getOwnChoice() {
        return ownChoice;
    }

    /**
     * Met à jour le choix utilisateur
     * <br />
     * La fonction gère l'accès concurentiel
     * @param ownChoice Le choix de l'utilisateur
     */
    public void  setOwnChoice(@NonNull Choice ownChoice) {
        synchronized (lockOwnChoice) {
            this.ownChoice = ownChoice;
            lockOwnChoice.notify();
        }
    }

    @NonNull
    public Choice getOpponentChoice() {
        return opponentChoice;
    }

    /**
     * Met à jour le choix du l'opposant
     * <br />
     * La fonction gère l'accès concurentiel
     * @param opponentChoice Choix de l'opposant
     */
    public void setOpponentChoice(@NonNull Choice opponentChoice) {
        synchronized (lockOppChoice) {
            this.opponentChoice = opponentChoice;
            lockOppChoice.notify();
        }
    }
}
