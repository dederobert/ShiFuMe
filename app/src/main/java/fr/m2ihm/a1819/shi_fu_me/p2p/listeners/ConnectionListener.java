package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;


import fr.m2ihm.a1819.shi_fu_me.activities.InGameActivity;
import fr.m2ihm.a1819.shi_fu_me.p2p.Client;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;

public class ConnectionListener implements WifiP2pManager.ConnectionInfoListener {

    private InGameActivity inGameActivity;

    public ConnectionListener(InGameActivity inGameActivity) {
        this.inGameActivity = inGameActivity;
    }


    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

        if (info.groupFormed && !info.isGroupOwner) {
                inGameActivity.setClientServer(new Client(inGameActivity, info.groupOwnerAddress));
        }else{
                inGameActivity.setClientServer(new Server(inGameActivity));
        }
        inGameActivity.getClientServer().execute();
    }
}
