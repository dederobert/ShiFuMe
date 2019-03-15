package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.models.Game;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.callbacks.ServerSideClientCallBack;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.ServerSideClientListener;

/**
 * Classe représentant le serveur
 * @version 1.0.0
 */
public class Server extends Common {

    /**
     * List des sockets clients
     */
    private final ServerSideClient[] clients = new ServerSideClient[2];
    private final ServerSideClientCallBack serverSideClientCallBack;

    private final Choice[] choices = new Choice[2];
    private final Object[] locks = new Object[2];

    public void resetChoice() {
        synchronized (this) {
            for (Choice choice : choices)
                choice = Choice.UNSET;
        }
    }

    /**
     * Créer un serveur
     * @param context Le context de l'application
     * @param game
     */
    public Server(Context context, Game game) {
        super(context, game);
                
        this.serverSideClientCallBack = new ServerSideClientListener(this);
        Toast.makeText(context, "Je suis le serveur", Toast.LENGTH_LONG).show();
    }

    @Override
    public void run() {
        super.run();
        try {
            Log.d("[Server]", "Création serveur");
            ServerSocket serverSocket = new ServerSocket(8888);
            int i = 0;
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                if (i < 2) { //On accepte que deux connection
                    Log.d("[Server]", "Client connecté");
                    clients[i] = new ServerSideClient(i, getContext(), socket, serverSideClientCallBack, getGame());
                    choices[i] = Choice.UNSET;
                    locks[i] = new Object();
                    clients[i++].run();
                }else
                    socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Choice getChoice(int index) {
        synchronized (this) {
            return choices[index];
        }
    }

    public void setChoice(Choice choice, int index) {
        synchronized (this) {
            synchronized (locks[index]) {
                this.choices[index] = choice;
                locks[index].notify();
            }
        }
    }

    public boolean allChoiceIsSet() {
        synchronized (this) {
            for (Choice choice : choices) {
                if (choice.equals(Choice.UNSET)) return false;
            }
            return true;
        }
    }

    public ServerSideClient[] getClients() {
        return clients;
    }

    public ServerSideClient getClient(int i) {
        return clients[i];
    }
}
