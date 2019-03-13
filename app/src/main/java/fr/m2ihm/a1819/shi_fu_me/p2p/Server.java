package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.ServerSocket;
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
    private final List<ServerSideClient> clients = new ArrayList<>();
    private final ServerSideClientCallBack serverSideClientCallBack;

    private final List<Choice> choices = new ArrayList<>();
    private final List<Object> locks = new ArrayList<>();

    private void resetChoice() {
        for(Choice choice: choices)
            choice = Choice.UNSET;
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
            while (clients.add(new ServerSideClient(i, getContext(), serverSocket.accept(), serverSideClientCallBack, getGame()))) {
                Log.d("[Server]", "Client connecté");
                choices.add(Choice.UNSET);
                locks.add(new Object());
                clients.get(i++).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Choice getChoice(int index) {
        return choices.get(index);
    }

    public void setChoice(Choice choice, int index) {
        synchronized (locks.get(index)) {
            this.choices.set(index, choice);
            locks.get(index).notify();
        }
    }

    public List<ServerSideClient> getClients() {
        return clients;
    }
}
