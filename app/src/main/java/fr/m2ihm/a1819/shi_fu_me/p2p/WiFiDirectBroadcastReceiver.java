package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import fr.m2ihm.a1819.shi_fu_me.activities.InGameActivity;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.ConnectionListener;

public final class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private final WifiP2pManager mManager;
    private final WifiP2pManager.Channel mChannel;
    private final InGameActivity mActivity;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       InGameActivity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION:
                    int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
                        Toast.makeText(mActivity, "WiFiDirect activé", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(mActivity, "Vous devez activé le WiFiDirect pour utiliser cette fonction", Toast.LENGTH_LONG).show();
                    break;
                case WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION:
                    // Respond to new connection or disconnections
                    NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
                    if (networkInfo.isConnected())
                        this.mManager.requestConnectionInfo(mChannel, new ConnectionListener(mActivity));
                    break;
//                case WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION:
                // Call WifiP2pManager.requestPeers() to get a list of current peers
                //            if (mManager != null)
                //                mManager.requestPeers(mChannel, new PeersListener(mActivity));
//                    break;
//                case WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION:
//                    // Respond to this device's wifi state changing
//                    break;
            }
        }
    }
}