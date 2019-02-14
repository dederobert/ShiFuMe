package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.widget.Toast;


public class Server extends Common {


    public Server(final Context context) {
        super(context);
        Toast.makeText(context, "Je suis le serveur", Toast.LENGTH_LONG).show();
    }



}
