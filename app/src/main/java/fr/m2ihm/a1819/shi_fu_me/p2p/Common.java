package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;

import java.net.InetAddress;

public abstract class Common {
    private final Context context;
    private InetAddress ownerAddress;

    public Common(Context context) {
        this.context = context;
    }

    public void setOwnerAddress(InetAddress ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public InetAddress getOwnerAddress() {
        return ownerAddress;
    }
}
