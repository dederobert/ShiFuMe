package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public final class DiscoverListener implements WifiP2pManager.ActionListener {

    private final Context context;

    public DiscoverListener(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess() {
        Log.d("Discover", "Success");
    }

    @Override
    public void onFailure(int reason) {
        Log.d("Discover", "Failed");
    }
}
