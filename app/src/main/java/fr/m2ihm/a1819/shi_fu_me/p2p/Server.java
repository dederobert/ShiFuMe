package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le serveur
 * @version 1.0.0
 */
public class Server extends Common {

    /**
     * List des sockets clients
     */
    private List<ServerSideClient> clients = new ArrayList<>();

    /**
     * Créer un serveur
     * @param context Le context de l'application
     */
    public Server(Context context) {
        super(context);
        Toast.makeText(context, "Je suis le serveur", Toast.LENGTH_LONG).show();
    }

    @Override
    public void run() {
        super.run();
        try {
            Log.d("[Server]", "Création serveur");
            ServerSocket serverSocket = new ServerSocket(8888);
            while (clients.add(new ServerSideClient(getContext(), serverSocket.accept()))) {
                Log.d("[Server]", "Client connecté");
                //Toast.makeText(getContext(), "Client connecté", Toast.LENGTH_LONG).show();
                clients.get(clients.size()-1).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
