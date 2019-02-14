package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.os.AsyncTask;

import java.net.InetAddress;

public abstract class Common extends AsyncTask{

    private Context context;

    public Common(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
