package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.widget.Toast;

public class Client extends Common {

    public Client(Context context) {
        super(context);
        Toast.makeText(context, "Je suis le client", Toast.LENGTH_LONG).show();
    }
}
